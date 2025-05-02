import src.Course;

import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EnrollServlet")
public class EnrollServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // TODO: Implement enrollment logic
        // 1. Get courseId from URL parameter
        String courseId = request.getParameter("courseId");
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("login.html");
            return;
        }

        // 2. Get current user's session
        List<Course> allCourses = new ArrayList<>();
        allCourses.add(new Course("101", "Java Programming", "Dr. Silva"));
        allCourses.add(new Course("102", "Web Development", "Prof. Fernando"));
        allCourses.add(new Course("103", "Database Systems", "Dr. Perera"));

        // 3. Add course to enrolled list in session
        Course selectedCourse = null;
        for (Course c : allCourses) {
            if (c.getId().equals(courseId)) {
                selectedCourse = c;
                break;
            }
        }

        // 4. Redirect back to DashboardServlet
        if (selectedCourse != null) {
            List<Course> enrolledCourses = (List<Course>) session.getAttribute("enrolledCourses");
            if (enrolledCourses == null) {
                enrolledCourses = new ArrayList<>();
            }

            if (!enrolledCourses.contains(selectedCourse)) {
                enrolledCourses.add(selectedCourse);
                session.setAttribute("enrolledCourses", enrolledCourses);
            }
        }

        response.sendRedirect("DashboardServlet");
    }
}