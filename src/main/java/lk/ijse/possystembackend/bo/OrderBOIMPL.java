package lk.ijse.possystembackend.bo;

import lk.ijse.possystembackend.dao.OrderDAOIMPL;
import lk.ijse.possystembackend.dto.OrderDTO;

public final class OrderBOIMPL implements OrderBO {
    private final OrderDAOIMPL orderDAO = new OrderDAOIMPL();

    @Override
    public String saveOrder(OrderDTO order) {
        return orderDAO.saveOrder(order);
    }
}
