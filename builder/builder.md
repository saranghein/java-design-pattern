# Builder Pattern
+ 여행일정 짤때 처럼 **구조가 복잡할 때**
+ client가 builder에게 위임해서 builder가 생성 
+ 장점: 
  + 복잡한 구조의 생성방법을 encapsulate할 수 있음
  + 내부 생성 구조를 client에게서 hide할 수 있음
+ Factory vs. Builder 
  + Factory는 무엇이 들어가는 지 몰라도 알아서 만들어줌
  + Builder: 내부에 무엇이 들어가는 지 (language, city,...)정도는 알고 있어야 함 
---
## CarBuilder
+ CarBuilder interface를 따로 만들어서 build함
+ SportsCarBuilder, SedanCarBuilder implements CarBuilder에서 build함수들 정의 
  + Car car=new Car(carType: "Sport")를 갖고 있음 
+ CarDirector에서 CarBuilder를 받아서 build함수에서 CarBuilder의 build함수들 실행 
+ main: 
  + CarBuilder carbuilder= new SedanCarBuilder("Sedan")생성
  + CarDirector director=new CarDirector(carbuilder)
  + director.build()후 Car car= carbuilder.getCar(); 

## Form
+ `Form` class내에서 builder class(`FormBuilder`)따로 생성 
+ Form 내에 public static class FormBuilder 생성 
+ 아래처럼 함수 생성 (**`return this;`**)
    ```java
    public FormBuilder address(String address){
        this.address = address;
        return this;
    }
    ```
+ 마지막에는 build함수 작성 `return new Form(this)`
  + Form에 FormBuilder 값을 옮김 
+ Form class생성자 인자값으로 FormBuilder을 받음
  + 생성자 안에서는 `this.firstName = formBuilder.firstName;`
+ main:
  ```java
		Form form = new FormBuilder("Dave", "Carter", "DavCarter", "DAvCaEr123")
                    .passwordHint("MyName")
                    .city("NY")
                    .language("English")
                    .build();
    ```
