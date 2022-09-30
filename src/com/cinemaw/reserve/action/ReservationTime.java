package com.cinemaw.reserve.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cinemaw.reserve.db.ReservationDAO;
import com.cinemaw.reserve.db.ReservationDTO;

public class ReservationTime implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      // System.out.println("##############################");
      String s_date = request.getParameter("s_date");
      int m_id = Integer.parseInt(request.getParameter("m_id"));
      // System.out.println("##############################"+s_date+","+m_id);

      ReservationDAO dao = new ReservationDAO();

      List<ReservationDTO> reservationTimeList = dao.getReservationTimeList(s_date, m_id);

      JSONArray timeList = new JSONArray();
      for (int i = 0; i < reservationTimeList.size(); i++) {
         JSONObject timeObject = new JSONObject();
         System.out.println(reservationTimeList.get(i).getM_id());
         System.out.println(reservationTimeList.get(i).getS_time());
         timeObject.put("s_time", reservationTimeList.get(i).getS_time());
         timeObject.put("m_id", reservationTimeList.get(i).getM_id());
         timeObject.put("s_date", reservationTimeList.get(i).getS_date());
         
         timeList.add(timeObject);
      }
      System.out.println(reservationTimeList);
      response.setContentType("text/html; charset=UTF-8");
      PrintWriter out = response.getWriter();
      out.println(timeList);
      out.close();

      return null;
   }

}