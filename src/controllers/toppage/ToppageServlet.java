package controllers.toppage;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Post;
import utils.DBUtil;

/**
 * Servlet implementation class ToppageServlet
 */
@WebServlet("/toppage")
public class ToppageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToppageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().getAttribute("login_id");
		
		EntityManager em = DBUtil.createEntityManager();
		
		int page;
		try{
			page = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			page = 1;
		}
		List<Post> posts = em.createNamedQuery("getAllPosts", Post.class)
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();
		
		long posts_count = (long)em.createNamedQuery("getAllPostsCount", Long.class)
						            .getSingleResult();
		
		em.close();
		
		request.setAttribute("posts", posts);
		request.setAttribute("posts_count", posts_count);
		request.setAttribute("page", page);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/topPage/toppage.jsp");
		rd.forward(request, response);
	}

}
