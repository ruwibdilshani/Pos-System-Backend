package lk.ijse.possystembackend.dao;

import lk.ijse.possystembackend.dto.OrderDTO;
import lk.ijse.possystembackend.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public final class OrderDAOIMPL implements OrderDAO {
    private final Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public String saveOrder(OrderDTO order) {
        try {
            var ps = connection.prepareStatement("insert into order(o_id, dateTime) values (?,?)");
            ps.setString(1, order.getOId());
            ps.setString(2, order.getDateTime());

            if (ps.executeUpdate() != 0){
                return "Order saved";
            } else {
                return "Failed to saved";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
