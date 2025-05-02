import src.Course;

import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO: Implement dashboard logic
        // 1. Check if user is logged in (session)
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        String username = (String) session.getAttribute("username");
        request.setAttribute("username", username);

        // 2. Create a list of courses (hardcoded)
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("101", "Java Programming", "Dr. Silva"));
        courses.add(new Course("102", "Web Development", "Prof. Fernando"));
        courses.add(new Course("103", "Database Systems", "Dr. Perera"));
        request.setAttribute("courses", courses);

        // 3. Store courses in request attribute
        // 4. Forward to dashboard.jsp
        List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
        if (enrolledCourses == null) {
            enrolledCourses = new ArrayList<>();
        }
        request.setAttribute("enrolledCourses", enrolledCourses);

        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}