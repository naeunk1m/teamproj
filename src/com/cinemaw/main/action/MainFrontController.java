package com.cinemaw.main.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainFrontController extends HttpServlet {
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

		if (command.equals("/Main.mo")) {
			// 메인 페이지 보여주기 (DB정보 필요없음)
			System.out.println(" C : /Main.mo 호출 ");
			System.out.println(" C : DB정보가 필요없음-view페이지로 이동 ");

			forward = new ActionForward();
			forward.setPath("main.jsp");
			forward.setRedirect(false);
		}

	}

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