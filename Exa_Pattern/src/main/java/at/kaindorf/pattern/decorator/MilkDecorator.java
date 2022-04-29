package at.kaindorf.pattern.decorator;

public class MilkDecorator extends CoffeeDecorator{

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double costs() {
        return 0.5 + coffee.costs();
    }

    @Override
    public String getDescription() {
        return "Milk, " + coffee.getDescription();
    }
}
