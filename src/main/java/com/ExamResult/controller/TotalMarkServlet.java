package com.ExamResult.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class TotalMarkServlet
 */
@WebServlet("/TotalMarkServlet")
public class TotalMarkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalMarkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//accessing print writer and setting content type
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		try {
		
		//database coonection	
		dbconnection db = new dbconnection();
		
		//sql query
		String qry = "SELECT CONCAT(FirstName,' ',LastName) AS StudentName, EC1,EC2,EC3,EC4,EC5,"
				+ "sum(EC1+EC2+EC3+EC4+EC5) AS Total FROM student_details S join results R "
				+ " ON S.Student_Id = R.Student_Id GROUP BY EC1,EC2,EC3,EC4,EC5";
		ResultSet rs = db.select(qry);
		out.println("<table border = 1 width = 50% height = 50%>");
		out.println("<tr><th>Name</th><th>EC1</th><th>EC2</th><th>EC3</th><th>EC4</th><th>EC5</th><th>Total</th>");
		while(rs.next()) {
			String name = rs.getString("StudentName");
			String ec1 = rs.getString("EC1");
			String ec2 = rs.getString("EC2");
			String ec3 = rs.getString("EC3");
			String ec4 = rs.getString("EC4");
			String ec5 = rs.getString("EC5");
			String total = rs.getString("Total");
			
		//assigning to the table structure	
		out.println("<tr><td>"+name+" </td><td>"+ec1+" </td> <td>"+ec2+"</td> <td>"+ec3+"</td><td>"+ec4+"</td> <td>"+ec5+"</td><td>"+total+"</td></tr>");	
		}
		out.println("</table>");
//		out.println("</html></body>");
	}
		catch(Exception E) {
			
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
