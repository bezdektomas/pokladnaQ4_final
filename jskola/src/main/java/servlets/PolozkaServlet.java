package servlets;

import java.io.Console;
import java.io.IOException;
import java.util.Arrays;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Trida;
import model.Polozka;

// @WebServlet(name = "tridy", urlPatterns = { "/tridy" })
public class PolozkaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("menu") == null) {
            if (req.getParameter("pridavek") == null) {
                req.setAttribute("polozka", Polozka.getAll(Integer.parseInt(req.getParameter("id"))));
                getServletContext().getRequestDispatcher("/order.jsp").forward(req, resp);
            } else {
                req.setAttribute("polozka", Polozka.getAll(Integer.parseInt(req.getParameter("id"))));
                req.setAttribute("pridavek", Polozka.getAll(Integer.parseInt(req.getParameter("pridavek"))));
                getServletContext().getRequestDispatcher("/orderPridavek.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("menu", Polozka.getAll(1));
            req.setAttribute("menuPriloha", Polozka.getAll(2));
            req.setAttribute("menuNapoj", Polozka.getAll(3));
            req.setAttribute("menuPridavek", Polozka.getAll(4));
            getServletContext().getRequestDispatcher("/orderMenu.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean ok = true;

        if (req.getParameter("polozka") == null) {
            Polozka t;
            String[] menuPridavek = req.getParameterValues("menuPridavek");
            
            if (req.getParameter("menu") != null || req.getParameter("menuPriloha") != null
                    || req.getParameter("menuNapoj") != null) {

                try {
                    t = new Polozka(Integer.parseInt(req.getParameter("menu")));
                } catch (Exception e) {
                    resp.sendRedirect("/demo/objednavka");
                    return;
                }
                

                if (t.save()) {
                    int idHlav = t.getMaxProdukt();
                    t = new Polozka(Integer.parseInt(req.getParameter("menuPriloha")));
                    t.savePridavek(idHlav);
                    t = new Polozka(Integer.parseInt(req.getParameter("menuNapoj")));
                    t.savePridavek(idHlav);

                    if (menuPridavek != null) {
                        if (menuPridavek.length > 0) {
                            for (String value : menuPridavek) {
                                t = new Polozka(Integer.parseInt(value));
                                t.savePridavek(idHlav);
                            }
                        }

                    }
                }

            }

            resp.sendRedirect("/demo/objednavka");
            return;
        } else {
            int id = Integer.parseInt(req.getParameter("polozka"));
            String[] pridavek = req.getParameterValues("pridavek");
            if (ok) {
                Polozka t;

                t = new Polozka(id);

                if (t.save()) {
                    int idHlav = t.getMaxProdukt();
                    if (pridavek != null) {
                        if (pridavek.length > 0) {
                            for (String value : pridavek) {
                                t = new Polozka(Integer.parseInt(value));
                                t.savePridavek(idHlav);
                            }
                        }

                    }

                    resp.sendRedirect("/demo/objednavka");
                    return;
                } else {
                    resp.sendRedirect("/demo/objednavka");
                    return;
                }
            }
        }

    }
}