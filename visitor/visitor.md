# Visitor pattern
+ 여러 개의 visitor
+ ConcreteElement가 2개면 ConcreteVisitor 2개 
+ visitor는 방문하는 노드의 type에 따라 처리방법을 다르게 함 
+ 장점: 
  + 구조를 변경하지 않고 composite구조에 add operation
  + adding operation easy
+ 단점
  + 각 노드에 대해 accept 함수가 있어야 함 
  + broken encapsulation: 외부에서 변경하므로
---
## 구현 내용
+ Visitor - interface 
  + visit(HtmlElement element);
  + visit(HtmlParentElement parentElement);
+ StyleVisitor implements Visitor
    ```java
    public void visit(HtmlElement element) {
        element.setStartTag(element.getStartTag().replace(">", " style='width:46px;'>"));
    }
    public void visit(HtmlParentElement parentElement) {
        parentElement.setStartTag(parentElement.getStartTag().replace(">", " style='width:58px;'>"));
    }
    ```
+ CssClassVisitor implements Visitor
    ```java
    public void visit(HtmlElement element) {
        element.setStartTag(element.getStartTag().replace(">", " class='visitor'>"));
    }
    public void visit(HtmlParentElement parentElement) {
        parentElement.setStartTag(parentElement.getStartTag().replace(">", " class='visitor'>"));
    }
    ```
+ Element - interface
  + accept(Visitor visitor);
+ HtmlTag implements Element - abstract
  + abstract void setStartTag(String tag), ...
  + child 에서는 getChildren을 할 수 없으므로 exception 처리 
    ```java
    public void addChildTag(HtmlTag htmlTag){
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }
    public List<HtmlTag>getChildren(){
        throw new UnsupportedOperationException("Current operation is not support for this object");
    }
    ```
+ HtmlParentElement extends HtmlTag
  + 부모 tag
  + 생성자 인자는 tagName
  + private List<HtmlTag>childrenTag, tagName, startTag, endTag
  + generateHtml()
    + for문으로 childrenTag의 tag.generateHtml()
  + accept(Visitor visitor) { visitor.visit(this); }
+ HtmlElement extends HtmlTag
  + 생성자 인자는 tagName  
  + tagBody, tagName, startTag, endTag
  + accept(Visitor visitor) { visitor.visit(this); }
    + 변환하려면 해당node가 visitor의 accept 호출 
+ main
  ```java
    Visitor cssClass = new CssClassVisitor();
    Visitor style = new StyleVisitor();
    parentTag = new HtmlParentElement("<html>");
    parentTag.setStartTag("<html>");
    parentTag.setEndTag("</html>");
    parentTag.accept(style);
    parentTag.accept(cssClass);
    ```