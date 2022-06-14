package servlets;

import java.io.Console;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Trida;

// @WebServlet(name = "tridy", urlPatterns = { "/tridy" })
public class TridyServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("id") == null) {
            // není zadaný parametr id (v adrese)
            
            if (req.getParameter("edit") == null) {
                // získání dat z modelu a jejich příprava pro pohled
                req.setAttribute("tridy", Trida.getAll()); 
                // předání požadavku ke zpracování pohledem
                getServletContext().getRequestDispatcher("/tridy.jsp").forward(req, resp);
                // přeposlání nemění url adresu v řádce
            } else {
                // prosté zobrazení prázdného editačního formuláře
                getServletContext().getRequestDispatcher("/trida.edit.jsp").forward(req, resp);
            }
            
        } else {
            req.setAttribute("trida", new Trida(Integer.parseInt(req.getParameter("id"))));
            getServletContext().getRequestDispatcher(req.getParameter("edit") == null ? "/trida.jsp" : "/trida.edit.jsp").forward(req, resp);
        }
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
                resp.sendRedirect("/jskola/tridy");
                return;
            } else {
                resp.sendError(500);
                return;
            }
        }

        
    }
}
