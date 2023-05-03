## Section 1
### 테스트는 귀찮다. 하지만 해야 한다. 왜?
  1. 자동화 테스트로 비교적 빠른 시간 안에 버그를 발견할 수 있고, 수동 테스트에 드는 비용을 크게 절약할 수 있다.
  2. 소프트웨어의 빠른 변화를 지원한다
  3. 팀원들의 집단 지성을 팀 차원의 이익으로 승격시킨다.
  4. 가까이 보면 느리지만, 멀리 보면 가장 빠르다.

## Section 2
### 단위 테스트
  - 작은 코드 단위(클래스 or 메서드)를 독립적으로 검증하는 테스트
  - 검증 속도가 빠르고, 안정적이다.
### 중요!
  - 테스트 케이스 세분화하기
    - 먼저 테스트 케이스를 세분화할 수 있는 질문을 던져야 한다.
      - ex) 암묵적이거나 아직 드러나지 않은 요구사항이 있는가?
    - 범위 조건이 있다면 경계 값에서 테스트하는 것이 중요하다.

  - 테스트하기 어려운 영역을 구분한다.
    - 그렇지 않으면 나머지 영역의 테스트도 깨지게 될 수 있다.
    - 외부로 어려운 영역을 분리할 수도 있다. 외부로 분리할수록 테스트 가능한 코드는 많아진다.

  - 테스트하기 어려운 영역
    - 관측할 때마다 다른 값에 의존하는 코드
      - ex. 현재 날짜/시간, 랜덤 값, 전역 함수/변수, 사용자 입력 등
    - 외부 세계에 영향을 주는 코드
      - 표준 출력, 메시지 발송, 데이터베이스에 기록하기 등
    - 이는 결국 순수 함수와 같이 같은 입력에 같은 결과, 외부와 단절된 형태 가 테스트하기 쉬운 형태을 말한다.
    - **외부로 분리하는 것이 가장 쉬운 방법이다!** 
  
## Section 3
### TDD: Test Driven Development
- 프로덕션 코드보다 테스트 코드를 먼저 작성하여 테스트가 구현 과정을 주도하도록 하는 개발 방법론
- **TDD의 핵심 가치 ⇒ 구현 코드에 대해 자주, 빠르게 피드백을 받을 수 있음.**
- 레드-그린-리팩토링

**선 기능 구현, 후 테스트 작성**

- 테스트 자체의 누락 가능성
- 특정 테스트 케이스(해피 케이스)만 검증할 가능성
- 잘못된 구현을 다소 늦게 발견할 가능성

**선 테스트 작성, 후 기능 구현**

- 복잡도가 낮은(유연하고 유지보수가 쉬운), 테스트 가능한 코드로 구현할 수 있게 한다.
  - 만약 기능부터 작성했다면, 테스트하기 어렵기 때문에 테스트하지 않을 수도 있다. 그러나 테스트 부터 한다면, 테스트 상황을 고려하여 테스트를 작성할 수 있다.
- 쉽게 발견하기 어려운 엣지(Edge) 케이스를 놓치지 않게 해준다.
- 구현에 대한 빠른 피드백을 받을 수 있다.
- 과감한 리팩토링이 가능해진다.

## Section 4 테스트는 [] 다

### 4.1 테스트는 [] 다

테스트는 문서다.

**문서?**

- 프로덕션 기능을 설명하는 테스트 코드 문서
- 다양한 테스트 케이스를 통해

  프로덕션 코드를 이해하는 시각과 관점을 보완

- 어느 **한 사람**이 과거에 경험했던 고민의 결과물을

  **팀 차원**으로 승격시켜서, 모두의 자산으로 공유할 수 있다.

우리는 항상 팀으로 일한다.

## 4.2 DisplayName을 섬세하게
- @DisplayName을 도메인 관점에서 명확한 문장으로 기술하자!
- 메서드 자체의 관점이나, 테스트의 현상을 중점으로 기술하지 말 것!
- Given / When / Then - 주어진 환경, 행동, 상태 변화
- **언어가 사고를 제한한다.**
- 테스트의 기능만 잘하면 충분? X → 코드가 올바르더라도 명확하게 표현하지 못한 테스트가 허들이 되고, 사고를 제한할 수 있음.

### 5.2 service 레이어 테스트
- 서비스 레이어 테스트를 하다가, 필요하면 Domain, Repository에 필요한 메서드나 로직을 만들고, 먼저 해당 부분을 테스트한다.
  이후, 다시 돌아와 서비스 레이어를 테스트하게 된다. 실제 코드 작성 시에도 이러한 경우가 있는데, 테스트와 함께 이루어진다는 점이 인상 깊다.
- 생성자를 private으로 전환하고, 여기에 @Builder를 붙여서 of 메서드로 객체를 생성하도록 한다.
  - 캐싱은 객체 생성 시간을 줄이고, 시스템 전반적인 성능을 향상시킬 수 있는 강력한 기능입니다. 따라서, 객체 생성이 빈번하게 일어나는 시스템에서는 of 메서드를 사용하여 캐싱 기능을 활용하는 것이 좋습니다. from ChatGPT
- 테스트의 단위가 매우 작고, 따라서 거의 모든 로직이라고 할 수 있는 부분들은 다 테스트를 하고 있는 것 같다.
- @ActiveProfiles("test") 를 통해 Profile 설정을 해주어야 한다.

