package controllers.good;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Good;
import models.Post;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class GoodOn
 */
@WebServlet("/goodon")
public class GoodOn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodOn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();
		
		User gf = (User)request.getSession().getAttribute("login_user");
		String post_id = request.getParameter("good_post");
		
		Post post = em.find(Post.class, Integer.parseInt(post_id));
		
		Good g = new Good();
		
		g.setGood_from(gf);
		g.setGood_post(post);
		
		em.getTransaction().begin();
		em.persist(g);
		em.getTransaction().commit();
		em.close();
		
		response.sendRedirect(request.getContextPath() + "/posts/show?id=" + post.getId());
	}

}
