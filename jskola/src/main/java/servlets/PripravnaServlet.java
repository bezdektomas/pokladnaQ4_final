package servlets;

import java.io.Console;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Polozka;

public class PripravnaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("dokoncit") != null) {
            Polozka o = new Polozka(0, "");
            o.zmenaStatusu(Integer.parseInt(req.getParameter("dokoncit")), 2);
            req.setAttribute("pripravna", Polozka.getObjednavkyPripravna());
            getServletContext().getRequestDispatcher("/panelPripravna.jsp").forward(req, resp);
        } else {
            req.setAttribute("pripravna", Polozka.getObjednavkyPripravna());
            getServletContext().getRequestDispatcher("/panelPripravna.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
