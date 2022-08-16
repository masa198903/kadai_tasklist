package lesson16;

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

import models16.Task;
import models16.validators.TaskValidators;
import utils16.DBUtil16;

/**
 * Servlet implementation class CreateServlet16
 */
@WebServlet(name = "create", urlPatterns = { "/create" })
public class CreateServlet16 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateServlet16() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil16.createEntityManager();
            em.getTransaction().begin();

            Task t = new Task();

            String title = request.getParameter("title");
            t.setTitle(title);

            String content = request.getParameter("content");
            t.setContent(content);

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            t.setCreated_at(currentTime);
            t.setUpdated_at(currentTime);

            // バリデーションを実行してエラーがあったら新規登録のフォームに戻る
            List<String> errors = TaskValidators.validate(t);
            if(errors.size() > 0) {
                em.close();

                // フォームに初期値を設定、さらにエラーメッセージを送る
                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("task", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views16/tasks/new.jsp");
                rd.forward(request, response);
            } else {
                // データベースに保存
                em.persist(t);
                em.getTransaction().commit();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                em.close();

                // indexのページにリダイレクト
                response.sendRedirect(request.getContextPath() + "/index");
            }

//            em.persist(t);
//            em.getTransaction().commit();
//            request.getSession().setAttribute("flush", "登録が完了しました。");
//            em.close();
//
//            response.sendRedirect(request.getContextPath() + "/index");

        }
    }

}
