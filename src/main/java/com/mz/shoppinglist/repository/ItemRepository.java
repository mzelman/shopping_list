package com.mz.shoppinglist.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mz.shoppinglist.pojo.Item;

@Repository
public class ItemRepository {

    private List<Item> items =  new ArrayList<>();

    public List<Item> getList() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void deleteItem(int index) {
        items.remove(index);
    }

    public Item getItem(int index) {
        return items.get(index);
    }

    public void clearList() {
        items.clear();
    }

    public int numberOfItems() {
        return items.size();
    }

}
