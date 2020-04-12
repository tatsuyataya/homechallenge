package controllers.users;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/users/login")
public class UsersLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("_token", request.getSession().getId());
		request.setAttribute("haserror", false);
		if(request.getSession().getAttribute("flush") != null){
			request.setAttribute("flush", request.getSession().getAttribute("flush"));
			request.getSession().removeAttribute("flush");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/gamen/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean check_result = false;
		
		String name = request.getParameter("name");
		String plain_pass = request.getParameter("password");
		
		User u = null;
		
		if(name != null && !name.equals("") && plain_pass != null && !plain_pass.equals("")){
			EntityManager em = DBUtil.createEntityManager();
			
			String password = EncryptUtil.getPasswordEncrypt(plain_pass,
							(String)this.getServletContext().getAttribute("salt")
							);
			System.out.println(name);
			System.out.println(plain_pass);
			System.out.println(password);
			
			try {
				u = em.createNamedQuery("checkLoginCodeAndPassword",User.class)
					  .setParameter("name", name)
					  .setParameter("password", password)
					  .getSingleResult();
			} catch(NoResultException ex){}
			
			em.close();
			
			if(u != null){
				check_result = true;
			}
		}
		
		System.out.println(check_result);
			
		if(!check_result){
			request.setAttribute("_token", request.getSession().getId());
			request.setAttribute("hasError", true);
			request.setAttribute("name", name);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/gamen/login.jsp");
			rd.forward(request, response);
		} else{
			request.getSession().setAttribute("login_user",u);
			
			request.getSession().setAttribute("flush", "ログインしました。");
			response.sendRedirect(request.getContextPath() + "/toppage");
		}
	}

}
