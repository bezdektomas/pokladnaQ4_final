package servlets;

import java.io.IOException;

import org.mindrot.jbcrypt.BCrypt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Uzivatel;

public class LoginServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout") != null) {
            // provádíme odhlášení
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.removeAttribute("loggedUser"); // PHP: unset($_SESSION['loggedUser'])
                session.invalidate();                  // PHP: session_destroy()
            }
            resp.sendRedirect("/demo/");
            return;
        }

        //zobrazujeme přihlašovací formulář

        getServletContext().getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("login") == null || req.getParameter("login").isBlank() || req.getParameter("heslo") == null || req.getParameter("heslo").isBlank()) {
            resp.sendError(400);
            return;
        }

        String login = req.getParameter("login").trim();
        char[] heslo = req.getParameter("login").trim().toCharArray();

        Uzivatel uzivatel = Uzivatel.overeni(login, heslo);
        if (uzivatel == null) {
            resp.sendError(401);
            return;
        }

        HttpSession session = req.getSession(true); // PHP: session_start()

        session.setAttribute("loggedUser", login); // PHP: $_SESSION['loggedUser'] = ...

        resp.sendRedirect("/demo/");
    }
}
