package com.cinemaw.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.review.db.ReviewDAO;
import com.cinemaw.review.db.ReviewVO;

public class InsertReview implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		ReviewVO vo = new ReviewVO();

		// 세션에서 로그인한 유저 정보 가져오기
		String id = (String) request.getSession().getAttribute("loginId");

		// 영화 아이디값 가져오기
		Integer mid = Integer.parseInt(request.getParameter("m_id"));

		// 현재 페이번호 가져오기 기본값이 1
		Integer pageNum = 1;
		if (request.getParameter("pageNum") != null && !request.getParameter("pageNum").equals("")) {
			// request.getParameter("pageNum") 널이 아니거나 공백이 아니면 번호 저장
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}

		try {
			// vo.setR_id(Integer.parseInt("1"));
			vo.setM_id(mid);

			vo.setU_id(id);
			vo.setR_score(Integer.parseInt(request.getParameter("r_score")));  // null 수정
			vo.setR_text(request.getParameter("r_text"));
			// 예약번호 넣기
			vo.setReserveId(Integer.parseInt(request.getParameter("reserveId")));
		} catch (Exception e) {
			e.printStackTrace();
		}

		ReviewDAO dao = new ReviewDAO();
		dao.movieInsert(vo);

		ActionForward forward = new ActionForward();

		forward.setPath(request.getContextPath() + "/MovieContent.mo?m_id=" + mid + "&page=pageNum=" + pageNum);
		forward.setRedirect(true);

		return forward;
	}

}
