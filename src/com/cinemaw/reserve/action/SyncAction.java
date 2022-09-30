package com.cinemaw.reserve.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.reserve.db.ReserveDAO;
import com.cinemaw.reserve.db.ReserveDTO;


public class SyncAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : SyncAction.execute() 호출");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		ReserveDTO dtoR = (ReserveDTO) session.getAttribute("dtoR");

		System.out.println("dtoR" + dtoR);

		int r_id = Integer.parseInt(request.getParameter("r_id"));

		int r_adult = Integer.parseInt(request.getParameter("r_adult"));
		int r_teenager = Integer.parseInt(request.getParameter("r_teenager"));
		int r_elderly = Integer.parseInt(request.getParameter("r_elderly"));

		// 받아온 정보 dto에 저장하기
		ReserveDAO dao = new ReserveDAO();
		dtoR.setR_id(r_id);
		dtoR.setR_adult(r_adult);
		dtoR.setR_teenager(r_teenager);
		dtoR.setR_elderly(r_elderly);

		int num = r_adult + r_teenager + r_elderly; // 예매 인원수

		// 예매 좌석 저장하기
		String[] seatss = request.getParameterValues("r_seat");

		if (num == 1) {
			dtoR.setR_seat_1(seatss[0]);
		} else if (num == 2) {
			dtoR.setR_seat_1(seatss[0]);
			dtoR.setR_seat_2(seatss[1]);
		} else if (num == 3) {
			dtoR.setR_seat_1(seatss[0]);
			dtoR.setR_seat_2(seatss[1]);
			dtoR.setR_seat_3(seatss[2]);
		} else if (num == 4) {
			dtoR.setR_seat_1(seatss[0]);
			dtoR.setR_seat_2(seatss[1]);
			dtoR.setR_seat_3(seatss[2]);
			dtoR.setR_seat_4(seatss[3]);
		}

		System.out.println("M : " + dtoR);
		
		int result = 0;

		// 예매 제어하기
		synchronized (this) { // 인스턴스 메서드 안의 동기화 블럭
			// 디비의 좌석리스트 받아오기
			List<ReserveDTO> seatList = dao.getSeatList(dtoR.getT_id(), dtoR.getS_date(), dtoR.getS_time(),
					dtoR.getM_id());
			List<String> seat_1 = new ArrayList<String>();
			List<String> seat_2 = new ArrayList<String>();
			List<String> seat_3 = new ArrayList<String>();
			List<String> seat_4 = new ArrayList<String>();

			for (int i = 0; i < seatList.size(); i++) {
				ReserveDTO dtos = seatList.get(i);
				seat_1.add(dtos.getR_seat_1());
				seat_2.add(dtos.getR_seat_2());
				seat_3.add(dtos.getR_seat_3());
				seat_4.add(dtos.getR_seat_4());
			}
			System.out.println("좌석정보 받아오기 완료.-------------------------");

			// 예매한 좌석과 비교하기
			for (int i = 0; i < num; i++) {
				if (seat_1.contains(seatss[i])) {
					result = 1;
					break;
				} else if (seat_2.contains(seatss[i])) {
					result = 1;
					break;
				} else if (seat_3.contains(seatss[i])) {
					result = 1;
					break;
				} else if (seat_4.contains(seatss[i])) {
					result = 1;
					break;
				}
			}
			System.out.println("검토 완료.------------------------------------");

		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(result);
		out.close();
		return null;
	}

}
