package lesson16;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models16.Task;
import utils16.DBUtil16;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet16 extends HttpServlet {
        private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet16() {
        super();
    }

        /**
         * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
         */
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            EntityManager em = DBUtil16.createEntityManager();

            List<Task> tasks = em.createNamedQuery("getAllMessages", Task.class).getResultList();

            em.close();

            request.setAttribute("tasks", tasks);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views16/tasks/index.jsp");
            rd.forward(request, response);
//            response.getWriter().append(Integer.valueOf(messages.size()).toString());
//            em.close();
        }
}
