package at.kaindorf.pattern.decorator.client;

import at.kaindorf.pattern.decorator.Coffee;
import at.kaindorf.pattern.decorator.HouseBlend;
import at.kaindorf.pattern.decorator.MilkDecorator;
import at.kaindorf.pattern.decorator.SugarDecorator;

public class CoffeeClient {

    public static void main(String[] args) {
        Coffee coffee = new HouseBlend();
        System.out.println(coffee.getDescription() + " costs: " + coffee.costs());
        coffee = new MilkDecorator(coffee);
        System.out.println(coffee.getDescription() + " costs: " + coffee.costs());
        coffee = new SugarDecorator(new SugarDecorator(coffee));
        System.out.println(coffee.getDescription() + " costs: " + coffee.costs());
    }
}
