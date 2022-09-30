package com.cinemaw.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cinemaw.movie.db.MovieDAO;
import com.cinemaw.movie.db.MovieDTO;

public class ReviewContentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println(" M : ReviewContentAction_execute() ȣ�� ");

		// ���޵� ���� ����(bno,pageNum)
		// => ���޵Ǵ� �Ķ���Ͱ��� ���
		// ���̺� ����Ǵ� ���̸� ����ȯ O
		// ���̺� ����ȵǴ� ���̸� ����ȯ X
		int m_id = Integer.parseInt(request.getParameter("m_id"));
		String pageNum = request.getParameter("pageNum");

		// MovieDAO ��ü ����
		MovieDAO dao = new MovieDAO();

		// ��ȸ�� 1�����ϱ� - updateReadcount()
		// dao.updateReadcount(bno);

		// System.out.println(" M : ��ȸ�� 1���� �Ϸ� !!! ");

		// �Խ��� �� 1���� ������ �����ͼ� ���

		MovieDTO dto = dao.getMovie(m_id, (String) request.getSession().getAttribute("id"));

		// Model ��ü ���� ��� X
		// view ���� ���� ��� O
		// => model ��ü �ִ� ������ view �̵�

		// request ������ ����
		request.setAttribute("dto", dto);
		// request.setAttribute("dto2", dao.getBoard(bno));
		request.setAttribute("pageNum", pageNum); // ����Ҷ� ���

		// ��� view �������� �̵�
		ActionForward forward = new ActionForward();
		forward.setPath("./movie_MJ/movieContent.jsp");
		forward.setRedirect(false);

		return forward;
	}

}