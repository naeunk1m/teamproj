package com.cinemaw.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.review.db.ReviewDAO;

public class UpdateReview implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		Integer pageNum = 1;
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		int r_id = Integer.parseInt(request.getParameter("r_id"));
		int r_score;
		if(request.getParameter("r_score") == null) {
			r_score = 0;
		} else {
			r_score = Integer.parseInt(request.getParameter("r_score"));
		}
		
		String r_text = (String)request.getParameter("r_text");
		
		ReviewDAO dao = new ReviewDAO();
		dao.updateReview(r_id, r_score, r_text);
		
		ActionForward forward = new ActionForward();

		forward.setPath(request.getContextPath() + "/MovieContent.mo?m_id=" + m_id + "&page=pageNum=" + pageNum);
		forward.setRedirect(true);
		
		return forward;
//		return null;
	}

}
