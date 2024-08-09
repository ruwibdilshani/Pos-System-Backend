package com.example.backend.bo.custom;
import com.example.backend.dao.DAOFactory;
import com.example.backend.dao.custom.ItemDao;
import com.example.backend.dao.custom.OrderDao;
import com.example.backend.dao.custom.OrderDetailDao;
import com.example.backend.db.ConnectionManager;
import com.example.backend.dto.ItemDto;
import com.example.backend.dto.OrderDto;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBoImpl implements OrderBo {
    OrderDao orderDAO =
            (OrderDao) DAOFactory.getInstance()
                    .getDAO(DAOFactory.DAOType.Order);

    ItemDao itemDao =
            (ItemDao) DAOFactory.getInstance()
                    .getDAO(DAOFactory.DAOType.ITEM);

    OrderDetailDao orderItemDetailDAO =
            (OrderDetailDao) DAOFactory.getInstance()
                    .getDAO(DAOFactory.DAOType.OrderDetail);



    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException {
        Connection connection = ConnectionManager.getInstance().getConnection();
        boolean isOrderSaved;
        boolean isOrderItemSaved = true;
        connection.setAutoCommit(false);

        // Save ekata
        isOrderSaved = orderDAO.save(new Order(
                dto.getId(),
                dto.getDate(),
                dto.getCustomerId(),
                Double.parseDouble(dto.getTotal()),
                dto.getDiscount(),
                Double.parseDouble(dto.getSubTotal()),
                Double.parseDouble(dto.getCash()),
                Double.parseDouble(dto.getBalance())
        ));


        if (isOrderSaved) {
            for (ItemDto item : dto.getItems()) {
                boolean isOrderItemDetailSaved = orderItemDetailDAO.save(new OrderDetails(
                        dto.getId(),
                        item.getId(),
                        item.getQty()
                ));
                if (!isOrderItemDetailSaved) {
                    connection.rollback(); // Rollback transaction on failure
                    isOrderItemSaved = false;
                    break;
                }
            }
        }

        // itemQty Update ekh
/////
        boolean itemUpdated = true;
        if (isOrderItemSaved) {
            for (ItemDto item : dto.getItems()) {
                boolean isUpdated = itemDao.updateQty(item.getId(), item.getQty());
                if (!isUpdated) {
                    connection.rollback(); // Rollback transaction on failure
                    itemUpdated = false;
                    break;
                }
            }
        }
        if (isOrderSaved && isOrderItemSaved && itemUpdated) {
            connection.commit();
        } else {
            connection.rollback();
        }
        connection.setAutoCommit(true);
        return isOrderSaved;
    }

    @Override
    public List<OrderDto> getAllOrders() throws SQLException {
        List<Order> orderEntities = orderDAO.getAll();
        List<OrderDto> orderDTOS = new ArrayList<>();
        for (Order order : orderEntities) {
            orderDTOS.add(
                    new OrderDto(
                            order.getId(),
                            order.getDate(),
                            order.getCustomerId(),
                            null,
                            String.valueOf(order.getTotal()),
                            order.getDiscount(),
                            String.valueOf(order.getSubTotal()),
                            String.valueOf(order.getCash()),
                            String.valueOf(order.getBalance())
                    ));
        }
        return orderDTOS;
    }
}