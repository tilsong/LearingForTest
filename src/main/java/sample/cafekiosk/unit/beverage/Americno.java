package sample.cafekiosk.unit.beverage;

public class Americno implements Beverage {
    @Override
    public String getName() {
        return "아메리카노";
    }

    @Override
    public int getPrice() {
        return 4000;
    }
}
