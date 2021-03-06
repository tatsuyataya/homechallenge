package controllers.post;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Post;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class PostsShowServlet
 */
@WebServlet("/posts/show")
public class PostsShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostsShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();
		
		int i = Integer.parseInt(request.getParameter("id"));
		
		Post p = em.find(Post.class, i);
		
		User u = p.getWriter();
		
		long good_check = (long)em.createNamedQuery("checkGoodOrNot",Long.class)
								   .setParameter("good_from", (User)request.getSession().getAttribute("login_user"))
								   .setParameter("good_post", p)
								   .getSingleResult();
		
		
		
		long follow_check = (long)em.createNamedQuery("checkFollowOrNot",Long.class)
									  .setParameter("follower",(User)request.getSession().getAttribute("login_user"))
									  .setParameter("followed", u)
									  .getSingleResult();          
		
		em.close();
				
		request.setAttribute("post", p);
		request.setAttribute("_token", request.getSession().getId());
		request.setAttribute("good_check",good_check);
		request.setAttribute("follow_check",follow_check);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/gamen/postshow.jsp");
		rd.forward(request, response);
	}

}
