package sample.cafekiosk.spring.api.service.product;

import lombok.RequiredArgsConstructor;
import sample.cafekiosk.spring.domain.product.ProductRepository;

@RequiredArgsConstructor
public class ProductNumberFactory {

    private final ProductRepository productRepository;

    public String createNextProductNumber() {
        String latestProductNumber = productRepository.findLatestProductNumber();

        if (latestProductNumber == null) {
            return "001";
        }

        Integer latestProductNumeberInt = Integer.parseInt(latestProductNumber);
        int nextProductNumberInt = latestProductNumeberInt +1;

        return String.format("%03d", nextProductNumberInt);
    }
}
