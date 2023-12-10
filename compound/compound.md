# Compound pattern 
+ 배웠던 여러 개의 pattern 혼합

---
## Ducks
+ 공통된 부분은 interface, 구체적인 구현 내용은 implements로 구현 
+ Quackable-interface
  + quack() 
+ MallardDuck, RegheadDuck, ... implements Quackable
  + quack()
+ main
    ```java
    Quackable mallardDuck=new MallardDuck();
    simulate(mallardDuck);
    //void simulate(Quackable duck){duck.quack()}
    ```
---
## Adapter pattern
+ honk() -> quack()으로 연결함 
+ Goose()
  + honk()
+ GooseAdapter()
  + Goose()를 받아 quack()함수에서 goose.honk();로 구현함 
+ main
  + Adapter로 씌워주기만 하면 됨 
      ```java
    Quackable gooseDuck = new GooseAdapter(new Goose());
    simulate(gooseDuck);
      ```
---
## Decorator pattern
+ 몇 번 울었는 지 count
+ QuackCounter implements Quackable
  + Quackable duck,int numberOfQuacks
    + 생성자에서 duck 입력받음
  + quack()
    + duck.quack()를 호출하고 numberOfQuacks++;
+ main
  + `Quackable mallardDuck = new QuackCounter(new MallardDuck());`로 QuackCounter로 씌움 
  + `System.out.println(QuackCounter.getQuacks());` 로 울음 횟수 count 가능 
---
## Factory pattern
+ main 함수에 new로 객체를 생성하지 않도록 AbstractDuckFactory 생성
+ AbstractDuckFactory - abstract
    ```java
    public abstract Quackable createMallardDuck();
    public abstract Quackable createRedheadDuck();
    public abstract Quackable createDuckCall();
    public abstract Quackable createRubberDuck();
    ```
+ CountingDuckFactory extends AbstractDuckFactory
  + counting 기능도 포함해서 객체 생성 
    ```java
    public Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }
    //...
    ```
+ main
  + factory를 이용해 생성한다.
  + 오리 객체 코드가 바뀌어도 main코드는 바뀌지 않는 다 
```java
public static void main(String[] args) {
    DuckSimulator simulator = new DuckSimulator();
    AbstractDuckFactory duckFactory = new CountingDuckFactory();
    simulator.simulate(duckFactory);
}
void simulate(AbstractDuckFactory duckFactory){
    Quackable mallardDuck=duckFactory.createMallardDuck();
    //...
    simulate(mallardDuck);
}
void simulate(Quackable duck) {
    duck.quack();
}
```
---
## Composite & Iterator pattern
+ 여러개의 object를 다룰 때 
+ 여러개의 Quackable을 다루는 Flock
+ Flock implements Quackable
  + 여러 개의 quackers 를 ArrayList로 다룸
  + add()
    + Quackable을 받아 ArrayList에 add
  + quack()
    + Iterator을 사용해 Quackable quacker의 quack()호출 
    + Quackable 로 typecasting 필요 
+ main
  + 생성한 Quackable들을 Flock에 add하고 simulate 
  + 같은 종류의 Quackable, 다른 종류의 Quackable도 Flock으로 다룰 수 있음
    ```java
    void simulate(AbstractDuckFactory duckFactory) {
        Quackable redheadDuck = duckFactory.createRedheadDuck();
        //...
        Quackable gooseDuck = new GooseAdapter(new Goose());
        
        Flock flockOfDucks = new Flock();
        
        flockOfDucks.add(redheadDuck);
        flockOfDucks.add(gooseDuck);
        
        Flock flockOfMallards = new Flock();
        
        Quackable mallardOne = duckFactory.createMallardDuck();
        //...
        Quackable mallardFour = duckFactory.createMallardDuck();
        
        flockOfMallards.add(mallardOne);
        //...
        flockOfMallards.add(mallardFour);
        flockOfDucks.add(flockOfMallards);
        simulate(flockOfDucks);
        simulate(flockOfMallards);
    ```
---
## Observer pattern
+ 객체가 quack() 하면 observer에 update() 를 호출(알리고)싶을 때

**Observer**
+ QuackObserable - interface
  + registerObserver(Observer observer)
  + notifyObservers()
+ Quackable -interface extends QuackObserable
  + quack()
+ MallardDuck implements Quackable
  + Obserable obserable
  + registerObserver, notifyObservers() 추가됨 
  + **quack()하면 notifyObservers()호출**
  + notifyObserver()
    + obserable의 notifyObservers() 호출 
  + registerObserver(Observer observer)
    + obserable의 registerObserver(observer)을 호출 
+ Flock implements Quackable
  + registerObserver(Observer observer)
    + ArrayList의 ducks을 Iterator을 통해 duck.registerObserver(observer)호출 
+ Obserable implements QuackObserable
  + QuackObservable duck , observers을 ArrayList로 받음 
  + registerObserver(Observer observer)
    + observer를 observers에 add 
  + nodifyObserver()
    + Iterator 사용해 observers의 observer.update()호출 
    + Observer로 type casting 필요 

**Subject**
+ Observer - interface
  + update(QuackObserable duck)
+ Quackologist implements Observer
  + update(QuackObserable duck)
    + duck을 출력함 

+ main
    ```java
    void simulate(AbstractDuckFactory duckFactory) {
        Quackologist quackologist = new Quackologist();
        flockOfDucks.registerObserver(quackologist);
        simulate(flockOfDucks);//simulate하면 quack()호출하면서 notify가 됨
  } 
    ```