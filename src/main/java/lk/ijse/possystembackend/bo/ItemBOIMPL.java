package lk.ijse.possystembackend.bo;

import lk.ijse.possystembackend.dao.ItemDAOIMPL;
import lk.ijse.possystembackend.dto.ItemDTO;

import java.util.List;

public final class ItemBOIMPL implements ItemBO {
    private final ItemDAOIMPL itemDAO = new ItemDAOIMPL();

    @Override
    public List<ItemDTO> getAllItems() {
        return itemDAO.getAllItems();
    }

    @Override
    public String saveItem(ItemDTO item) {
        return itemDAO.saveItem(item);
    }

    @Override
    public boolean updateItem(String itemId, ItemDTO item) {
        return itemDAO.updateItem(itemId, item);
    }

    @Override
    public ItemDTO searchItem(String itemId) {
        return itemDAO.searchItem(itemId);
    }

    @Override
    public boolean deleteItem(String itemId) {
        return deleteItem(itemId);
    }
}
