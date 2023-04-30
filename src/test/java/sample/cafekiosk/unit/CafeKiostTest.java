package sample.cafekiosk.unit;

import org.junit.jupiter.api.Test;
import sample.cafekiosk.unit.beverage.Americano;
import sample.cafekiosk.unit.beverage.AmericanoTest;
import sample.cafekiosk.unit.beverage.Latte;

import static org.assertj.core.api.Assertions.assertThat;

class CafeKiostTest {

    // 최종 단계에서 사람이 개입해야 함
    // 무엇이 성공이고, 실패인지가 확실하지 않음.
    // => 수동 테스트 -> 자동 테스트 전환 필요
    @Test
    void add_manual_test() {
        CafeKiost cafeKiost = new CafeKiost();
        cafeKiost.add(new Americano());

        System.out.println(">>>>> 담긴 음료 수: " + cafeKiost.getBeverages().size());
        System.out.println(">>>>> 담긴 음료 : " + cafeKiost.getBeverages().get(0).getName());
    }

    @Test
    void add() {
        CafeKiost cafeKiost = new CafeKiost();
        cafeKiost.add(new Americano());

        assertThat(cafeKiost.getBeverages().size()).isEqualTo(1);
        assertThat(cafeKiost.getBeverages().get(0).getName()).isEqualTo("아메리카노");
        
    }

    @Test
    void remove() {
        CafeKiost cafeKiost = new CafeKiost();
        Americano americano = new Americano();

        cafeKiost.add(americano);
        assertThat(cafeKiost.getBeverages()).hasSize(1);

        cafeKiost.remove(americano);
        assertThat(cafeKiost.getBeverages()).isEmpty();
    }

    @Test
    void clear() {
        CafeKiost cafeKiost = new CafeKiost();
        Americano americano = new Americano();
        Latte latte = new Latte();

        cafeKiost.add(americano);
        cafeKiost.add(latte);

        assertThat(cafeKiost.getBeverages()).hasSize(2);

        cafeKiost.clear();
        assertThat(cafeKiost.getBeverages()).isEmpty();
    }
}