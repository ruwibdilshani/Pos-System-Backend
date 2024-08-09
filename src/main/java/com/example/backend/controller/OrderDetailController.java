package com.example.backend.controller;
import com.example.backend.bo.BOFactory;
import com.example.backend.bo.custom.OrderDetailBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(urlPatterns = "/orderDetail")
public class OrderDetailController extends HttpServlet {


    static Logger logger = org.slf4j.LoggerFactory.getLogger(CustomerController.class);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.OrderDetail);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Received GET request for order details");
        try (PrintWriter writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");
            jsonb.toJson(orderDetailBO.getAllOrderDetails(), writer);
            logger.info("Successfully retrieved all order details");
        } catch (SQLException | IOException e) {
            logger.error("Error retrieving  order details", e);
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
