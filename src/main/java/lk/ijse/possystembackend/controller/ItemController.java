package lk.ijse.possystembackend.controller;


import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.possystembackend.bo.ItemBOIMPL;
import lk.ijse.possystembackend.dto.ItemDTO;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/item/*", loadOnStartup = 1)
public class ItemController extends HttpServlet {
    private final ItemBOIMPL itemBOIMPL = new ItemBOIMPL();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        Jsonb jsonb = JsonbBuilder.create();
        resp.setContentType("application/json");

        if (pathInfo == null || pathInfo.equals("/")) {
            List<ItemDTO> items = itemBOIMPL.getAllItems();
            resp.getWriter().write(jsonb.toJson(items));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            String itemId = pathInfo.substring(1);
            ItemDTO item = itemBOIMPL.searchItem(itemId);

            if (item != null) {
                resp.getWriter().write(jsonb.toJson(item));
                resp.setStatus(HttpServletResponse.SC_OK);
            } else {
                resp.getWriter().write("{\"error\": \"Item not found\"}");
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.getWriter().write("{\"error\": \"Item ID is missing\"}");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String itemId = pathInfo.substring(1);
        Jsonb jsonb = JsonbBuilder.create();
        ItemDTO updatedItem = jsonb.fromJson(req.getReader(), ItemDTO.class);

        if (itemBOIMPL.updateItem(itemId, updatedItem)) {  // Assuming you have a method to update an item
            resp.getWriter().write(jsonb.toJson(updatedItem));
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.getWriter().write("{\"error\": \"Failed to update item\"}");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        try (var writer = resp.getWriter()) {
            Jsonb jsonb = JsonbBuilder.create();
            ItemDTO item = jsonb.fromJson(req.getReader(), ItemDTO.class);

            itemBOIMPL.saveItem(item);
            writer.write(jsonb.toJson(item));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            resp.getWriter().write("{\"error\": \"Item ID is missing\"}");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String itemId = pathInfo.substring(1);

        if (itemBOIMPL.deleteItem(itemId)) {
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
        } else {
            resp.getWriter().write("{\"error\": \"Failed to delete item\"}");
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
