package com.example.backend.bo.custom;

import com.example.backend.bo.SuperBO;
import com.example.backend.dto.OrderDetailDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO extends SuperBO {
    List<OrderDetailDto> getAllOrderDetails() throws SQLException;
}
