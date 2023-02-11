package structural.composite.shapes;

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
}
