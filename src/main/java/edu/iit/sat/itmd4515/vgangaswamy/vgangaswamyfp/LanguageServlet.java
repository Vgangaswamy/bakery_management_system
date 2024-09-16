package edu.iit.sat.itmd4515.vgangaswamy.vgangaswamyfp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet(name = "LanguageServlet", urlPatterns = {"/language", "/lang", "/l"})
public class LanguageServlet extends HttpServlet {
    // Corrected Logger capitalization
    private static final Logger LOG = Logger.getLogger(LanguageServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Correct LOG.info usage
        LOG.info("Inside LanguageServlet.doGet()");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Correct LOG.info usage
        LOG.info("Inside LanguageServlet.doPost()");

        String language_idParam = req.getParameter("langId");
        String nameParam = req.getParameter("name");

        LOG.info("langId:\t\t\t" + language_idParam);
        LOG.info("name:\t\t\t" + nameParam);

    }
}
