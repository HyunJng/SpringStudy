package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseHtmlServlet", urlPatterns = "/response-html")
public class ResponseHtmlServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Content-Type: text/html;charset=utf-8;
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        
        response.getWriter().print("<html>");
        response.getWriter().print("<body>");
        response.getWriter().print("<h1>안녕</h1>");
        response.getWriter().print("</body>");
        response.getWriter().print("</html>");

    }
}
