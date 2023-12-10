# Proxy pattern
protection proxy, remote proxy, virtual proxy
## remote proxy
### java RMI(RemoteMethodInvocation)
+ 분산객체
+ 서로 다른 host객체가 Java로 개발돼 있어야 함 
+ ORB의 축소판
+ IDL 필요없음
+ Proxy 자동 생성
+ server 객체를 만들면 STUB, SKELETON 자동생성, Naming.rebind()
+ client 객체에서는 service 객체가 interface 로 보임 , Naming.lookup()
1. ```shell
   rmic MyRemoteImpl
   # STUB, SKELETON 생성 
   ```
2. ```shell 
   rmiregistry
   ```
3. ```shell
   java MyRemoteImpl
   ```
+ MyRemote extends Remote - interface
  + Remote는 marker interface 
  + String sayHello()throws RemoteException;
+ MyRemoteImpl extends UnicastRemoteObject implements MyRemote
  + main
    + MyRemote service=new MyRemoteImpl();
      Naming.rebind("RemoteHello",service);
    + try, catch 필요 
+ MyRemoteClient 
  + main
    + new MyRemoteClient().go();
  + go()
    + try, catch문 필요
    ```java
    MyRemote service =(MyRemote) Naming.lookup("rmi://127.0.0.1/RemoteHello");
    String s=service.sayHello();
    System.out.println(s);
    ```
### GumballMachine
+ clent: GumballMonitor
+ server: GumballMachine, GumballMachineRemote
+ GumballMachine extends UnicastRemoteObject implements GumballMachineRemote
  + String location, getLocation()추가
+ GumballMachineRemote extends Remote - interface 
  + getCount() throws RemoteException;
  + getState() throws RemoteException; => Serialization(직렬화) 필요 
  + getLocation() throws RemoteException;
+ State extends Serializable - interface 
```shell
rmiregistry
java GumballMachineTestDrive seattle.mightgumball.com 100
```
---
## Virtual Proxy 
+ printer, lazy evaluation, eager evaluation
+ 객체의 크기가 클때, 실제 객체를 생성하지 않고 간단한 응답을 할 수 있는 proxy를 만드는 것
+ 객체가 불여불급하게 만들어지면 그 객체에 역할을 delegate 
+ ImageIcon이 없으면 ImageProxy에서 그대로 호출, 있으면 ImageIcon에서 호출 
+ ImageProxy implements Icon
  + ImageIcon imageIcon;URL imageURL; Thread retrievalThread; boolean retrieving = false;
  + 생성자에서 imageURL 받음 
  + int getIconWidth()
    + imageIcon!=null이면 return imageIcon.getIconWidth()
    + null이면 default값으로 800 return
  + public int getIconHeight()
    + getIconWidth()와 같음 
  + paintIcon(final Component c, Graphics  g, int x,  int y)
    + imageIcon != null이면 imageIcon.paintIcon(c, g, x, y)
    + null 이면 imageURL로 imageIcon을 생성하고 thread 시작