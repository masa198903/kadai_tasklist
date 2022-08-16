package lesson16;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models16.Task;
import utils16.DBUtil16;

/**
 * Servlet implementation class NewServlet16
 */
@WebServlet(name = "new", urlPatterns = { "/new" })
public class NewServlet16 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewServlet16() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil16.createEntityManager();
        em.getTransaction().begin();

        Task t = new Task();

        String title = "taro";
        t.setTitle(title);

        String content = "hellow";
        t.setContent(content);

        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        t.setCreated_at(currentTime);
        t.setUpdated_at(currentTime);

        // データベースに保存
        em.persist(t);
        em.getTransaction().commit();

        // 自動採番されたIDの値を取得
        response.getWriter().append(Integer.valueOf(t.getId()).toString());

        em.close();
    }
}
