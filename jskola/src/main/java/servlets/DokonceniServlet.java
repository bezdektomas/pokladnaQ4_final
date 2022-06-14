package servlets;

import java.io.Console;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Polozka;
import print.Tiskarna;

public class DokonceniServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("zahodit") != null) {
            Polozka t = new Polozka(0, "");
            t.zahozeniObjednavky();
            getServletContext().getRequestDispatcher("/").forward(req, resp);
        } else {
            if (req.getParameter("dokoncit") != null) {

                Tiskarna pr = Tiskarna.getTiskarna();
                pr.ucetenka(Polozka.getObjednavkaPolozkyMazani(), Polozka.getObjednavkaMax());

                Polozka t = new Polozka(0, "");
                t.dokonceniObjednavky();
                getServletContext().getRequestDispatcher("/").forward(req, resp);
            } else {
                if (Polozka.getObjednavkaPolozky().size() != 0) {
                    req.setAttribute("objednavka", Polozka.getObjednavkaPolozkyMazani());
                    getServletContext().getRequestDispatcher("/dokonceniObjednavky.jsp").forward(req, resp);
                } else {
                    req.setAttribute("objednavka", Polozka.getObjednavkaPolozky());
                    getServletContext().getRequestDispatcher("/objednavka.jsp").forward(req, resp);
                }

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
