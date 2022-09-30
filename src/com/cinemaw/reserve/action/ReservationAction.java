package com.cinemaw.reserve.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.reserve.db.ReservationDAO;
import com.cinemaw.reserve.db.ReservationDTO;

public class ReservationAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : ReservationAction_execute() 호출");

		ReservationDAO dao = new ReservationDAO();

		List<ReservationDTO> reservationList = dao.getReservationList();
		System.out.println("reservationList.size()" + reservationList.size());

		request.setAttribute("reservationList", reservationList);

		ActionForward forward = new ActionForward();
		forward.setPath("./reservation/Reservation1.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
