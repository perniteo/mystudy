// 루트 패키지에 소속된 경우에는 package를 지정할 필요가 없다.

class Test2 {
  // 다른 패키지에 소속된 경우에는 반드시 패키지명까지 적어야 한다.
  //com.eomcs.F1 v1;
  //com.eomcs.F2 v2; 
  // 컴파일 오류

  com.eomcs.G1 v3;
  //com.eomcs.G2 v4; //  공개되지 않은 클래스는 같은 패키지 소속 클래스만 접근 가능
}
