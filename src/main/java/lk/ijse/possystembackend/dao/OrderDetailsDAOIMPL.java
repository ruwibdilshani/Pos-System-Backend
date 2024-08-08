package lk.ijse.possystembackend.dao;

import lk.ijse.possystembackend.dto.OrderDetailDTO;
import lk.ijse.possystembackend.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public final class OrderDetailsDAOIMPL implements OrderDetailsDAO {
    private final Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public String saveOrderDetails(OrderDetailDTO orderDetail) {
        try {
            var ps = connection.prepareStatement("insert into order_details(o_id, item_id, qty) values (?,?,?)");
            ps.setString(1, orderDetail.getOId());
            ps.setString(2, orderDetail.getItemId());
            ps.setString(3, orderDetail.getQty());

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