### 5.3 서비스 레이어 테스트 2
- 프레젠테이션 레이어의 테스트가 없을 때 -> ~.http 파일을 만들어서 사용할 수 있다.
- @DataJpaTest 
  - @Transactional을 내부에 가지고 있기 때문에, 테스트마다 Roll back이 됨.
    반면에, @SpringBootTest는 없음. 그래서 자동 롤백이 안됨.
    만약 @Transactional + @SpringBootTest => 롤백됨. 근데 이 때에도 문제가 생길 수 있음.
    but springboottest 선호 왜? -> 다음 섹션에서 이야기할 것.
- @SpringBootTest
  - 사용 시 롤백 대신 쓸 수 있는 것 -> @AfterEach, 테스트 수행 후 repository 초기화
- 테스트 코드 간소화를 위한 테스트용 헬퍼 메서드를 사용할 수 있다.
- 실제 사용할만한 내용들
  - throw new IllegalArgumentException  ==> 도메인 예외 없이 간편히 사용 가능한 예외
  - assertThat(stocks).hasSize(2)
                .extracting("productNumber", "quantity") // => 객체로부터 해당 이름의 속성 추출
                .containsExactlyInAnyOrder(  // ==> 어떤 요소 가지고 있는지 파악
                    tuple("001", 1),
                    tuple("002", 2)
                );
  - assertThatThrownBy(() -> stock.deductQuantity(quantity))  ==> 예외 발생 시 
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("차감할 재고 수량이 없습니다.");
  - stream에서 collect로 map 효과 동시에 내기
    - stockProductNumbers.stream()
        .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

- @Transactional -> 
  - 롤백 기능 편리. 
- but 실제 코드에 Transactional이 없음에도 있는 것처럼 생각할 수 있음. 그러므로 조심..

  // 동시성 이슈? 해결 방법은 여러 가지.
  // db 컬럼에 unique 제약 조건 걸고, 튕기면 3회 재시도해서 통과하도록 하기.
  // 동시 접속자 너무 많으면 -> uuid 같은 것을 활용해서 사용하기(값 자체를 unique하게 가져가기)


- Transactional
  - readOnly = true
    - 읽기 전용 트랜잭션 -> 중요!!
    - CRUD에서 CUD 동작 X / only Read
    - JPA : CUD 스냅샷 저장, 변경 감지 X (성능 향상)
  - CQRS - Command / Read
    - Read 작업 수가 훨씬 많음 => 이에 대한 책임 분리, 서로 연관 없게 끔 하자.
  - 쿼리용 서비스, 커맨드용 서비스를 분리할 수도 있음.
  - db에 대한 엔드포인트 구분이 가능
    - Read 용 db, Write 용 db // master, slave db 에 대한 엔드 포인트 분리 설정 가능
  - 트랜잭션 구분해서 주기! 조회용은 따로 플래그 주기
    - 서비스 상단에 readOnly = true를 주고, cud 작업이 있으면 메서드 단위에 @Transaction을 주기
    - 커맨드, 쿼리 서비스를 분리하자 -> 애플리케이션에서 아예 분리해서 관리.

@NoArgsConstructor // post-> objectmapper가 역직렬화해줌. 이 때, 기본 생성자를 사용하므로 post에서는 필요함.
public class ProductCreateRequest {

String 검증 시
- @NotNull // "" "   " -> 통과 , enum 시 사용
- @NotEmpty // "   " -> 통과
- @NotBlank // 무조건 있어야 함.
가격
- @Positive

Validation에 대한 책임 분리
- String name -> 상품 이름 20자 제한
  - @Max(20)을 쓰면 됨 but 이걸 여기서 검증하는 것이 맞을까? 
  - 기본적인 문자열, 숫자라면 마땅히 가져야할 validation과, 
    도메인 성격에 맞는 특수한 성격의 validation을 구분할 수 있는 시야를 길러야 함.
  - 그래서 여기서는 최소한의 조건을 validate 해주는 것이 좋다.

컨트롤러 -> 서비스 레이어용 dto ?  
- 서비스 모듈을 분리할 때 => validation 필요x 따라서 의존성을 들고 갈 필요가 없다.
- 작을 때는 귀찮, but 서비스가 커질수록 필요하다.
- 서비스 기능을 사용하면서, web 단이 영향을 받지 않도록 하기 위함.
- 의존성이나 책임 분리 측면에서 훨씬 좋다.

@NoArgsConstructor // post-> objectmapper가 역직렬화해줌. 이 때, 기본 생성자를 사용하므로 post에서는 필요함.

Mockito를 이용해서 Stubbing 해보기
// 테스트할 때 마다 메일을 보내야 하는가? no -> Mockbean + mockito
// stubbing (목 객체에 원하는 행위를 정의하는 것)
when(mailSendClient.sendEmail(any(String.class), any(String.class), any(String.class), any(String.class)))
.thenReturn(true);

mock 객체에 설정 안해주면, 기본값을 리턴함. public static final Answer<Object> RETURNS_DEFAULTS = Answers.RETURNS_DEFAULTS;

@Spy //-> 객체의 일부 기능만 모킹하고, 다른 기능은 원래 대로 작동하도록 할 수 있음.
// -> 실제 객체를 모킹하므로, stubbing이 되지 않음 -> when 사용 x -> do~ 사용 가능
doReturn(true) // stubbing 해주는 기능 명시.
.when(mailSendClient)
.sendEmail(anyString(), anyString(), anyString(), anyString());

//        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
//                .thenReturn(true);
// 모키토를 하나 감쌓는데, BDD 스타일로 이름을 바꾼 것. ==> 앞으로 이렇게 쓸 것
given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
.willReturn(true);

