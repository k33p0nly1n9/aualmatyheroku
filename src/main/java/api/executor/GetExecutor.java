/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api.executor;

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
public class GetExecutor extends HttpServlet {

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
       Executor r = db.getExecutor(id);

        if (r == null) {
            javax.json.JsonObjectBuilder objectBuilder = javax.json.Json.createObjectBuilder().
                    add("Ошибка", "Специалист не найден");
            out.print(objectBuilder.build().toString());
            return;
        }

        JsonObjectBuilder builderr = Json.createObjectBuilder();

        builderr.add("id", r.getId());
        builderr.add("pId", r.getPersonId());
        builderr.add("cId",r.getSectionId());
        builderr.add("spec",r.getSpecialztn());
         builderr.add("desc",r.getDescriptn());
         
         
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Service s : r.getServices()) {
            JsonObjectBuilder servicesBuilder = 
                    Json.createObjectBuilder()
                    .add("sId", s.getId())
                     .add("sTitle", s.getTitle())
                    .add("sPrice", s.getPrice());

            arrayBuilder.add(servicesBuilder);
        }

        builderr.add("services", arrayBuilder);

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
