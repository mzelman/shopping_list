package com.mz.shoppinglist.service;

import java.util.List;

import com.mz.shoppinglist.pojo.Item;

public interface ItemService {

    public List<Item> getList();

    public void addItem(Item item);

    public void deleteItem(int index);

    public Item getItem(int index);

    public void clearList();

    public int numberOfItems();
    
    public int getItemIndex(String id);

    public boolean indexExists(int i);

    public String checkDuplicate(Item item);

    public void crossOut(String id);

    public void uncross(String id);

    public void findAndDeleteCrossedOut();

}
