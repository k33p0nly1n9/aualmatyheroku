/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.response;

import java.io.IOException;
import java.io.PrintWriter;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.*;

/**
 *
 * @author bayan
 */
public class GetResponse extends HttpServlet {

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
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        models.DbHelper db = new models.DbHelper();
       Response r = db.getResponse(id);

        if (r == null) {
            javax.json.JsonObjectBuilder objectBuilder = javax.json.Json.createObjectBuilder().
                    add("Ошибка", "Отклик не найден");
            out.print(objectBuilder.build().toString());
            return;
        }

        JsonObjectBuilder builderr = Json.createObjectBuilder();

        builderr.add("id", r.getId());
        builderr.add("orderId", r.getOrderId());
        builderr.add("personId",r.getPersonId());
        builderr.add("text",r.getText());
         builderr.add("price",r.getPrice());
         String c = r.getCreatedDate() + "";
        builderr.add("created", c);

       JsonObject jsonObject = builderr.build();
       out.print(jsonObject.toString());
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
