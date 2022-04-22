package at.kaindorf.pattern.decorator;

public class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double costs() {
        return 0.35 + coffee.costs();
    }

    @Override
    public String getDescription() {
        return "Sugar, " + coffee.getDescription();
    }
}