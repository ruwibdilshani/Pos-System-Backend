package lk.ijse.possystembackend.dao;

import lk.ijse.possystembackend.dto.CustomerDTO;
import lk.ijse.possystembackend.dto.ItemDTO;

import java.util.List;

public sealed interface ItemDAO permits ItemDAOIMPL{
    List<ItemDTO> getAllItems();
    String saveItem(ItemDTO item);
    boolean updateItem(String itemId, ItemDTO item);
    ItemDTO searchItem(String itemId);
    boolean deleteItem(String itemId);
}
