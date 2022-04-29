package at.kaindorf.pattern.decorator;

public class HouseBlend extends Coffee{

    @Override
    public double costs() {
        return 2.49;
    }

    @Override
    public String getDescription() {
        return "House Blend";
    }
}
