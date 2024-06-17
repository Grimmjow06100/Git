package controller;

import model.Item;

public class ItemController {
    Item item;
    public ItemController(Item item){
        this.item = item;
    }

    public double getX(){
        return item.getX();
    }
    public double getY(){
        return item.getY();
    }

    public boolean getMustRemove(){
        return item.getMustRemove();
    }

    public void update(){
        item.update();
    }

    public Item.ItemID getItemId(){
        return item.getItemId();
    }

}
