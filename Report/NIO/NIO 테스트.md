# NIO 테스트

스레드가 10000개 넘어가면 jvm 메모리 에러가 발생한다.

스레드가 1000개만 되어도 현재 데스크탑 cpu에서 과부하가 걸려서 테스트를 못해봤다. 

채널 방법의 문제점

* 서버에서 작동시키는 경우 buffer에 채워야하기 때문에 연속적으로 해당 버퍼를 쓸 경우 문제가 발생했다. 
  * sleep을 통해 해결



![image-20210215110633492](C:\Users\rlawk\AppData\Roaming\Typora\typora-user-images\image-20210215110633492.png)

<요청없이 서버만 돌리는 경우>



![image-20210215110814004](C:\Users\rlawk\AppData\Roaming\Typora\typora-user-images\image-20210215110814004.png)

<요청이 있는 경우 - 클라이언트 thread 100개>



![image-20210215110904394](C:\Users\rlawk\AppData\Roaming\Typora\typora-user-images\image-20210215110904394.png)

큰 차이를 못 느꼈다.









우분투 에러(journal ) -> 가상머신과 우분투 버전의 호환성 문제

https://askubuntu.com/questions/924170/error-on-ubuntu-boot-up-recovering-journal

https://mer1.tistory.com/39

