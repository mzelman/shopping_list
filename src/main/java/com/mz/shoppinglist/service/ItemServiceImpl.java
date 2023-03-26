package com.mz.shoppinglist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mz.shoppinglist.Constants;
import com.mz.shoppinglist.pojo.Item;
import com.mz.shoppinglist.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    
    public List<Item> getList() {
        return itemRepository.getList();
    }

    public void addItem(Item item) {
        itemRepository.addItem(item);
    }

    public void deleteItem(int index) {
        itemRepository.deleteItem(index);
    }

    public Item getItem(int index) {
        return itemRepository.getItem(index);
    }

    public void clearList() {
        itemRepository.clearList();
    }

    public int numberOfItems() {
        return itemRepository.numberOfItems();
    }

    public int getItemIndex(String id) {
        for (int i = 0; i < itemRepository.getList().size(); i++) {
            if (itemRepository.getList().get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.ITEM_NOT_FOUND;
    }

    public boolean indexExists(int index) {
        if (index == Constants.ITEM_NOT_FOUND) {
            return false;
        }
        return true;
    }

    public String checkDuplicate(Item item) {
        boolean nameDuplicates = false;

        for (int i = 0; i < itemRepository.getList().size(); i++) {
            if (itemRepository.getList().get(i).getName().equals(item.getName())) {
                nameDuplicates = true;
            }
        }

        if (!nameDuplicates) {
            itemRepository.addItem(item);
            return Constants.STATUS_SUCCESS;
        }
        return Constants.STATUS_FAILED;
    }

    public void crossOut(String id) {
        itemRepository.getList().get(getItemIndex(id)).crossOut();
    }

    public void uncross(String id) {
        itemRepository.getList().get(getItemIndex(id)).uncross();
    }

    public void findAndDeleteCrossedOut() {
        for (int i = itemRepository.getList().size() - 1; i >= 0; i--) {
            if (itemRepository.getList().get(i).isCrossedOut() == true) {
                itemRepository.deleteItem(i);
            }
    }
}

}
