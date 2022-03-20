package ru.edu;

import Person.Person;
import dao.ProductRepository;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/m")

public class Servlet extends HttpServlet {

    /**
     * get person information on http://localhost:8087/lec/m
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductRepository person = new ProductRepository();
        Collection<Person> personTable = person.readAllPersons();

        req.setAttribute("allPerson", personTable);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
