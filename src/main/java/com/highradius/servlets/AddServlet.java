package com.highradius.servlets;

import com.highradius.implementation.InvoiceDao;
import com.highradius.implementation.InvoiceDaoImpl;
import com.highradius.model.Invoice;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/add")
public class AddServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InvoiceDao invoiceDao;

    @Override
    public void init() throws ServletException {
        super.init();
        invoiceDao = new InvoiceDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Retrieve data from the request parameters
    	int slNo = Integer.parseInt(req.getParameter("slNo"));
        int customerOrderID = Integer.parseInt(req.getParameter("customerOrderID"));
        int salesOrg = Integer.parseInt(req.getParameter("salesOrg"));
        String distributionChannel = req.getParameter("distributionChannel");
        int companyCode = Integer.parseInt(req.getParameter("companyCode"));
        String orderCreationDate = req.getParameter("orderCreationDate");
        String orderCurrency = req.getParameter("orderCurrency");
        double amountInUSD = Double.parseDouble(req.getParameter("amountInUSD"));
        double orderAmount = Double.parseDouble(req.getParameter("orderAmount"));
        String division = req.getParameter("division");
        double releasedCreditValue = Double.parseDouble(req.getParameter("releasedCreditValue"));
        String purchaseOrderType = req.getParameter("purchaseOrderType");
        String orderCreationTime = req.getParameter("orderCreationTime");
        String creditControlArea = req.getParameter("creditControlArea");
        int soldToParty = Integer.parseInt(req.getParameter("soldToParty"));
        String requestedDeliveryDate = req.getParameter("requestedDeliveryDate");
        String creditStatus = req.getParameter("creditStatus");
        int customerNumber = Integer.parseInt(req.getParameter("customerNumber"));
        String uniqueCustID = req.getParameter("uniqueCustID");

        // Create an Invoice object with the retrieved data
        Invoice invoice = new Invoice(slNo,customerOrderID, salesOrg, distributionChannel, division, releasedCreditValue,
                purchaseOrderType, companyCode, orderCreationDate, orderCreationTime, creditControlArea,
                soldToParty, orderAmount, requestedDeliveryDate, orderCurrency, creditStatus, customerNumber,
                amountInUSD, uniqueCustID);

        // Add the invoice to the database
        invoiceDao.insertInvoice(invoice);

        // Set the response type to JSON
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // Create a JSON response
        Gson gson = new Gson();
        String json = gson.toJson("Success");

        // Send the JSON response
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}
