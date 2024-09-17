package edu.iit.sat.itmd4515.vgangaswamy.vgangaswamyfp;

import domain.Language;
import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.valueOf;
import static java.sql.DriverManager.getConnection;
import static java.util.logging.Level.*;

@WebServlet(name = "LanguageServlet", urlPatterns = {"/language", "/lang", "/l", "/vgangaswamy-fp/language"})
public class LanguageServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(LanguageServlet.class.getName());
    @Resource
    Validator validator;
    @Resource(name = "java:app/jdbc/itmd4515DS")
    DataSource ds ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Inside LanguageServlet.doGet()");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.info("Inside LanguageServlet.doPost()");

        String language_idParam = req.getParameter("langId");
        String nameParam = req.getParameter("name");


        LOG.info("langId received: " + language_idParam);
        LOG.info("nameParam received: " + nameParam);

        Language language = new Language();


        if (language_idParam != null && !language_idParam.isBlank()) {
            language.setLanguageId(Integer.parseInt(language_idParam));
        }

        if (nameParam != null && !nameParam.isBlank()) {
            language.setName(nameParam);
        }

        LOG.info("Built Language: " + language.toString());


        Set<ConstraintViolation<Language>> violations = validator.validate(language);


        if (!violations.isEmpty()) {
            LOG.info("User failed validation:");
            for (ConstraintViolation<Language> violation : violations) {
                LOG.info(violation.getPropertyPath() + ": " + violation.getMessage());
            }

            req.setAttribute("language",language);
            req.setAttribute("violations",violations);

            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);

        } else {
            LOG.info("User passed validation:");

            try {
                createALanguage(language);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            req.setAttribute("language",language);

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/conf.jsp");
            rd.forward(req, resp);

        }
    }

    private void createALanguage(Language language) throws SQLException {
        String insertLanguage = "insert into language" + "(language_id, name)"
                + " values( ?, ?)";
        try (
                Connection c = ds.getConnection();
                PreparedStatement ps = c.prepareCall(insertLanguage)){
            ps.setInt(1,language.getLanguageId());
            ps.setString(2,language.getName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            LOG.log(SEVERE, null, ex);
        }
    }

}
