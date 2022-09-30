package com.cinemaw.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberFrontController extends HttpServlet {
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
		// 1. 가상주소 계산------------------------------------------------
		System.out.println(" 1. 가상주소 계산 - 끝 \n");

		System.out.println("\n  2. 가상주소 매핑 - 시작");
		// 2. 가상주소 매핑------------------------------------------------
		Action action = null;
		ActionForward forward = null;

		if (command.equals("/MemberInsert.me")) {
			// 회원가입 페이지 보여주기 (DB정보 필요없음)
			System.out.println(" C : /MemberInsert.me 호출 ");
			System.out.println(" C : DB정보가 필요없음-view페이지로 이동 ");

			forward = new ActionForward();
			forward.setPath("./Member_SH/signUp.jsp");
			forward.setRedirect(false);
		} else if (command.equals("/MemberInsertAction.me")) {
			System.out.println(" C : /MemberInsertAction.me 호출 ");
			System.out.println(" C : DB작업 o, 페이지 이동");

			action = new MemberInsertAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberLogin.me")) {
			System.out.println(" C : /MemberLogin.me 호출 ");
			System.out.println(" C : DB작업 o, 페이지 이동");
			forward = new ActionForward();
			forward.setPath("./Member_SH/login.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/MemberLoginAction.me")) {
			System.out.println(" C : /MemberLoginAction.me 호출 ");
			System.out.println(" C : DB작업 O, 페이지 이동");

			action = new MemberLoginAction();

			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/Main.mo")) {
			System.out.println(" C : /Main.mo 호출 ");
			System.out.println(" C : DB작업 X, 페이지 이동");

			forward = new ActionForward();
			forward.setPath("main.jsp");
			forward.setRedirect(false);

		} else if (command.equals("/MemberLogout.me")) {
			System.out.println(" C : /MemberLogout.me 호출 ");
			System.out.println(" C : DB작업 o, 페이지 이동");

			action = new MemberLogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/myPage.me")) {
			System.out.println("Controller : 가상주소 /myPage.me 실제주소: ./myPage.jsp");
			action = new myPageAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*
			 * } else if(command.equals("/MemberInfo.me")){ System.out.
			 * println("Controller : 가상주소 /MemberInfo.me 실제주소: ./member/memberinfo.jsp"
			 * ); action = new MemberInfoAction(); try { forward =
			 * action.execute(request, response); } catch (Exception e) {
			 * e.printStackTrace(); }
			 */
		} else if (command.equals("/MemberUpdate.me")) {
			System.out.println("Controller : 가상주소 MemberUpdate/.me 실제주소: ./member/updateForm.jsp");
			action = new MemberUpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberUpdatePro.me")) {
			System.out.println("Controller : 가상주소 MemberUpdatePro/.me 실제주소: ./member/updateForm.jsp");
			action = new MemberUpdateProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberDelete.me")) {
			System.out.println("@@@@ Controller : 가상주소 /MemberDelete.me 실제주소: ./member/deleteForm.jsp");
			forward = new ActionForward();
			forward.setPath("./Member_SH/deleteForm.jsp");
			forward.setRedirect(false);// 주소전환없이 jsp페이지가 보여야함 forwarding방식
		} else if (command.equals("/MemberDeleteAction.me")) {
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response); // 어차피 null에서
																// foward에 넣지
																// 않아도 됨
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

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
