package structural.composite.boxes;

import java.util.List;

public class Client {
    public static void main(String args[]){
        Product p1 = new Product();
        p1.setPrice(100);
        Product p2 = new Product();
        p2.setPrice(10);
        Product p3 = new Product();
        p3.setPrice(10);

        Product p4 = new Product();
        p4.setPrice(100);
        Product p5 = new Product();
        p5.setPrice(10);
        Product p6 = new Product();
        p6.setPrice(10);

        Box b1 = new Box();
        b1.setItems(List.of(p1,p2));

        Box b2 = new Box();
        b2.setItems(List.of(p3,p4,b1));

        Box b3 = new Box();
        b3.setItems(List.of(p5,p6,b2));

        System.out.println(b3.getPrice());
    }
}
