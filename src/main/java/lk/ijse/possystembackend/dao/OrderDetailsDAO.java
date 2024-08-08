package lk.ijse.possystembackend.dao;

import lk.ijse.possystembackend.dto.OrderDetailDTO;

public sealed interface OrderDetailsDAO permits OrderDetailsDAOIMPL{
    String saveOrderDetails(OrderDetailDTO orderDetail);
}
