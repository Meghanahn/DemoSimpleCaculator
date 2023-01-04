package com.test;
import java.io.*;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalculatorServlet extends HttpServlet
{
private static final long serialVersionUID = 1L;
public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
   response.setContentType("text/html;charset=UTF-8");
   PrintWriter out = response.getWriter();

	double n1 = Double.parseDouble(request.getParameter("no1"));
	double n2 = Double.parseDouble(request.getParameter("no2"));
	String opr=request.getParameter("opr");
	
	double result =0;
		if(opr.equals("add")) {
		   result=n1+n2;
		 }
		if(opr.equals("sub")) {
		  result=n1-n2;
		  }
		if(opr.equals("mul")) {
		  result=n1*n2;
		  }
		if(opr.equals("div")) {
		  result=n1/n2;
		  }
		try {
	        Connection con = DatabaseConnection.initializeDatabase();
	        PreparedStatement st = con.prepareStatement("INSERT INTO CALCULATION_RESULT values (?,?,?,?,?)");
	        st.setDouble(1, n1);
	        st.setDouble(2, n2);
	        st.setString(3, opr);
	        st.setDouble(4, result);
	        
	        java.util.Date date = new java.util.Date();
	        st.setString(5, date.toString());
	
	        st.executeUpdate();
	
	        st.close();
	        con.close();
		   }
		 catch (Exception e) {
	            e.printStackTrace();
	           }
		
	out.println("<html><head><title>Servlet CalculatorServlet</title></head><body>");	
	out.println("<h1> Result = "+result);
	out.println("</body></html>");
	
	
	
} 
}
