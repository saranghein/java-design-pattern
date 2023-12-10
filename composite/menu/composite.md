# Composite pattern
+ tree구조에 사용 (tree는 recursive 구조) <-> decorate pattern
+ iterator도 사용
+ compose objects into tree structures
+ client는 객체들을 unifomly하게 다룰 수 있도록 함 
+ root, 중간 노드, leaf를 다루게 됨
+ 중간 노드, leaf는 client입장에서 모두 component
---
## 구현 내용
+ Waitress -> MenuComponent(abstract)
+ Menu(SubMenu, 중간 노드), MenuItem(leaf 노드) extends MenuComponent 
+ Waitress는 모든 메뉴를 다루고 print만 함
+ MenuComponent
  + leaf에서는 getChild하면 안되므로 `throw new UnsupportedOperationException();`
+ Menu
  + menuComponents, name, description
  + menuComponents를 ArrayList로 받음 
  + add(MenuComponent menuComponent), remove(MenuComponent menuComponent) , getChild(int i)
    + getChild에서는 type casting 필요: `(MenuComponent)menuComponents.get(i);`
  + print()에서는 iterator를 사용해 menuComponents의 print() 수행
    + type casting 필요
+ MenuItem
  + name, description, vegetarian, price
  + print()에서는 get함수를 통해 내용 출력 
+ Waitress
  + menuItem을 받아 저장
  + print하면 menuItem의 print()수행 
+ main
  + `MenuComponent pancakeHouseMenu =
    new Menu(name: "PANCAKE HOUSE MENU", description: "Breakfast");` 처럼 dinnermenu, cafemenu, coffemenu등을 생성 
  + Waitress가 다룰 allMenu도 생성 
  + allMenu에 중간 노드 add
    ```java
    allMenus.add(pancakeHouseMenu);
    allMenus.add(dinerMenu);
	allMenus.add(cafeMenu);
    ```
  + 중간 노드에 leaf 노드들 추가
    ```java
    pancakeHouseMenu.add(new MenuItem(
        "K&B's Pancake Breakfast",
        "Pancakes with scrambled eggs, and toast",
        true,
        2.99));
    dinerMenu.add(new MenuItem(
            "Vegetarian BLT",
            "(Fakin') Bacon with lettuce & tomato on whole wheat",
            true,
            2.99));
    //...
    ```
  + 중간 노드(dinnerMenu)에 다른 노드 add도 가능(`dinerMenu.add(dessertMenu);`)
  + 추가한 dessertMenu에 leaf 노드 추가 
    ```java
    dessertMenu.add(new MenuItem( //...
    ```
  + 생성한 tree를 Waitress에 넣고 출력
    ```java
    Waitress waitress = new Waitress(allMenus);
    waitress.printMenu();
    ```