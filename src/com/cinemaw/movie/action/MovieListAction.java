package com.cinemaw.movie.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.movie.db.MovieDAO;
import com.cinemaw.movie.db.MovieDTO;

public class MovieListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(" M : MovieListAction_execute() 호출");
		String isMain = null; // 메인화면여부 확인
		if (null != request.getAttribute("isMain"))
			isMain = (String) request.getAttribute("isMain");

		// MovieDAO 객체 생성
		MovieDAO dao = new MovieDAO();

		// 게시판에 작성되어 있는 전체 글 개수
		int cnt = dao.getMovieCount();

		// 페이징 처리----------------------------------------

		// 한 페이지에 보여줄 글의 개수 설정
		// ./MovieList.mo?pageNum=2&pageSize=3
		String urlPageSize = request.getParameter("pageSize");
		if (urlPageSize == null) {
			urlPageSize = "20";
		}
		if ("Y".equals(isMain))
			urlPageSize = "5"; // 메인화면은 5개까지만 출력
		int pageSize = Integer.parseInt(urlPageSize);

		// 현 페이지가 몇번째 페이지인지 계산
		// >> 페이지 정보가 없을경우 항상 1페이지
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		// 시작행 번호 계산 1 11 21 31 .....
		int currentPage = Integer.parseInt(pageNum);
		int startRow = (currentPage - 1) * pageSize + 1;

		// 끝행 번호 계산 10 20 30 40.....
		int endRow = currentPage * pageSize;

		// 장르별보기
		String genre = "";
		if (null == request.getParameter("genre") || "".equals(request.getParameter("genre"))) {

		} else {
			genre = request.getParameter("genre");
		}

		// 현재상영작 여부
		boolean isNow = true;
		if ("N" == request.getParameter("isNow") || "N".equals(request.getParameter("isNow"))) {
			isNow = false;
		}
		// 페이징 처리----------------------------------------

		// dao 메서드 중에서 게시판 글정보를 모두 가져오는 메서드 호출
		// List<MovieDTO> movieList = dao.getMovieList();
		List<MovieDTO> movieList = dao.getMovieList(startRow, pageSize, genre, isNow);

		System.out.println(" M : 게시판 글정보 저장완료! ");

		// 페이징 처리 2(하단 페이지 링크)---------------------------------------

		// 전체 페이지 수 계산
		// ex) 전체 글 50개 -> 한페이지 10개씩 출력, 5개 페이지
		// ex) 전체 글 55개 -> 한페이지 10개씩 출력, 6개 페이지

		int pageCount = cnt / pageSize + (cnt % pageSize == 0 ? 0 : 1);

		// 한 화면에 보여줄 페이지수(페이지 블럭)
		int pageBlock = 3;

		// 페이지블럭 시작번호 1~10 => 1, 11~20 => 11, 21~30 => 21
		int startPage = ((currentPage - 1) / pageBlock) * pageBlock + 1;

		// 페이지블럭 끝번호 1~10 => 10, 11~20 => 20
		int endPage = startPage + pageBlock - 1;

		// 총 페이지, 페이지 블럭(끝번호) 비교
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		// 페이징 처리 2(하단 페이지 링크)---------------------------------------

		// view 페이지 정보 전달을 위해서 request 영역에 저장
		request.setAttribute("movieList", movieList);

		System.out.println(" M : 리스트 정보 저장 request 영역");

		// 페이징 처리 정보 전달 (request 영역)
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("cnt", cnt);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		System.out.println(" M : 페이징 처리정보 저장");

		// 화면에 출력
		// 페이지 이동(화면 전환)
		ActionForward forward = new ActionForward();
		if ("Y".equals(isMain))
			forward.setPath("./main.jsp");
		else
			forward.setPath("./movie_MJ/movieList.jsp");
		// forward.setPath("/MovieList.mo");
		forward.setRedirect(false);

		return forward;
	}

}