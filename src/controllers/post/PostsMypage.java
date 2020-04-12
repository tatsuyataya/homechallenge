package controllers.post;

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
 * Servlet implementation class PostsMypage
 */
@WebServlet("/posts/mypage")
public class PostsMypage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostsMypage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.createEntityManager();
		
		int page;
		try{
			page = Integer.parseInt(request.getParameter("page"));
		} catch(Exception e) {
			page = 1;
		}
		List<Post> posts = em.createNamedQuery("getMyPosts", Post.class)
				.setParameter("login_user", request.getSession().getAttribute("login_user"))
                .setFirstResult(15 * (page - 1))
                .setMaxResults(15)
                .getResultList();
		
		long posts_count = (long)em.createNamedQuery("getMyPostsCount", Long.class)
									.setParameter("login_user", request.getSession().getAttribute("login_user"))
						            .getSingleResult();
		
		em.close();
		
		request.setAttribute("posts", posts);
        request.setAttribute("posts_count", posts_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        if(request.getSession().getAttribute("flush_create") != null){
        	request.setAttribute("flush_create",request.getSession().getAttribute("flush_create"));
        	request.getSession().removeAttribute("flush_create");
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/gamen/mypage.jsp");
        rd.forward(request, response);
        }

}
