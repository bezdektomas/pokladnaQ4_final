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
public class ObjednavkaServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                // získání dat z modelu a jejich příprava pro pohled
                req.setAttribute("objednavka", Polozka.getObjednavkaPolozky()); 
                // předání požadavku ke zpracování pohledem
                getServletContext().getRequestDispatcher("/objednavka.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       
        
    }
}
