package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dal.OrderDAO;

@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try(PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if(id != null) {
				OrderDAO orderDAO = new OrderDAO();
				orderDAO.cancelOrder(Integer.parseInt(id));
                                orderDAO.closeConnection();
			}
			response.sendRedirect(UrlConstant.ORDERED_URL);
		}
                catch(Exception e){
                    e.printStackTrace();
                }
            // TODO Auto-generated catch block
            	}

}
