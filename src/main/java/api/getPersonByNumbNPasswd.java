/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import java.io.IOException;
import models.*;
import java.io.PrintWriter;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bayan
 */
public class getPersonByNumbNPasswd extends HttpServlet {

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

        PrintWriter out = response.getWriter();

        JsonReader jsonReader = Json.createReader(request.getReader());

        JsonObject jsonObject = jsonReader.readObject();

        String number = jsonObject.getString("numb");
        String passwd = jsonObject.getString("passwd");

        Person p = new Person();
        p.setNumber(number);
        p.setPasswd(passwd);

        models.DbHelper db = new models.DbHelper();
        Person person = db.getPersonByPasswdNNumb(number, passwd);

        if (person == null) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder().add("errorMessage", "404error");
            String result = objectBuilder.build().toString();
            out.print(result);
            return;
        }
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("id", person.getId());
        objectBuilder.add("name", person.getName());
        objectBuilder.add("lastname", person.getLastname());
        objectBuilder.add("number", person.getNumber());
        objectBuilder.add("rating", person.getRating());
        String c = person.getCreatedDate() + "";
        objectBuilder.add("created", c);
        String b = person.getBirthday() + "";
        objectBuilder.add("birth", b);
        JsonObject jsonObject2 = objectBuilder.build();
        out.print(jsonObject2.toString());
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
