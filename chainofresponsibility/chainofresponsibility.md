# Chain of Responsibility
+ 여러 개의 역할(responsibility, handler)을 chain으로 연결한 것 
+ handler 가 유동적 
+ handlerRequest가 처리하지 못하면 successor의 handlerRequest를 수행의 반복
+ 장점
  + chain의 구조를 client가 알 필요가 없음
  + 중간의 handler를 dynamic하게 바꿀 수 있음(add or remove responsibiltiy)
  + request, receiver분리 
+ 단점
  + 끝까지 갔는데 처리가 안되면 어디서 error 발생했는 지 알 수 없음 -> 처리할 수 있도록 함
  + debug하기 어려움 
+ Uses
  + window의 event에 대한 처리
 ---
## 구현 내용
+ Handler interface: setHandler(Handler handler), process(File file), getHandlerName() 
  + process()는 handlerRequest() 
+ TextFileHandler, AudioFileHandler , ... implements Handler 
  + process()에서는 `file.getFileType().equals("text")` 이면 결과 출력,
    + else if handler!=null이 아니면 다음 handler 호출(`handler.process(file);`)
    + else null이면 unsupportfiletype 
  + setHandler()에서는 처리가 안되면 위임할 handler객체를 저장한다. 
+ main
  + TextFileHandler, AudioFileHandler, ...객체들을 생성 =new TextFileHandler(handlerName: " ")
  + chain 생성 (textHandler -> docHandler -> excelHandler -> ...)
    ```java
    textHandler.setHandler(docHandler);
    docHandler.setHandler(excelHandler);
    //...
    ```
  + process()를 통해 handler순차적 실행
    ```java
    file = new File(filName: "Abc.mp3", fileType: "audio", filePath: "C:");
    textHandler.process(file);
    ```

