package com.cinemaw.movie.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.cinemaw.movie.db.MovieDAO;
import com.cinemaw.movie.db.MovieDTO;
import com.cinemaw.review.db.ReviewDAO;
import com.cinemaw.review.db.ReviewVO;

public class MovieContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : MovieContentAction_execute() 호출 ");

		// 전달된 정보 저장(bno,pageNum)
		// => 전달되는 파라메터값의 경우
		// 테이블에 저장되는 값이면 형변환 O
		// 테이블에 저장안되는 값이면 형변환 X
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		String pageNum = request.getParameter("pageNum");
		
		String order = request.getParameter("order");
		if(order==null || order.equals("")) order="new";

		// MovieDAO 객체 생성
		MovieDAO dao = new MovieDAO();

		// 조회수 1증가하기 - updateReadcount()
		// dao.updateReadcount(bno);

		// System.out.println(" M : 조회수 1증가 완료 !!! ");

		// 게시판 글 1개의 정보를 가져와서 출력
		String id = (String) request.getSession().getAttribute("loginId");
		MovieDTO dto = dao.getMovie(m_id, id);

		// 리뷰 가져오기
		ReviewDAO dao2 = new ReviewDAO();
		List<ReviewVO> reviewlist = dao2.reviewSelect(m_id, order);

		System.out.println("리뷰 가져오기  갯수  : " + reviewlist.size());

		// Model 객체 정보 출력 X
		// view 에서 정보 출력 O
		// => model 객체 있는 정보를 view 이동

		// request 영역에 저장
		request.setAttribute("dto", dto);
		// request.setAttribute("dto2", dao.getBoard(bno));
		request.setAttribute("pageNum", pageNum); // 출력할때 사용
		request.setAttribute("reviewlist", reviewlist);

		// 출력 view 페이지로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./movie_MJ/movieContent.jsp");

		forward.setRedirect(false);

		return forward;
	}

}