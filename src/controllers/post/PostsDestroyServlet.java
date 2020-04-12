package controllers.post;

import java.io.IOException;

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
 * Servlet implementation class PostsDestroyServlet
 */
@WebServlet("/posts/destroy")
public class PostsDestroyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostsDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String _token = (String)request.getParameter("_token");
    	System.out.println("うううう" + _token);
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();
            
            System.out.println("ああああ");
            
            Post p = em.find(Post.class, Integer.parseInt(request.getParameter("id")));
            
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            em.close();
            
            request.getSession().removeAttribute("post_id");
            
            User lu = (User)request.getSession().getAttribute("login_user");
            int lu_id = lu.getId();
            response.sendRedirect(request.getContextPath() + "/posts/mypage?id=" + lu_id);
        }
    }

}
