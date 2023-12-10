# Prototype pattern 
+ 객체를 복제해서 생성 
+ 게임 캐릭터 처럼 초기 설정이 있을 때 
+ clone() 사용 
+ copy
  + deep copy: 완전히 다른 객체, 변경에 따른 다른 객체에 영향 X => cloning
  + shallow copy: 같은 객체인데 이름이 여러개 
+ 내용이 중복되므로 복사해서 사용
+ AccessControl객체를 한 개만 만들어 복사해 사용
+ 장점: 복잡성 hide가능, new 보다는 복사하는 것이 효율적
+ 단점: 한 level에 대해서만 clone 되므로 하위까지 연쇄적으로 clone해줘야 함 
---
## 구현 내용
+ Prototype extends Cloneable - interface
  + Cloneable: mark 
  + AccessControl clone() throws CloneNotSupportedException;
+ AccessControl implements Prototype
  + controlLevel, access
  + clone()
    + return (AccessControl)super.clone()
    + super.clone()은 Object type이므로 type casting 필요 
    + try, catch문 사용 (CloneNotSupportedException e)
+ AccessControlProvider
    ```java
        private static Map<String, AccessControl>map = new HashMap<String, AccessControl>();
    static{
            map.put("USER", new AccessControl("USER","DO_WORK"));
            map.put("ADMIN", new AccessControl("ADMIN","ADD/REMOVE USERS"));
            //...
    ```
  + static AccessControl getAccessControlObject(String controlLevel)
    + AccessControl ac 를 만들고
    + ac=map.get(controlLevel)로 map의 내용(new AccessContol)을 가져옴
    + ac != null이면 return ac.clone() 
+ main
  + AccessContolProvide.getAccessControlObject 사용해 얻은 userAccessControl을 user에 넣어줌 
  + getAccessControl을 이용해 변경(setAccess)도 가능 
      ```java
      userAccessControl = AccessControlProvider.getAccessControlObject("USER");
      user = new User("User B", "USER Level", userAccessControl);
      System.out.println("Changing access control of: "+user.getUserName());
      user.getAccessControl().setAccess("READ REPORTS");
      ```