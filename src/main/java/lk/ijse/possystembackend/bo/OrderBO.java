package lk.ijse.possystembackend.bo;

import lk.ijse.possystembackend.dto.OrderDTO;

public sealed interface OrderBO permits OrderBOIMPL{
    String saveOrder(OrderDTO order);
}
