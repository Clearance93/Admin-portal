package com.admin.management.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.usermanager.bean.User;
import com.usermanager.dao.UserDao;

/**
 * Servlet implementation class UserServelt
 */
@WebServlet("/")
public class UserServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UserDao userDao;
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public UserServelt() {
//        super();
//        // TODO Auto-generated constructor stub
//    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDao();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		switch(action) {
			case "/new":
				showNewForm(request, response);
				break;
				
			case "/insert":
				try {
					insertUser(request, response);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case "/delete":
				try {
					deleteUser(request, response);
				} catch (IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case "/edit":
				try {
					showEditForm(request, response);
				} catch (ServletException | IOException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case "/update":
				try {
					updateUser(request, response);
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}
				break;
				
			default: 
				try {
					listUser(request, response);
				} catch (SQLException | IOException e) {
					e.printStackTrace();
				}
				break;
		}
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispacher = request.getRequestDispatcher("user-form.jsp");
		dispacher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(firstName, lastName, email, country);
		
		userDao.insertUser(newUser);
		response.sendRedirect("list");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		try {
			userDao.deleteUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		response.sendRedirect("list");
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		User existingUser;
		try {
			existingUser = userDao.selectUser(id);
			RequestDispatcher dispatch = request.getRequestDispatcher("user-form.jsp");
			request.setAttribute("user", existingUser);
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
	{
		int id = Integer.parseInt(request.getParameter("id"));
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		
		User user = new User(id, firstName, lastName, email, country);
		userDao.updateUser(user);
		response.sendRedirect("list");
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException 
	{
		try {
			List<User> listUser = userDao.selectAllUsers();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatch = request.getRequestDispatcher("user-list.jsp");
			dispatch.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
