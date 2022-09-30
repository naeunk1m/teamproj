package com.cinemaw.review.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReviewFrontContoller extends HttpServlet {

	// URL : http://localhost:8088/Project_Itwill/MovieList.mo
	// URI : /Project_Itwill/MovieList.mo
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" GET,POST방식 모두 호출 - doProcess() 실행 ");

		System.out.println("\n 1. 가상주소 계산 - 시작");
		// 1. 가상주소 계산------------------------------------------------
		String requestURI = request.getRequestURI();
		System.out.println(" C : requestURI : " + requestURI);

		String ctxPath = request.getContextPath();
		System.out.println(" C : ctxPath : " + ctxPath);

		String command = requestURI.substring(ctxPath.length());
		System.out.println(" C : command : " + command);
		// 1. 가상주소 계산 끝 -----------------------------------------------
		System.out.println(" 1. 가상주소 계산 - 끝 \n");

		System.out.println("\n  2. 가상주소 매핑 - 시작");
		// 2. 가상주소 매핑------------------------------------------------
		Action action = null;
		ActionForward forward = null;

		/*
		 * if (command.equals("/MovieWrite.mo")) {
		 * 
		 * //관리자 접근만 가능한 곳에서 String isAdmin=
		 * (String)request.getSession().getAttribute("isAdmin");
		 * System.out.println(" isAdmin  "+ isAdmin); if(isAdmin==null ||
		 * !isAdmin.equals("true")){ try { PrintWriter script =
		 * response.getWriter(); script.println("<script>");
		 * script.println("alert('Only admin access ')");
		 * script.println("location.href='main.html'");
		 * script.println("</script>"); } catch (Exception e) {
		 * e.printStackTrace(); } forward = new ActionForward();
		 * forward.setPath("/MovieList.mo"); forward.setRedirect(true); return;
		 * }
		 * 
		 * // 영화등록 페이지 보여주기 (DB정보 필요없음)
		 * System.out.println(" C : /MovieWrite.mo 호출 ");
		 * System.out.println(" C : DB정보가 필요없음-view페이지로 이동 ");
		 * 
		 * forward = new ActionForward();
		 * forward.setPath("./admin_Movie/writeForm.jsp");
		 * forward.setRedirect(true); } else
		 */
		if (command.equals("/InsertReview.re")) {
			System.out.println(" C :/InsertReview.re 호출 ");
			System.out.println(" C : DB작업 o, 페이지 이동");

			// MovieWriteAction() 객체 생성
			/* MovieWriteAction mwAction = new MovieWriteAction(); */
			//

			action = new InsertReview();
			try {
				forward = action.execute(request, response);
				// forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		} else if(command.equals("/UpdateReview.re")) {
			System.out.println(" C :/InsertReview.re 호출 ");
			System.out.println(" C : DB작업 o, 페이지 이동");

			// MovieWriteAction() 객체 생성
			/* MovieWriteAction mwAction = new MovieWriteAction(); */
			//

			action = new UpdateReview();
			try {
				forward = action.execute(request, response);
				// forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			

		}
		/*
		 * else if (command.equals("/MovieList.mo")) {
		 * System.out.println(" C : /MovieList.mo 호출 ");
		 * System.out.println(" C : DB정보가 필요,페이지 이동x,페이지 출력o");
		 * 
		 * // MovieListAction() 객체 생성 MovieListAction listAction = new
		 * MovieListAction(); //action = new MovieListAction();
		 * 
		 * try { System.out.println(" C : 해당 Model 객체 호출 "); forward =
		 * listAction.execute(request, response); //forward =
		 * action.execute(request, response); } catch (Exception e) {
		 * e.printStackTrace(); }
		 * 
		 * 
		 * } else if (command.equals("/MovieContent.mo")) {
		 * System.out.println(" C : /MovieContent.mo 호출 ");
		 * System.out.println(" C : DB정보 사용, 출력");
		 * 
		 * // MovieContentAction 객체 action = new MovieContentAction();
		 * 
		 * try { forward = action.execute(request, response); } catch (Exception
		 * e) { e.printStackTrace(); }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * else if (command.equals("/Main.mo")) {
		 * System.out.println(" C : /Main.mo 호출 ");
		 * System.out.println(" C : DB정보 사용, 출력");
		 * 
		 * forward = new ActionForward(); forward.setPath("./main.jsp");
		 * forward.setRedirect(false); }
		 * 
		 * else if (command.equals("/Login.me")) {
		 * System.out.println(" C : /login.me 호출 ");
		 * System.out.println(" C : DB정보 사용, 출력");
		 * 
		 * forward = new ActionForward(); forward.setPath("./login.jsp");
		 * forward.setRedirect(false); }
		 */

		// 2. 가상주소 매핑------------------------------------------------
		System.out.println("  2. 가상주소 매핑 - 끝 \n");

		System.out.println("\n  3. 가상주소 이동 - 시작");
		// 3. 가상주소 이동------------------------------------------------
		if (forward != null) {
			// 페이지 이동정보가 있을때

			if (forward.isRedirect()) {
				// true - sendRedirect() 방식으로 이동
				System.out.println(" C : true-" + forward.getPath() + "이동, sendRedirect() 방식");
				response.sendRedirect(forward.getPath());

			} else {
				// false - forward() 방식으로 이동
				System.out.println(" C : false-" + forward.getPath() + "이동, forward() 방식");
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}

		}
		// 3. 가상주소 이동------------------------------------------------
		System.out.println("\n  3. 가상주소 이동 - 끝");
	}
	//////////////////////////////// doProcess////////////////////////////////////

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" GET방식 호출 - doGet() 실행");
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(" POST방식 호출 - doPost() 실행 ");
		doProcess(request, response);
	}

}