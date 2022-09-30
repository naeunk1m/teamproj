package com.cinemaw.reserve.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.reserve.action.ActionForward;

public class ReserveFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("c : doProcess() 호출");

		// 1.가상주소계산/////////////////////////////////////////////////////////////////////////
		System.out.println("1. 가상주소 계산 - 시작");
		String requestURI = request.getRequestURI(); // 프로젝트명+주소
		System.out.println("C : requestURI : " + requestURI);

		String ctxPath = request.getContextPath(); // 프로젝트명
		System.out.println("C : ctxPath : " + ctxPath);

		String command = requestURI.substring(ctxPath.length()); // 프로젝트명 길이만큼 자름
		System.out.println("C : command : " + command);
		System.out.println("1. 가상주소 계산 - 끝");

		// 2. 가상 주소
		// 매핑/////////////////////////////////////////////////////////////////////////
		System.out.println("2. 가상주소 매핑 - 시작");
		Action action = null;
		ActionForward forward = null;

		if (command.equals("/MovieSelect.res")) { // 영화선택페이지로 이동
			forward = new ActionForward();
			forward.setPath("./reservation/Reservation1.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/SeatSelect.res")) { // 영화선택액션으로 이동
			System.out.println("C : /MovieSelectAction.re 호출");
			System.out.println("C : DB에서 정보 가져오기, 페이지 이동");

			action = new MovieSelectAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/PayView.res")) { // 좌석선택액션으로 이동

			System.out.println("C : /SeatSelectAction.re 호출");

			action = new SeatSelectAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (command.equals("/seatSelectView.res")) { // 좌석선택페이지로 이동
			forward = new ActionForward();
			forward.setPath("./reservation/seatSelectView.jsp");
			forward.setRedirect(false);
		}

		else if (command.equals("/payView.res")) { // 결제페이지로 이동
			forward = new ActionForward();
			forward.setPath("./reservation/payView.jsp");
			forward.setRedirect(false);
		}

		else if (command.equals("/PayAction.res")) { // 결제액션으로 이동 - 디비에 저장하기

			System.out.println("C : /PayAction.re 호출");

			action = new PayAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (command.equals("/payCom.res")) { // 결제완료페이지 이동
			forward = new ActionForward();
			forward.setPath("./reservation/payCom.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/errorPage.res")) { // 에러페이지->영화선택 페이지
														// -사용안함////////
			forward = new ActionForward();
			forward.setPath("./errorPage.jsp");
			forward.setRedirect(true);
		} else if (command.equals("/RefreshPage.res")) { // 결제시간 초과 -> 영화선택 페이지로
															// 이동

			System.out.println("C : /RefreshPage.re 호출");

			action = new RefreshAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		else if (command.equals("/Reservation1.res")) {
			// 예매 페이지 보여주기 (DB정보 필요 없음)
			System.out.println("C : /Reservation.re 호출");
			System.out.println("C : DB정보가 필요없음 - view 페이지로 이동");

			forward = new ActionForward();
			forward.setPath("./reservation/Reservation1.jsp");

			forward.setRedirect(false);
		}

		else if (command.equals("/ReservationeAction.res")) {
			// Action이 붙은 페이지는 Pro페이지의 역할을 한다
			System.out.println("C : /ReservationAction.re 호출");
			System.out.println("C : DB 작업 o, 페이지 이동");

			action = new ReservationAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/Reservation.res")) {

			System.out.println("C : /Reservation.re 호출");
			System.out.println("C : DB정보가 필요, 페이지 이동x, 페이지 출력o");

			// ReservationListAction() 객체 생성
			// ReservationListAction listAction = new ReservationListAction();
			action = new ReservationAction();
			try {
				System.out.println("C : 해당 Model 객체 호출");
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ReservationDate.res")) {
			System.out.println("C : /ReservationDate.res 호출");
			System.out.println("C : DB 정보 사용, 출력");

			action = new ReservationDate();
			try {
				System.out.println("C : 해당 Model 객체 호출");
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		else if (command.equals("/ReservationTime.res")) {
			System.out.println("C : /ReservationTime.re 호출");
			System.out.println("C : DB 정보 사용, 출력");

			action = new ReservationTime();
			try {
				System.out.println("C : 해당 Model 객체 호출");
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		else if (command.equals("/SyncAction.res")) {
			System.out.println("C : /SyncAction.res 호출");
			System.out.println("C : DB 정보 사용, 출력");

			action = new SyncAction();
			try {
				System.out.println("C : 해당 Model 객체 호출");
				forward = action.execute(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		System.out.println("2. 가상주소 매핑 - 끝");

		// 3. 가상주소
		// 이동//////////////////////////////////////////////////////////////////////////
		System.out.println("3. 가상주소 이동 - 시작");
		if (forward != null) {
			if (forward.isRedirect()) {
				// true
				response.sendRedirect(forward.getPath());
				System.out.println("C : true - " + forward.getPath());
			} else {
				// false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
				System.out.println("C : false - ");
			}
		}
		System.out.println("3. 가상주소 이동 - 끝");

	}// doProcess

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
