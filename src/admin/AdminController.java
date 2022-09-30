package admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.member.action.ActionForward;
import com.cinemaw.movie.action.MovieListAction;

public class AdminController extends HttpServlet {

	// URL : http://localhost:8088/Project_Itwill/MovieList.mo
	// URI : /Project_Itwill/MovieList.mo
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8 ");
		System.out.println(" GET,POST방식 모두 호출 - doProcess() 실행 ");

		System.out.println("\n 11. 가상주소 계산 - 시작");
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
		PrintWriter script = null;
		if (command.equals("/AdminMovieWrite.am")) {

			System.out.println("// 관리자 접근만 가능한 곳에서 :" + request.getSession().getAttribute("loginId"));
			// 관리자 접근만 가능한 곳에서
			Object loginId = request.getSession().getAttribute("loginId");

			if (loginId == null) {
				try {
					script = response.getWriter();
					response.setContentType("text/html; charset=utf-8 ");
					script.println("<script>");
					script.println("alert('로그인 후 이용 가능합니다.')");
					script.println("location.href='" + request.getContextPath() + "/MemberLogin.me'");
					script.println("</script>");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					script.close();
				}

			} else if (!String.valueOf(loginId).equals("admin")) {
				try {
					script = response.getWriter();
					response.setContentType("text/html; charset=utf-8 ");
					script.println("<script>");
					script.println("alert('관리자만 접근가능 합니다.')");
					script.println("location.href='" + request.getContextPath() + "/'");
					script.println("</script>");
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					script.close();
				}
				// forward = new ActionForward();
				// forward.setPath("/MovieList.mo");
				// forward.setRedirect(true);
				// return;
			}

			// 영화등록 페이지 보여주기 (DB정보 필요없음)
			System.out.println(" C : /MovieWrite.am 호출 ");
			System.out.println(" C : DB정보가 필요없음-view페이지로 이동 ");
			// String page
			// =request.getContextPath()+"/admin_Movie/writeForm.jsp";
			String page = "/admin_Movie/writeForm.jsp";
			System.out.println(" 이동할 페이지 : " + page);
			forward = new ActionForward();
			// forward.setPath(page);
			// forward.setRedirect(false);
			//
			RequestDispatcher dis = request.getRequestDispatcher(page);
			dis.forward(request, response);

		}

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