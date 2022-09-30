package com.cinemaw.movie.action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.movie.db.MovieDAO;
import com.cinemaw.movie.db.MovieDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.movies.MovieVO;

public class MovieWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		MovieDAO dao = new MovieDAO();
		MovieDTO movieVO = new MovieDTO();

		request.setCharacterEncoding("utf-8");

		String realFolder = "";

		String saveFolder = "image";
		String encType = "utf-8";
		int maxSize = 5 * 1024 * 1024;

		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		System.out.println(" real Folder : " + realFolder);

		MultipartRequest multi = null;
		multi = new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
		Enumeration params = multi.getParameterNames();

		while (params.hasMoreElements()) {
			String name = (String) params.nextElement();
			String value = multi.getParameter(name);
			System.out.println(name + " = " + value);

			if (name.equals("nm"))
				movieVO.setM_nm(value);
			if (name.equals("story"))
				movieVO.setM_story(value);
			if (name.equals("genre"))
				movieVO.setMv_genre(value);
			if (name.equals("runtime"))
				movieVO.setMv_runtime(Integer.parseInt(value));
			if (name.equals("grade"))
				movieVO.setMv_grade(value);
			if (name.equals("rlsdate"))
				movieVO.setMv_rlsdate(value);
			// if(name.equals("picture"))
			// movieVO.setMv_picture(multi.getFilesystemName(name));
			if (name.equals("video"))
				movieVO.setMv_video(value);
		}

		Enumeration files = multi.getFileNames();
		while (files.hasMoreElements()) {
			String name = (String) files.nextElement();
			String filename = multi.getFilesystemName(name);
			System.out.println("getFilesystemName  :" + filename);
			movieVO.setMv_picture(filename);
		}

		//
		// String nm = request.getParameter("nm");
		// String story = request.getParameter("story");
		// String person = request.getParameter("person");
		// String genre = request.getParameter("genre");
		// String runtime = request.getParameter("runtime");
		// String grade = request.getParameter("grade");
		// String rlsdate = request.getParameter("rlsdate");
		// String picture = request.getParameter("picture");
		// String video = request.getParameter("video");
		// String dtb = request.getParameter("dtb");
		// String rserverId =request.getParameter("video");

		// movieVO.setM_nm(nm);
		// movieVO.setM_story(story);
		// movieVO.setM_person(person);
		// movieVO.setMv_genre(genre);
		// movieVO.setMv_runtime(Integer.parseInt(runtime));
		// movieVO.setMv_grade(grade);
		// movieVO.setMv_rlsdate(rlsdate);
		// movieVO.setMv_picture(picture);
		// movieVO.setMv_video(video);

		// movieVO.setMvdt_dtb(dtb);
		int insertId = dao.movieInsert(movieVO);

		// 출력 view 페이지로 이동
		ActionForward forward = new ActionForward();
		System.out.println(" request getContextPath :" + request.getContextPath());

		// "/MovieWrite.mo?write=ok"
		// forward.setPath(request.getContextPath()+"/MovieList.mo?isNow=N");
		forward.setPath(request.getContextPath() + "/MovieContent.mo?m_id=" + insertId + "&pageNum=1&write=ok");
		forward.setRedirect(true);

		return forward;
	}

}