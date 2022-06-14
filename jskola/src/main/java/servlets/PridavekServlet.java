package servlets;

import java.io.Console;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Trida;
import model.Polozka;

// @WebServlet(name = "tridy", urlPatterns = { "/tridy" })
public class PridavekServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("pridavek", new Polozka(Integer.parseInt(req.getParameter("id"))));
        getServletContext()
                .getRequestDispatcher(req.getParameter("edit") == null ? "/polozka.view.jsp" : "/polozka.edit.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int rocnik = Integer.parseInt(req.getParameter("rocnik"));
        String oznaceni = req.getParameter("oznaceni").trim();

        boolean ok = true;
        ok &= rocnik > 0 && rocnik < 5;
        ok &= !oznaceni.isEmpty();

        if (ok) {
            Trida t;
            if (id > 0) {
                t = new Trida(id).setOznaceni(oznaceni).setRocnik(rocnik);
            } else {
                t = new Trida(rocnik, oznaceni);
            }

            if (t.save()) {
                resp.sendRedirect("/demo/tridy");
                return;
            } else {
                resp.sendError(500);
                return;
            }

            
        }

    }
}
