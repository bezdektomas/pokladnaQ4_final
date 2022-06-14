package servlets;

import java.io.Console;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Objednavky;
import model.Polozka;

public class VydejServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("vydat") != null) {
            Objednavky o = new Objednavky(0, "");
            o.zmenaStatusu(Integer.parseInt(req.getParameter("vydat")), 3);
            req.setAttribute("vydej", Objednavky.getObjednavkyVydej());
            getServletContext().getRequestDispatcher("/panelVydej.jsp").forward(req, resp);
        } else {
            if (req.getParameter("vydano") != null) {
                Objednavky o = new Objednavky(0, "");
                o.zmenaStatusu(Integer.parseInt(req.getParameter("vydano")), 4);
                req.setAttribute("vydej", Objednavky.getObjednavkyVydej());
                getServletContext().getRequestDispatcher("/panelVydej.jsp").forward(req, resp);
            } else {
                req.setAttribute("vydej", Objednavky.getObjednavkyVydej());
                getServletContext().getRequestDispatcher("/panelVydej.jsp").forward(req, resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
