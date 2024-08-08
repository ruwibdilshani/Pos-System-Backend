package lk.ijse.possystembackend.bo;

import lk.ijse.possystembackend.dto.ItemDTO;

import java.util.List;

public sealed interface ItemBO permits ItemBOIMPL{
    List<ItemDTO> getAllItems();
    String saveItem(ItemDTO item);
    boolean updateItem(String itemId, ItemDTO item);
    ItemDTO searchItem(String itemId);
    boolean deleteItem(String itemId);
}
