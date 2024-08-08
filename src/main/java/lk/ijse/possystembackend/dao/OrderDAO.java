package lk.ijse.possystembackend.dao;

import lk.ijse.possystembackend.dto.OrderDTO;

public sealed interface OrderDAO permits OrderDAOIMPL{
    String saveOrder(OrderDTO order);
}
