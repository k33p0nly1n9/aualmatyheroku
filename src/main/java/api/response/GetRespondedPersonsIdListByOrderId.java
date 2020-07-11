/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.response;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bayan
 */
public class GetRespondedPersonsIdListByOrderId extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
     PrintWriter out = response.getWriter();

       int orderId = -1;
        try {
            orderId = Integer.parseInt(request.getParameter("orderId"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        
      models.DbHelper db = new models.DbHelper();
      ArrayList <Integer> r = db.getRespondedPersonsIdListByOrderId(orderId);

        if (r == null || r.size() <= 0) {
           javax.json.JsonObjectBuilder objectBuilder = javax.json.Json.createObjectBuilder().
                    add("Ошибка", "Пользователей не найдено");
            out.print(objectBuilder.build().toString());
            return;
        }

         javax.json.JsonObjectBuilder objectBuilder = javax.json.Json.createObjectBuilder();
       javax.json.JsonArrayBuilder arrayBuilder = javax.json.Json.createArrayBuilder();

        for (Integer s : r) {
               arrayBuilder.add(s);
        }

       objectBuilder.add("persons", arrayBuilder.build());
       
        out.print(objectBuilder.build().toString());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
