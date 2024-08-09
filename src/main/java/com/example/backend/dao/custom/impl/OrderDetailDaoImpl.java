package com.example.backend.dao.custom.impl;

import com.example.backend.dao.custom.OrderDetailDao;
import com.example.backend.entity.Customer;
import com.example.backend.entity.OrderDetails;
import com.example.backend.utill.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDaoImpl  implements OrderDetailDao {
    public static String SAVE_ORDER_ITEM_DETAIL = "INSERT INTO order_item_detail (order_id, item_id, qty) VALUES(?,?,?)";

    @Override
    public boolean save(OrderDetails entity) throws SQLException {
        return SQLUtil.execute(SAVE_ORDER_ITEM_DETAIL,
                entity.getOrderId(),
                entity.getItemId(),
                entity.getQty()
        );
    }

    @Override
    public List<OrderDetails> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM order_item_detail");
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        while (resultSet.next()) {
            orderDetailsList.add(new OrderDetails(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)

            ));
        }
        return orderDetailsList;
    }
}