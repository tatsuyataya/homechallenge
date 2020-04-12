package controllers.favorite;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Follow;
import models.Good;
import models.User;
import utils.DBUtil;

/**
 * Servlet implementation class FavoriteServlet
 */
@WebServlet("/favorite")
public class FavoriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoriteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lu_id;
		User lu = new User();
		try {
			lu = (User)request.getSession().getAttribute("login_user");
			lu_id = lu.getId();
		}catch (Exception ex){
			lu_id = -1;
		}
		
		int _token = Integer.parseInt(request.getParameter("id"));
		
		int page;
		try{
			page = Integer.parseInt(request.getParameter("page"));
		} catch (Exception ex){
			page = 1;
		}
		
		
		if(_token != 0 && _token == lu_id){
			EntityManager em = DBUtil.createEntityManager();
			
			
			List<Good> good_post = em.createNamedQuery("getMyFavoritePost",Good.class)
										 .setParameter("me",lu)
										 .getResultList();
			
			int favorite_post_count = good_post.size();
			
			List<Follow> follower = em.createNamedQuery("getFollowMe",Follow.class)
									  .setParameter("me",lu)
									  .getResultList();
			
			int follower_count = follower.size();
			
			List<Follow> followed = em.createNamedQuery("getMyFollowed",Follow.class)
									  .setParameter("me",lu)
									  .getResultList();
			
			int followed_count = followed.size();
			
			request.setAttribute("fp", good_post);
			request.setAttribute("fp_count", favorite_post_count);
			request.setAttribute("follower", follower);
			request.setAttribute("followed", followed);
			request.setAttribute("follower_count", follower_count);
			request.setAttribute("followed_count", followed_count);
			request.setAttribute("page", page);
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/gamen/favorite.jsp");
			rd.forward(request, response);
		}
		
	}

}
