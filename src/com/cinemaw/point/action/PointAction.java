package com.cinemaw.point.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cinemaw.point.db.PointDAO;
import com.cinemaw.point.db.PointDAO2;
import com.cinemaw.point.db.PointDTO;
import com.cinemaw.point.db.PointDTO2;

public class PointAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : PointAction_execute() 호출");
		
		PointDAO dao = new PointDAO();
		PointDAO2 dao2 = new PointDAO2();
		
		HttpSession session=request.getSession();
		String u_id = (String)session.getAttribute("loginId");
		
		System.out.println("u_id : "+u_id);
		
		//dao 메서드 중에서 게시판 글정보를 모두 가져오는 메서드 호출 
		List<PointDTO> pointList = dao.getPointList(u_id); //적립내역
		List<PointDTO2> pointList2 = dao2.getPointList2(u_id); //사용내역
		
		//view 페이지 정보 전달을 위해서 request 영역에 저장
//		pointList2.get(0).setTotal(dao2.PointTotal(u_id));
//		pointList.get(0).setTotal(dao.PointTotal(u_id));
		int pointTotal=0;
		int pointTotal2=0;
		
		if(pointList != null) {
			for(int i=0; i<pointList.size(); i++){
				pointTotal += pointList.get(i).getPoint();
			}
		} 
		
		if(pointList2 != null) {
			for(int i=0; i<pointList2.size(); i++){
				pointTotal2 += pointList2.get(i).getR_user_point();
			}
			
		} 
				request.setAttribute("pointList", pointList);
				request.setAttribute("pointTotal", pointTotal);
				request.setAttribute("pointList2", pointList2);
				request.setAttribute("pointTotal2", pointTotal2);
				
				System.out.println(" M : 리스트 정보 저장 request 영역");

		
		//화면에 출력
		//페이지 이동(화면 전환)
		ActionForward forward = new ActionForward();
		forward.setPath("./Point_SH/mypoint.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}

}
