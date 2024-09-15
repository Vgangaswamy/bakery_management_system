package edu.iit.sat.itmd4515.vgangaswamy.vgangaswamyfp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "DemoServlet", value = "/demo-servlet")
public class DemoServlet extends HttpServlet {
    private String message;
    private static final Logger LOG = Logger.getLogger(DemoServlet.class.getName());

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("https://www.iit.edu");

        // Hello
        LOG.log(Level.INFO, "I am info message from doGet method within Demo Servlet");
        LOG.log(Level.FINEST, "I am finest message from doGet method within Demo Servlet");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        LOG.log(Level.INFO, "I am info message from doPost method within Demo Servlet");
    }

    public void destroy() {
    }
}
