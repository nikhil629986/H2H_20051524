package com.highradius.servlets;

import com.highradius.connection.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateInvoice")
public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DatabaseConnection databaseConnection;

    @Override
    public void init() throws ServletException {
        super.init();
        databaseConnection = new DatabaseConnection();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int slNo = Integer.parseInt(req.getParameter("slNo"));

        // Delete existing invoice data
        databaseConnection.deleteInvoice(slNo);

        // Redirect to the AddInvoice.html page with pre-filled slNo parameter
        resp.sendRedirect(req.getContextPath() + "/addInvoice.html?slNo=" + slNo);
    }
}