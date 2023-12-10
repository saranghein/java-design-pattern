# Mediator Pattern
+ subSystem들을 mediator들로 연결
+ facade pattern과 비슷
+ 중앙집중식 
+ class 가 변하면 mediator만 변경하면 됨 
+ 모든 class가 mediator와 관계 
---
## 구현 내용
+ MachineMediator - interface
  + start(), wash(), open(), closed(), ... 
+ CottonMediator implements MachineMediator
  + Machine machine, Heater heater, ... 
  + start(), ...함수 구현 
+ Colleague - interface 
  + setMediator(MachineMediator mediator);
+ Button implements Colleague
  + MachineMediator mediator
  + setMediator(MachineMediator mediator);
  + press()
    + mediator.start()
+ Heater implements Colleague
  + MachineMediator mediator
  + setMediator(MachineMediator mediator);
  + on()
  + off()
      + mediator.wash()
+ main
  + 모든 객체 생성
  + mediator = new CottonMediator(machine, heater, motor, sensor, soilRemoval, valve);
  + button.setMediator(mediator);
    machine.setMediator(mediator);
  + button.press();
  