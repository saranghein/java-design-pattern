# Factory pattern
+ 생성에 관련한 것을 별도로 두는 것
---
## Factory class
+ SimplePizzaFactory
  + Pizza createPizza(String type)
    + Pizza pizza=null;
    + type에 따라 어떤 피자를 생성할 지(`type.equals("cheese")`)
    + new CheesePizza(), new PepperoniPizza(), ... 
    + return pizza;
+ Pizza - abstract
  + name, dough, topping-ArrayList, sauce
  + prepare(), bake(), ...
+ CheesePizza extends Pizza
  + 생성자에서 name, dough, ... 등등을 지정
+ PizzaStore 
  + SimplePizzaFactory simplePizzaFactory
  + orderPizza(String type)
    + Pizza pizza를 simplePizzaFactory.createPizza(type)로 생성하고
    + pizza의 메서드 호출함 
    + return pizza;
+ main
  + SimpleFactory생성
  + new PizzaStore(simpleFactory)
  + Pizza pizza=pizzaStore.orderPizza("cheese")
---
## Factory Method
+ 클래스가 아닌 메소드로 구현하는 방법
+ subclass(NYPizzaStore)에 어떤 object(NYPizza)를 생성할 지 결정하도록 함 
+ PizzaStore class내에 createPizza 생성
+ PizzaStore - abstract
  + createPizza(Stirng type) - abstract
  + orderPizza
+ NYPizzaStore extends PizzaStore 
  + createPizza(String type)
    + type에 따라 return new NYStyleCheesePizza(), ... 생성 
+ main
  + PizzaStore, Pizza만 다룸 (implementation과 independence)
  + PizzaStore nyStore = new NYPizzaStore();
  + Pizza pizza = nyStore.orderPizza("cheese");
  + Factory class와 다르게 SimpleFactory를 사용하지 않음 
+ 종속 역정원리
  + depend upon abstractions
  + do not depend upon concrete classes
---
## Abstract Factory
+ Factory들을 상위에 묶어두는 것 
+ NYPizzaStore에서는 interface만 다룸
+ 구체적으로 어느 Dough, Sauce 등을 쓸건지는 PizzaIngredientFactory에서 다룸 
+ Factory class를 바꾸기 위해서는 PizzaIngerFac으로 다룸 
+ Cheese, Sauce, ... - interface
+ ReggianoCheese implements Cheese
+ PizzaIngreFac - interface
  + Dough createDough(), Sauce createSauce(),...
+ NYPizzaIngerFac implements PizzaIngreFac
    ```java
    public Dough createDough() {
            return new ThinCrustDough();
        }
  //...
    ```
+ PizzaStore - abstract
  + orderPizza(type)
    + Pizza pizza=createPizza(type)
    + pizza.prepare() 
    + pizza.bake()
    + return pizza;
  + createPizza - abstract 
+ NYPizzaStore extends PizzaStore
  + createPizza(type)
    + PizzaIngredientFactory ingredientFactory = new NYPizzaIngredientFactory();
    + type에 따라 Pizza를 생성한다.
    + pizza = new CheesePizza(ingredientFactory);
+ CheesePizza extends Pizza
  + PizzaIngreFac pizzaIngreFac
  + 생성자로 pizzaIngreFac을 받음 
  + prepare()
    + dough = ingredientFactory.createDough(); 처럼 재료를 생성하도록 한다.
+ Pizza - abstract
  + prepare() - abstract
+ main
  + PizzaStore pizzaStore=new NYPizzaStore();
  + Pizza pizza=pizzaStore.orderPizza("cheese");