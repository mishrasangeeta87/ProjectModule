package structural.composite.boxes;

import java.util.List;

public class Box implements Item{
    private List<Item> items;
    @Override
    public double getPrice() {
        double totalPrice = 0.0D;
        for(Item item : items){
            totalPrice+= item.getPrice();
        }
        return totalPrice;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
