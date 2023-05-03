package sample.cafekiosk.spring.api.service.product;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import sample.cafekiosk.spring.IntegrationTestSupport;
import sample.cafekiosk.spring.api.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.spring.api.service.product.response.ProductResponse;
import sample.cafekiosk.spring.domain.product.Product;
import sample.cafekiosk.spring.domain.product.ProductRepository;
import sample.cafekiosk.spring.domain.product.ProductSellingStatus;
import sample.cafekiosk.spring.domain.product.ProductType;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest extends IntegrationTestSupport {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    void tearDown() {
        productRepository.deleteAllInBatch();
    }

    @DisplayName("신규 상품을 등록한다. 상품 번호는 가장 최근 상품의 상품 번호에서 1 증가한 값이다.")
    @Test
    void createProduct() {
        // 기능이 많지 않은 서비스 테스트는 레포지토리 테스트와 거의 비슷하다.
        // 그러나 기능이 추가될 수록 검증할 것들이 추가되기 때문에, 만들어두는 것이 좋다.
        // given
        Product product1 = createProductOne("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "아메리카노", 4000);
        productRepository.save(product1);

        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("카푸치노")
                .price(5000).build();

        // when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        // then
        assertThat(productResponse)
                .extracting("productNumber", "type", "sellingStatus", "price", "name")
                .contains("002", ProductType.HANDMADE, ProductSellingStatus.SELLING, 5000, "카푸치노");

        List<Product> products = productRepository.findAll();
        assertThat(products).hasSize(2)
                .extracting("productNumber", "type", "sellingStatus", "price", "name")
                .containsExactlyInAnyOrder(
                        tuple("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, 4000, "아메리카노"),
                        tuple("002", ProductType.HANDMADE, ProductSellingStatus.SELLING, 5000, "카푸치노")
                );
    }

    @DisplayName("상품이 하나도 없는 경우 신규 상품을 등록하면 상품번호는 001이다.")
    @Test
    void createProductWhenProductsIsEmpty() {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("카푸치노")
                .price(5000).build();

        // when
        ProductResponse productResponse = productService.createProduct(request.toServiceRequest());

        // then
        assertThat(productResponse)
                .extracting("productNumber", "type", "sellingStatus",  "name", "price")
                .contains("001", ProductType.HANDMADE, ProductSellingStatus.SELLING, "카푸치노", 5000);
    }

    private Product createProductOne(String productNumber, ProductType productType, ProductSellingStatus hold, String name, int price) {
        return Product.builder().
                productNumber(productNumber)
                .type(productType)
                .sellingStatus(hold)
                .name(name)
                .price(price)
                .build();
    }
}