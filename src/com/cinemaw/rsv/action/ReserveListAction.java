package com.cinemaw.rsv.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.board.db.BoardDAO;
import com.cinemaw.board.db.BoardDTO;
import com.cinemaw.rsv.db.ReserveDAO;
import com.cinemaw.rsv.db.ReserveDTO;

public class ReserveListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		//BoardDAO 객체 생성
				ReserveDAO dao = new ReserveDAO();
				
				
				//세션값 생성
				HttpSession session = request.getSession();
				String u_id = (String) session.getAttribute("loginId");
				
				
				//dao 메서드 중에 게시판 글 정보를 모두 가져오는 메서드 호출
				List<ReserveDTO> reserveList = dao.getReserveList(u_id); 
				
				//view 페이지 정보 전달을 위해서 request 영역에 저장
				request.setAttribute("reserveList", reserveList);
				
				System.out.println(" M : 리스트 정보 저장 request 영역");
				
				
				
				
				
				//화면에 출력
				//페이지 이동(화면 전환)
				ActionForward forward = new ActionForward();
				forward.setPath("./Reserve_SH/reserveList.jsp");
				forward.setRedirect(false);
				
				
				return forward;
			}

}
