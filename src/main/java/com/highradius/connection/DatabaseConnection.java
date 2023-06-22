package com.highradius.connection;

import com.highradius.model.Invoice;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
  private String url = "jdbc:mysql://localhost:3306/oap_h2h";
  private String username = "root";
  private String password = "Nikhil@123";

  public List < Invoice > getInvoices() {
    List < Invoice > invoices = new ArrayList < > ();

    String sql = "SELECT * FROM h2h_oap LIMIT 1000";
    try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    try (Connection connection = DriverManager.getConnection(url, username, password); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {


        while (resultSet.next()) {
        	int slNo = resultSet.getInt("Sl_no");
            int customerOrderID = resultSet.getInt("CUSTOMER_ORDER_ID");
            int salesOrg = resultSet.getInt("SALES_ORG");
            String distributionChannel = resultSet.getString("DISTRIBUTION_CHANNEL");
            String division = resultSet.getString("DIVISION");
            double releasedCreditValue = resultSet.getDouble("RELEASED_CREDIT_VALUE");
            String purchaseOrderType = resultSet.getString("PURCHASE_ORDER_TYPE");
            int companyCode = resultSet.getInt("COMPANY_CODE");
            String orderCreationDate = resultSet.getString("ORDER_CREATION_DATE");
            String orderCreationTime = resultSet.getString("ORDER_CREATION_TIME");
            String creditControlArea = resultSet.getString("CREDIT_CONTROL_AREA");
            int soldToParty = resultSet.getInt("SOLD_TO_PARTY");
            double orderAmount = resultSet.getDouble("ORDER_AMOUNT");
            String requestedDeliveryDate = resultSet.getString("REQUESTED_DELIVERY_DATE");
            String orderCurrency = resultSet.getString("ORDER_CURRENCY");
            String creditStatus = resultSet.getString("CREDIT_STATUS");
            int customerNumber = resultSet.getInt("CUSTOMER_NUMBER");
            double amountInUSD = resultSet.getDouble("AMOUNT_IN_USD");
            String uniqueCustID = resultSet.getString("UNIQUE_CUST_ID");

            Invoice invoice = new Invoice(slNo,customerOrderID, salesOrg, distributionChannel, division, releasedCreditValue,
                    purchaseOrderType, companyCode, orderCreationDate, orderCreationTime, creditControlArea,
                    soldToParty, orderAmount, requestedDeliveryDate, orderCurrency, creditStatus, customerNumber,
                    amountInUSD, uniqueCustID);
            invoices.add(invoice);
        }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return invoices;
  }

public void addInvoice(Invoice invoice) {
	
    try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    String sql = "INSERT INTO h2h_oap (Sl_no,CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, DIVISION, RELEASED_CREDIT_VALUE, " +
                 "PURCHASE_ORDER_TYPE, COMPANY_CODE, ORDER_CREATION_DATE, ORDER_CREATION_TIME, CREDIT_CONTROL_AREA, " +
                 "SOLD_TO_PARTY, ORDER_AMOUNT, REQUESTED_DELIVERY_DATE, ORDER_CURRENCY, CREDIT_STATUS, CUSTOMER_NUMBER, " +
                 "AMOUNT_IN_USD, UNIQUE_CUST_ID) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (Connection connection = DriverManager.getConnection(url, username, password);
         PreparedStatement statement = connection.prepareStatement(sql)) {

    	statement.setInt(1, invoice.getSlNo());
        statement.setInt(2, invoice.getCustomerOrderID());
        statement.setInt(3, invoice.getSalesOrg());
        statement.setString(4, invoice.getDistributionChannel());
        statement.setString(5, invoice.getDivision());
        statement.setDouble(6, invoice.getReleasedCreditValue());
        statement.setString(7, invoice.getPurchaseOrderType());
        statement.setInt(8, invoice.getCompanyCode());
        statement.setString(9, invoice.getOrderCreationDate());
        statement.setString(10, invoice.getOrderCreationTime());
        statement.setString(11, invoice.getCreditControlArea());
        statement.setInt(12, invoice.getSoldToParty());
        statement.setDouble(13, invoice.getOrderAmount());
        statement.setString(14, invoice.getRequestedDeliveryDate());
        statement.setString(15, invoice.getOrderCurrency());
        statement.setString(16, invoice.getCreditStatus());
        statement.setInt(17, invoice.getCustomerNumber());
        statement.setDouble(18, invoice.getAmountInUSD());
        statement.setString(19, invoice.getUniqueCustID());

        statement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void deleteInvoice(int slNo) {
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }

    String sql = "DELETE FROM h2h_oap WHERE Sl_no = ?";

    try (Connection connection = DriverManager.getConnection(url, username, password);
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, slNo);

        statement.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}



}