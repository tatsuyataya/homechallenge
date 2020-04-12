package controllers.users;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.validators.UserValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class UserCreateServlet
 */
@WebServlet("/users/create")
public class UsersCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsersCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _token = (String)request.getParameter("_token");
		if(_token != null && _token.equals(request.getSession().getId())){
			EntityManager em = DBUtil.createEntityManager();
			
			User u = new User();
			
			u.setName(request.getParameter("name"));
            u.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );
            int gender;
            try{
            gender = Integer.parseInt(request.getParameter("gender"));
            }catch (Exception e){
            	gender = 0;
            }
            u.setGender(gender);
			u.setAge(Integer.parseInt(request.getParameter("age")));
			Timestamp currentTime = new Timestamp(System.currentTimeMillis());
			u.setRegistration(currentTime);
			u.setUpdate_at(currentTime);
		
			List<String> errors = UserValidator.validate(u,true,true,true);
			if(errors.size() > 0){
				em.close();
				
				request.setAttribute("_token", request.getSession().getId());
				request.setAttribute("user", u);
				request.setAttribute("errors", errors);
				
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/gamen/new.jsp");
				rd.forward(request, response);
			} else {
				em.getTransaction().begin();
				em.persist(u);
				em.getTransaction().commit();
				em.close();
				request.getSession().setAttribute("flush", "登録が完了しました。");
				
				response.sendRedirect(request.getContextPath() + "/toppage");
			}
			
		}
	}

}
