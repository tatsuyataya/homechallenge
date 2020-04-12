package controllers.good;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class GoodOff
 */
@WebServlet("/goodoff")
public class GoodOff extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoodOff() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();
		
		User u = (User)request.getSession().getAttribute("login_user");
		
		int p_id = Integer.parseInt(request.getParameter("good_post"));
		Post p = em.find(Post.class, p_id);
		List<Good> g = em.createNamedQuery("goodKaijo", Good.class)
						 .setParameter("good_from",u)
						 .setParameter("good_post",p)
						 .getResultList();
		
		em.getTransaction().begin();
		for(Good e : g)em.remove(e);
		em.getTransaction().commit();
		em.close();
		
		response.sendRedirect(request.getContextPath() + "/posts/show?id=" + p_id);
		
	}

}
