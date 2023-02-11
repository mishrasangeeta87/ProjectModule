package structural.composite.boxes;

public class Product implements  Item{
    private int price;
    @Override
    public double getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
