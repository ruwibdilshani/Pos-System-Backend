package lk.ijse.possystembackend.controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.possystembackend.bo.CustomerBOIMPL;
import lk.ijse.possystembackend.dto.CustomerDTO;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/customer/*", loadOnStartup = 1)
public class CustomerController extends HttpServlet {
    private final CustomerBOIMPL customerBOIMPL = new CustomerBOIMPL();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customer = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            customerBOIMPL.saveCustomer(customer);
            writer.write(jsonb.toJson(customer));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (var writer = resp.getWriter()) {
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                writer.write("Customer ID is missing");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            String customerId = pathInfo.substring(1);
            if (customerId.isEmpty()) {
                writer.write("Customer ID is missing");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Jsonb jsonb = JsonbBuilder.create();
            CustomerDTO customer = jsonb.fromJson(req.getReader(), CustomerDTO.class);

            if (customerBOIMPL.updateCustomer(customerId, customer)) {
                writer.write(jsonb.toJson(customer));
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                writer.write("Customer update failed");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String customerId = req.getParameter("cusId");

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            resp.setContentType("application/json");

            if (customerId != null) {
                var customer = customerBOIMPL.searchCustomer(customerId);
                if (customer != null) {
                    writer.write(jsonb.toJson(customer));
                    resp.setStatus(HttpServletResponse.SC_OK);
                } else {
                    writer.write("{\"error\": \"Customer not found\"}");
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                }
            } else {
                List<CustomerDTO> customers = customerBOIMPL.getAllCustomers();
                System.out.println(customers);
                writer.write(jsonb.toJson(customers));
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (var writer = resp.getWriter()) {
            String pathInfo = req.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/")) {
                writer.write("Customer ID is missing");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            String[] split = pathInfo.split("/");
            if (split.length != 2) {
                writer.write("Invalid Customer ID");
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            String customerId = split[1];

            if (customerBOIMPL.deleteCustomer(customerId)) {
                writer.write("Customer delete successful");
                resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            } else {
                writer.write("Customer delete failed");
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}

