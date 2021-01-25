---
typora-copy-images-to: ..\img
typora-root-url: ./
---


typora-copy-images-to: ..\img

# Blocking/Non-Blocking, SYNC/ASYNC

## 개념

#### Synchronous

- 요청과 그 결과가 동시에 일어난다는 뜻이며, 어떤 객체 또는 함수 내부에서 다른 함수를 호출했을 때 이 **함수의 결과를 호출한 쪽에서 처리**하면 동기입니다.

  ![image-20210125111438237](C:\Users\rlawk\Desktop\SpringStudy\Theory\img\image-20210125111438237.png)

#### Asynchronous

* 요청과 그 결과가 동시에 일어나지 않는다는 뜻이며 동기와 달리 어떤 객체 또는 함수 내부에서 다른 함수를 호출했을 때 이 **함수의 결과를 호출한 쪽에서 처리하지 않으면 비동기**입니다.

- ![image-20210125111506455](C:\Users\rlawk\Desktop\SpringStudy\Theory\img\image-20210125111506455.png)

- 여기서 foo()함수를 setTimeout()함수의 **callback**함수라 부른다.

- **콜백 함수**:  비동기 방식에서 **어떤 Event(foo)가 발생 했을 때 수행해야 할 함수**를 의미.

- 이처럼 비동기 방식에서는 함수를 호출한 쪽에서 수행 결과를 직접 처리하지 않고 콜백 함수를 통해 수행 결과를 처리한다.

#### Blocking

- **블로킹**은 자신의 수행결과가 끝날 때까지 제어권을 갖고 있는 것을 의미한다. 
  - 해당 프로세스의 작업은 중단되고 **WAIT**상태가 된다. 

#### Non-Blocking

- **논블로킹**은 자신이 호출되었을 때 제어권을 바로 자신을 호출한 쪽으로 넘기며, 자신을 호출한 쪽에서 다른 일을 할 수 있도록 하는 것을 의미합니다
  - 해당 프로세스의 가 호출하면 바로 응답을 준다. (작업이 끝났는지 여부와 관련없이)

## 사용

![image-20210125112408888](/../img/image-20210125113022194.png)





#### 참조

https://victorydntmd.tistory.com/8