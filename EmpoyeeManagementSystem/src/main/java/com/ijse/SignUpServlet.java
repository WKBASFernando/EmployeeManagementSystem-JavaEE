package com.ijse;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;

@WebServlet("/api/v1/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> event = mapper.readValue(req.getInputStream(), Map.class);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eventdb", "root", "Ijse@1234");

            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO event (eid,ename, edescription, edate, eplace) VALUES (?, ?, ?, ?,?)"
            );
            stmt.setString(1, event.get("eid"));
            stmt.setString(2, event.get("ename"));
            stmt.setString(3, event.get("edescription"));
            stmt.setString(4, event.get("edate"));
            stmt.setString(5, event.get("eplace"));

            int rows = stmt.executeUpdate();
            resp.setContentType("application/json");
            mapper.writeValue(resp.getWriter(), rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}