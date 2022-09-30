package com.cinemaw.reserve.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cinemaw.reserve.db.ReservationDAO;
import com.cinemaw.reserve.db.ReservationDTO;

public class ReservationDate implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      int m_id = Integer.parseInt(request.getParameter("m_id"));
      // String s_date = request.getParameter("s_date");
      // System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
      ReservationDAO dao = new ReservationDAO();

      List<ReservationDTO> reservationDateList = dao.getReservationDateList(m_id);
      System.out.println(reservationDateList);
      JSONArray dateList = new JSONArray();
      for (int i = 0; i < reservationDateList.size(); i++) {
         JSONObject dateObject = new JSONObject();
         dateObject.put("s_date", reservationDateList.get(i).getS_date());
         dateObject.put("m_id", reservationDateList.get(i).getM_id());
         

         dateList.add(dateObject);
      }

      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println(dateList);
      out.close();

      return null;
   }
}