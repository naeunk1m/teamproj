package com.cinemaw.movie.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	// ����x,���o
	// �ν��Ͻ� �޼���x,�߻�޼���o
	// => ����� ���ؼ� �߻�޼��带 �������̵� ��� (������)

	/**
	 * �߻�޼����̸�, �ݵ�� �������̵��ؼ� ����ؾ���. 
	 * �����Ҷ� request, response ������ �����ؾ����� ȣ�� ����. 
	 * ȣ���� �Ϸ�Ǹ� ActionForward(�ּ�,���) ������ ����
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}