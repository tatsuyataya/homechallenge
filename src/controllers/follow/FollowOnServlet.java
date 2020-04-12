package controllers.follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class FollowOnServlet
 */
@WebServlet("/follow/on")
public class FollowOnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowOnServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();
		
		User follower = (User)request.getSession().getAttribute("login_user");
		int followed_id = Integer.parseInt(request.getParameter("writer"));
		User followed = em.find(User.class, followed_id);
		
		Follow f = new Follow();
		f.setFollower(follower);
		f.setFollowed(followed);
		
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
		em.close();
		
		
		response.sendRedirect(request.getContextPath() + "/posts/show?id=" + Integer.parseInt(request.getParameter("post")));
	}

}
