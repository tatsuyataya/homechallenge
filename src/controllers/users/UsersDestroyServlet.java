package controllers.users;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Post;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class UsersDestroyServlet
 */
@WebServlet("/users/destroy")
public class UsersDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		
		if(_token != null && _token.equals(request.getSession().getId()));;
			EntityManager em = DBUtil.createEntityManager();
			
			User du = (User)request.getSession().getAttribute("login_user");
			User u = em.find(User.class, du.getId());
			
			List<Post> posts = em.createNamedQuery("getAllDestroyPost", Post.class)
					.setParameter("destroy_writer", u)
	                .getResultList();
			
			em.getTransaction().begin();
			for (Post e : posts) em.remove(e);
			em.remove(u);
			em.getTransaction().commit();
			em.close();
			
			request.getSession().removeAttribute("user_id");
			request.getSession().removeAttribute("login_user");
			
			request.getSession().setAttribute("flush", "退会が完了しました。");
			
			response.sendRedirect(request.getContextPath() + "/toppage");
	}

}
