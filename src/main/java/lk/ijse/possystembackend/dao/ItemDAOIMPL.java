package lk.ijse.possystembackend.dao;

import lk.ijse.possystembackend.dto.ItemDTO;
import lk.ijse.possystembackend.util.DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class ItemDAOIMPL implements ItemDAO {
    private final Connection connection = DBConnection.getInstance().getConnection();

    @Override
    public List<ItemDTO> getAllItems() {
        List<ItemDTO> items = new ArrayList<>();

        try {
            var pst = connection.prepareStatement("Select * from item");
            var rs = pst.executeQuery();

            while (rs.next()){
                ItemDTO item = new ItemDTO(
                        rs.getString("item_id"),
                        rs.getString("name"),
                        rs.getString("qty"),
                        rs.getString("price")
                );
                items.add(item);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public String saveItem(ItemDTO item) {
        try {
            var ps = connection.prepareStatement("insert into item(item_id, name, qty, price) values (?,?,?,?)");
            ps.setString(1, item.getItemId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getQty());
            ps.setString(4, item.getPrice());

            if (ps.executeUpdate() != 0){
                return "item saved";
            } else {
                return "Failed to saved";
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateItem(String itemId, ItemDTO item) {
        try {
            var ps = connection.prepareStatement("update item set name=?, qty=?, price=? where item_id=?");
            ps.setString(1, item.getItemId());
            ps.setString(2, item.getName());
            ps.setString(3, item.getQty());
            ps.setString(4, item.getPrice());

            return ps.executeUpdate() != 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ItemDTO searchItem(String itemId) {
        try {
            ItemDTO itemDTO = new ItemDTO();
            var ps = connection.prepareStatement("select * from item where item_id=?");
            ps.setString(1, itemId);
            var rst = ps.executeQuery();

            while (rst.next()){
                itemDTO.setItemId(rst.getString("item_id"));
                itemDTO.setName(rst.getString("name"));
                itemDTO.setQty(rst.getString("qty"));
                itemDTO.setPrice(rst.getString("price"));
            }
            return itemDTO;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteItem(String itemId) {
        try {
            var ps = connection.prepareStatement("delete from item where item_id=?");
            ps.setString(1, itemId);
            return ps.executeUpdate() != 0;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
