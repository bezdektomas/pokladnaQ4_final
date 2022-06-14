package servlets;

import java.io.Console;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Polozka;

// @WebServlet(name = "tridy", urlPatterns = { "/tridy" })
public class MazaniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("id") != null) {
            Polozka t = new Polozka(0, "");
            t.smazaniPolozky(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("objednavka", Polozka.getObjednavkaPolozkyMazani());
            getServletContext().getRequestDispatcher("/smazat.jsp").forward(req, resp);
        } else {
            req.setAttribute("objednavka", Polozka.getObjednavkaPolozkyMazani());
            getServletContext().getRequestDispatcher("/smazat.jsp").forward(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}

// x dé dé dé dé, muck :*