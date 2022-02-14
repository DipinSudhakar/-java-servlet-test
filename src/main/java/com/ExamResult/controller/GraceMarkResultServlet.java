package com.ExamResult.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GraceMarkResultServlet
 */
@WebServlet("/GraceMarkResultServlet")
public class GraceMarkResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraceMarkResultServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//setting printwriter and content type
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			int grace = 6;
		try {
			
		//database connection	
			dbconnection db = new dbconnection();
			
		
			//sql query
			String qry = "SELECT CONCAT(FirstName,' ',LastName) AS StudentName, EC1,EC2,EC3,EC4,EC5,sum((EC1) "
					+ "+(EC2)+(EC3)+(EC4)+(EC5)) AS Total, CASE\r\n"
					+ "	    WHEN EC1 < 30 THEN 'FAIL'\r\n"
					+ "            WHEN EC2 < 30 THEN 'FAIL'\r\n"
					+ "            WHEN EC3 < 30 THEN 'FAIL'\r\n"
					+ "            WHEN EC4 < 30 THEN 'FAIL'\r\n"
					+ "            WHEN EC5 < 30 THEN 'FAIL'\r\n"
					+ "            ELSE 'PASS'\r\n"
					+ "            END AS Result\r\n"
					+ "FROM student_details S join results R \r\n"
					+ "ON S.Student_Id = R.Student_Id GROUP BY EC1,EC2,EC3,EC4,EC5";
			
		
			
			ResultSet rs = db.select(qry);
			out.println("<table border = 1 width = 50% height = 50%>");
			out.println("<tr><th>Name</th><th>EC1</th><th>EC2</th><th>EC3</th><th>EC4</th><th>EC5</th><th>Total</th><th>Result</th>");
		
			//accessing values from database	
			while(rs.next()) {
				String name = rs.getString("StudentName");
				int ec1 = Integer.parseInt( rs.getString("EC1"));
				int ec2 = Integer.parseInt(rs.getString("EC2"));
				int ec3 = Integer.parseInt(rs.getString("EC3"));
				int ec4 = Integer.parseInt( rs.getString("EC4"));
				int ec5 = Integer.parseInt( rs.getString("EC5"));
				String total = rs.getString("Total");
				String result =rs.getString("Result");
			
			//updating with grace marks	
				
				if(ec1 < 30 && grace > 0) {
					ec1 = ec1+(30 - ec1);
					grace = grace - (30 - ec1);
				}
					else if(ec2 < 30 && grace > 0) {
						ec2 = ec2+(30-ec2);
						grace = grace - (30 - ec1);
					}
						else if(ec3 < 30 && grace > 0 ) {
							ec3 = ec3 + (30 - ec3);
							grace = grace - (30 - ec3);	
						}
							else if(ec4 < 30 && grace > 0 ) {
								ec4 = ec4 + (30 -ec4);
								grace = grace -(30 - ec4);
							} 
								else if(ec5 < 30 && grace > 0) {
									ec5 = ec5 + (30 - ec5);
									grace = grace - (30 - ec5);
								} 
						
				if(ec1 < 30) {
					result = "fail";
				}
					else if(ec2 < 30) { 
						result = "fail";
					}
						else if(ec3 < 30) {
							result = "fail";
						}
							else if(ec4 < 30) {
								result = "fail";
							}
							else if(ec5 < 30) {
								result = "fail";	
							}
				else {
					result = "Pass";
				}
				
				
			//assigning to the table structure	
				out.println("<tr><td>"+name+" </td><td>"+ec1+" </td> <td>"+ec2+"</td> <td>"+ec3+"</td><td>"+ec4+"</td> "
						+ "<td>"+ec5+"</td> <td>"+total+"</td><td>"+result+"</td></tr>");	
			}
			out.println("</table>");
//			out.println("</html></body>");
			
		}
		
		catch(Exception e) {
			
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
