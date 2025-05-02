import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO: Implement login logic
        // 1. Get username & password from request
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 2. Validate credentials (hardcode a few users)
        Map<String, String> users = new HashMap<>();
        users.put("Nuwani", "nuwani123");
        users.put("Tharindu", "hhhh666");


        // 3. If valid:
        //    - Create session
        //    - Store username in cookie
        //    - Redirect to DashboardServlet
        // 4. If invalid, redirect back to login.html
        if (users.containsKey(username) && users.get(username).equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);

            Cookie userCookie = new Cookie("username", username);
            userCookie.setMaxAge(60 * 60);
            response.addCookie(userCookie);

            response.sendRedirect("DashboardServlet");
        } else {
            response.sendRedirect("login.html");
        }



    }
}