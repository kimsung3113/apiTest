# apiTest
sunghun API project test

SpringBoot 3.1.11 version -> 3.2.6 version upgrade
mySql 8.0.32 version


1. JPA 사용 DB 연동 test(@query)
2. Junit 단위테스트
3. Jacoco 코드 커버리지 test
4. 서버 2개로 RestTemplate 통신 test
5. http 통신 메서드 API방식 test
6. 인터셉터 적용
7. custom Exception, @RestControllerAdvice 적용
8. docker-compose파일로 image 올려 실행(db, redis)
9. property key Jasypt로 암호화
10. profile로 local, dev properties 분리
11. devtools, swagger, postman으로 API test
12. custom annotation 생성 및 test
13. gitignore 파일 추가
14. maven -> gradle로 변경하며 에러 해결(호환성, Junit(TDD), Jacoco)
15. 내가 만든 header와 resttemplate response type 동적으로 변경할 수 있게 구조와 코드 변경 후 test 완료.
16. 내가 만들어놓은 서버에서 httpServletRequst로 Enumeration해서 읽어오기 vs. @RequestHeader test
17. DTO를 inner class들과 추상클래스들로 만들어 구조 깨끗하게 변경 후 test중.
18. DB와 redis Docker에 올리고 Server 프로젝트 구축 후 Docker 컨테이너에 올려 실행 test 완료
