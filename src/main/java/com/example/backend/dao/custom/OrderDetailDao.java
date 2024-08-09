package com.example.backend.dao.custom;

import com.example.backend.dao.SuperDAO;
import com.example.backend.entity.Order;
import com.example.backend.entity.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao extends SuperDAO {
    boolean save(OrderDetails entity) throws SQLException;
    List<OrderDetails> getAll() throws SQLException;
}