# Bridge Pattern
+ Abstraction -(has-A) -> implementation
+ 사용처: 자동차 회사마다 다른 System, 다른 종류의 부품


+ 장점
  + implementation과 abstraction이 독립적
  + abstrac이 변경되면 imple입장에서는 변하는 것이 없음 
+ Uses
  + Window에서 어떤 System(multiple platforms) 을 사용할 지 결정(mac, windows)
  + 다른 구현 방법으로 interface를 다양하게 구형할 수 있음 

---
## 구현 내용
+ Abstraction : Car
  + child: BigWheel, Motoren
+ interface: Product
  + imple: CentrallLocking, GearLocking


+ Car -(has-A)-> Product


+ Car: Product product, String carType, assemble()-Abstrac, produceProduct()-Abstrac, printDetails()
  + BigWheel: Product product, String carType, assemble(), produceProduct()
  + Motoren: Product product, String carType, assemble(), productProduct()
  + BigWheel, Motoren 생성자에는 `super(product, carType);`도 넣기 
+ Product: productName(), product()
  + CentralLocking: productName(), produce()
  + GearLocking: productName(), produce()

+ Client: product를 만들고 생성한 product를 Car = new Motoren 생성자에 넣기 