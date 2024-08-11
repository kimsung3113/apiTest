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
19. 공공데이터 포탈 API 연동 후 exceptionhandler로 exception Custom
## 사업자 등록 진위확인 API Request

호출 URL

<aside>
💡 **https://api.odcloud.kr/api/nts-businessman/v1/validate**

</aside>

API 정보

- 입력한 사업자 정보에 대한 일치여부 제공.
- 1회 호출 시 최대 100개 에 해당하는 사업자 상태정보 요청 가능.
- 일치할경우, valid: 01 및 해당하는 사업자 정보 return.
- 일치하지 않을 경우, valid: 02, valid_msg: 확인할 수 없습니다 return.

| Parameter | Type | Mandatory | Descrption | Example |
| --- | --- | --- | --- | --- |
| (b)businesses | JsonArray | Yes | 사업자 등록 진위확인 API Request | {"businesses": [{"b_no": "0000000000","start_dt": "20000101",...}]} |
| b.b_no | String | Yes | 사업자 등록번호
(숫자로 이루어진 10자리 값만 가능) | 0000000000 |
| b.start_dt | String | Yes | 개업일자 | YYYYMMDD포맷 |
| b.p_nm | String | Yes | 대표자 성명1 | 홍길동 |
| b.p_nm2 | String | No | 대표자 성명 2
대표자성명1 이 한글이 아닌 경우, 이에 대한 한글명 | 홍길동 |
| b.b_nm | String | No(Optional) | 상호명 | (주)테스트 |
| b.corp_no | String | No(Optional) | 법인등록번호 | 0000000000000 |
| b.b_sector | String | No(Optional) | 주업태명 |  |
| b.b_type | String | No(Optional) | 주종목명 |  |
| b.b_adr | String | No(Optional) | 사업장 주소 |  |

## 사업자 등록 진위확인 API Response

### 정상 response

```json
{
  "status_code": "OK",
  "request_cnt": 0,
  "valid_cnt": 0,
  "data": [
    {
      "b_no": "0000000000",
      "valid": "01",
      "valid_msg": "",
      "request_param": {
        "b_no": "0000000000",
        "start_dt": "20000101",
        "p_nm": "홍길동",
        "p_nm2": "홍길동",
        "b_nm": "(주)테스트",
        "corp_no": "0000000000000",
        "b_sector": "",
        "b_type": "",
        "b_adr": ""
      },
      "status": {
        "b_no": "0000000000",
        "b_stt": "계속사업자",
        "b_stt_cd": "01",
        "tax_type": "부가가치세 일반과세자",
        "tax_type_cd": "01",
        "end_dt": "20000101",
        "utcc_yn": "Y",
        "tax_type_change_dt": "20000101",
        "invoice_apply_dt": "20000101",
        "rbf_tax_type": "부가가치세 일반과세자",
        "rbf_tax_type_cd": "01"
      }
    }
  ]
}
```

## **사업자등록 상태조회 API Request**

호출 URL

<aside>
💡 **https://api.odcloud.kr/api/nts-businessman/v1/status**

</aside>

| Parameter | Type | Mandatory | Descrption | Example |
| --- | --- | --- | --- | --- |
| b_no | String | Yes | 사업자 등록번호
(숫자로 이루어진 10자리 값만 가능) | 0000000000,0000000001… |

## **사업자등록 상태조회 API Response**

### 정상 Response

```json
{
  "status_code": "OK",
  "match_cnt": 0,
  "request_cnt": 0,
  "data": [
    {
      "b_no": "0000000000",
      "b_stt": "계속사업자",
      "b_stt_cd": "01",
      "tax_type": "부가가치세 일반과세자",
      "tax_type_cd": "01",
      "end_dt": "20000101",
      "utcc_yn": "Y",
      "tax_type_change_dt": "20000101",
      "invoice_apply_dt": "20000101",
      "rbf_tax_type": "부가가치세 일반과세자",
      "rbf_tax_type_cd": "01"
    }
  ]
}
```

### 공통 Error Response

| ErrorCode | status_code |
| --- | --- |
| 400 | JSON 포맷에 적합하지 않는 요청
{
  "status_code": "BAD_JSON_REQUEST"
} |
| 404 | Not Found Service |
| 411 | 필수 요청 파라미터 누락
{
  "status_code": "REQUEST_DATA_MALFORMED"
} |
| 413 | 요청 사업자번호 100개 초과
{
  "status_code": "TOO_LARGE_REQUEST"
} |
| 500 | Internal Server Error
{
  "status_code": "INTERNAL_ERROR"
} |
