package com.example.backend.bo.custom;

import com.example.backend.dao.DAOFactory;
import com.example.backend.dao.custom.CustomerDao;
import com.example.backend.dao.custom.OrderDetailDao;
import com.example.backend.dto.CustomerDto;
import com.example.backend.dto.OrderDetailDto;
import com.example.backend.entity.Customer;
import com.example.backend.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBoImpl implements   OrderDetailBO {

    OrderDetailDao orderDetailDao = (OrderDetailDao) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.OrderDetail);

    @Override
    public List<OrderDetailDto> getAllOrderDetails() throws SQLException {

        List<OrderDetails> orderDetails = orderDetailDao.getAll();
        if (orderDetails != null) {
            return orderDetails.stream().map(allOrderDetails -> new OrderDetailDto(allOrderDetails.getOrderId(), allOrderDetails.getItemId(), allOrderDetails.getQty())).toList();
        } else {
            return null;

        }
    }
}
