# State pattern 
+ 상태를 별도의 객체로 -> 상태의 객체화 
---
## Gumball State
+ State - interface
  + insertQuarter()
  + ejectQuarter()
  + turnCrank()
  + dispense()
+ HasQuarterState implements State
  + turnCrank()
    + SoldState으로 전환     
    + `gumballMachine.setState(gumballMachine.getSoldState());`
  + ejectQuarter()
    + NoQuarterState로 전환 
    + `gumballMachine.setState(gumballMachine.getNoQuarterState());`
+ SoldState
  + dispense()
    + gumballMachine.gumball() : count --;
    + gumballMachine.getCount()>0 이면 NoQuarterState 전환
    + else SoldOutState전환 
+ GumballMachine
  + State 4개 + 현상태 State 1개 + int count
  + State state=soldOutState로 초기화
  + 생성자에서 new HasQuarter(this), ...로 4개 State 초기화 
  + numberGumballs>0이면 state= noQuarterState 
  + insertQuarter(): state.insertQuarter()
  + ejectQuarter(): state.ejectQuarter()
  + turnCrank(): state.turnCrank() -> state.dispense() 
  + releaseBall(): count !=0 이면 count --;
+ main
  + GumballMachine gumballMachine = new GumballMachine(5);
    ```java
    gumballMachine.insertQuarter();
    gumballMachine.turnCrank();
    gumballMachine.insertQuarter();
    gumballMachine.turnCrank();
    //count: 5-> 3
    ```
---
## Winner State 추가
+ HasQuarterState의 turnCrank()변화
+ GumballMachine에 WinnerState추가
+ WinnerState
  + dispense()
    + gumballMachine.releaseBall() 
    + count==0이면 SoldOutState변환
    + gumballMachine.releaseBall() 
    + count > 0이면 NoQuarterState 변환
    + else SoldoutState 변환
+ HasQuarterState
  + Random randomWinner = new Random(System.currentTimeMillis());
  + turnCrank()
    + int winner = randomWinner.nextInt(10);
    + winner을 0으로함
    + winner ==0이고 count > 1 이면 WinnerState으로 바꿈 
    + else SoldState로 바꿈 
+ GumballMachine
  + State 1개 추가하고 생성자에서 new WinnerState(this) 