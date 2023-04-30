package sample.cafekiosk.unit;

import sample.cafekiosk.unit.beverage.Americno;
import sample.cafekiosk.unit.beverage.Latte;

public class CafeKioskRunner {

    public static void main(String[] args) {
        CafeKiost cafeKiost = new CafeKiost();
        cafeKiost.add(new Americno());
        System.out.println(">>>> 아메리카노 추가");

        cafeKiost.add(new Latte());
        System.out.println(">>>> 라떼 추가");

        int totalPrice = cafeKiost.calculateTotalPrice();
        System.out.println("총 주문 가격: " +  totalPrice);
    }
}
