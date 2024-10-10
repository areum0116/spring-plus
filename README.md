# SPRING PLUS 심화 주차 개인 과제

## 과제 기대 역량
- 복잡한 데이터 모델링
- 성능 최적화
- **스프링 시큐리티**의 심화 기능을 활용한 사용자 권한 관리
- **AWS**의 다양한 서비스를 연동한 **확장성과 안정성 높은 시스템 구축**

## 요구 사항

## Level. 1
### **1. 코드 개선 퀴즈 - @Transactional의 이해**
- 할 일 저장 기능을 구현한 API(`/todos`)를 호출할 때, 아래와 같은 에러가 발생하고 있어요.
    
![image](https://github.com/user-attachments/assets/ca747171-9f73-46fd-82e8-0633b5e2c2cf)
    
jakarta.servlet.ServletException: Request processing failed: org.springframework.orm.jpa.JpaSystemException: could not execute statement [Connection is read-only. Queries leading to data modification are not allowed] [insert into todos (contents,created_at,modified_at,title,user_id,weather) values (?,?,?,?,?,?)]
        
- 에러가 발생하지 않고 정상적으로 할 일을 저장 할 수 있도록 코드를 수정해주세요.

### **2. 코드 추가 퀴즈 - JWT의 이해**
- User의 정보에 nickname이 필요해졌어요.
    - User 테이블에 nickname 컬럼을 추가해주세요.
    - nickname은 중복 가능합니다.
- 프론트엔드 개발자가 JWT에서 유저의 닉네임을 꺼내 화면에 보여주길 원하고 있어요.

### **3. 코드 개선 퀴즈 - AOP의 이해**

![image](https://github.com/user-attachments/assets/4b871e23-1721-45c3-9d72-b60f964ca802)

<aside>
😱 AOP가 잘못 동작하고 있어요!
</aside>

- `UserAdminController` 클래스의 `changeUserRole()` 메소드가 실행 전 동작해야해요.
- `AdminAccessLoggingAspect` 클래스에 있는 AOP가 개발 의도에 맞도록 코드를 수정해주세요.

### **4. 테스트 코드 퀴즈 - 컨트롤러 테스트의 이해**

![image](https://github.com/user-attachments/assets/e4dfca8f-7997-43ab-bbcb-c74b39b766ed)

- 테스트 패키지 `org.example.expert.domain.todo.controller`의 
`todo_단건_조회_시_todo가_존재하지_않아_예외가_발생한다()` 테스트가 실패하고 있어요.

![image](https://github.com/user-attachments/assets/d5136f5e-f19b-4cfd-b59a-5909fc71ed4d)

- 테스트가 정상적으로 수행되어 통과할 수 있도록 테스트 코드를 수정해주세요.

### **5. 코드 개선 퀴즈 -  JPA의 이해**

![image](https://github.com/user-attachments/assets/557b20d8-1294-4dc3-950a-4cf5e029dc82)

<aside>
🚨 기획자의 긴급 요청이 왔어요!
아래의 요구사항에 맞춰 기획 요건에 대응할 수 있는 코드를 작성해주세요.
</aside>

- 할 일 검색 시 `weather` 조건으로도 검색할 수 있어야해요.
    - `weather` 조건은 있을 수도 있고, 없을 수도 있어요!
- 할 일 검색 시 수정일 기준으로 기간 검색이 가능해야해요.
    - 기간의 시작과 끝 조건은 있을 수도 있고, 없을 수도 있어요!
- JPQL을 사용하고, 쿼리 메소드명은 자유롭게 지정하되 너무 길지 않게 해주세요.

## Level. 2
### **6. JPA Cascade**
![image](https://github.com/user-attachments/assets/3ee8b6a7-f5f2-470c-b692-ed7bd37ac5e1)

<aside>
🤔 앗❗ 실수로 코드를 지웠어요!
</aside>

- 할 일을 새로 저장할 시, 할 일을 생성한 유저는 담당자로 자동 등록되어야 합니다.
- JPA의 `cascade` 기능을 활용해 할 일을 생성한 유저가 담당자로 등록될 수 있게 해주세요.

### **7. N+1**

![image](https://github.com/user-attachments/assets/a1084cca-1853-4e08-abd0-7800d1234d42)

- `CommentController` 클래스의 `getComments()` API를 호출할 때 N+1 문제가 발생하고 있어요. N+1 문제란, 데이터베이스 쿼리 성능 저하를 일으키는 대표적인 문제 중 하나로, 특히 연관된 엔티티를 조회할 때 발생해요.
- 해당 문제가 발생하지 않도록 코드를 수정해주세요.

### **8. QueryDSL**

![image](https://github.com/user-attachments/assets/df9b8e15-607e-45d1-9992-e9836550586e)

TodoService.getTodo 메소드

- JPQL로 작성된 `findByIdWithUser` 를 QueryDSL로 변경합니다.
- 7번과 마찬가지로 N+1 문제가 발생하지 않도록 유의해 주세요!

### **9. Spring  Security**

<aside>
⚙️

Spring Security를 도입하기로 결정했어요!

</aside>

- 기존 `Filter`와 `Argument Resolver`를 사용하던 코드들을 Spring Security로 변경해주세요.
    - 접근 권한 및 유저 권한 기능은 그대로 유지해주세요.
    - 권한은 Spring Security의 기능을 사용해주세요.
- 토큰 기반 인증 방식은 유지할 거예요. JWT는 그대로 사용해주세요.

## Level. 3

### **10. QueryDSL 을 사용하여 검색 기능 만들기**

<aside>
👉 일정을 검색하는 기능을 만들고 싶어요!
검색 기능의 성능 및 사용성을 높이기 위해 QueryDSL을 활용한 쿼리 최적화를 해보세요.
❗Projections를 활용해서 필요한 필드만 반환할 수 있도록 해주세요❗

</aside>

- 새로운 API로 만들어주세요.
- 검색 조건은 다음과 같아요.
    - 검색 키워드로 일정의 제목을 검색할 수 있어요.
        - 제목은 부분적으로 일치해도 검색이 가능해요.
    - 일정의 생성일 범위로 검색할 수 있어요.
        - 일정을 생성일 최신순으로 정렬해주세요.
    - 담당자의 닉네임으로도 검색이 가능해요.
        - 닉네임은 부분적으로 일치해도 검색이 가능해요.
- 다음의 내용을 포함해서 검색 결과를 반환해주세요.
    - 일정에 대한 모든 정보가 아닌, 제목만 넣어주세요.
    - 해당 일정의 담당자 수를 넣어주세요.
    - 해당 일정의 총 댓글 개수를 넣어주세요.
- 검색 결과는 페이징 처리되어 반환되도록 합니다.

### **11. Transaction 심화**

<aside>
👉 매니저 등록 요청 시 로그를 남기고 싶어요!
`@Transactional`의 옵션 중 하나를 활용하여 매니저 등록과 로그 기록이 각각 독립적으로 처리될 수 있도록 해봅시다.

</aside>

- 매니저 등록 요청을 기록하는 로그 테이블을 만들어주세요.
    - DB 테이블명: `log`
- 매니저 등록과는 별개로 로그 테이블에는 항상 요청 로그가 남아야 해요.
    - 매니저 등록은 실패할 수 있지만, 로그는 반드시 저장되어야 합니다.
    - 로그 생성 시간은 반드시 필요합니다.
    - 그 외 로그에 들어가는 내용은 원하는 정보를 자유롭게 넣어주세요.

### **12. AWS 활용 마스터**

<aside>
👉 EC2, RDS, S3를 사용해서 프로젝트를 관리하고 배포합니다.
각 AWS 서비스 간 보안 그룹을 적절히 구성하여 보안에 신경써주세요!

</aside>

**공통사항**

- 각 AWS 서비스의 콘솔에서 내가 만든 서비스들의 설정 화면을 캡쳐하여 `README.md`에 첨부하세요.

**12-1. EC2**

- EC2 인스턴스에서 어플리케이션을 실행하세요.
- 탄력적 IP를 설정해서 외부에서도 접속할 수 있도록 해주세요.
- 서버 접속 및 Live 상태를 확인할 수 있는 health check API를 만들고 `README.md` 에 기재하세요.
    - health check API는 누구나 접속 가능해야 해요.
    - API path는 편하게 정해도 괜찮습니다.

**12-2. RDS**

- RDS에 데이터베이스를 구축하고, EC2에서 실행되는 어플리케이션에 연결하세요.

**12-3. S3**

- S3 버킷을 생성하여 유저의 프로필 이미지 업로드 및 관리 API를 구현하세요.

### **13. 대용량 데이터 처리**

<aside>
❗ RDS를 사용할 경우 과금이 될 수 있어요. 개인 환경에서 테스트해보시는 것을 추천해요.

</aside>

- 대용량 데이터 처리 실습을 위해, 테스트 코드로 유저 데이터를 100만 건 생성해주세요.
    - 데이터 생성 시 닉네임은 랜덤으로 지정해주세요.
    - 가급적 동일한 닉네임이 들어가지 않도록 방법을 생각해보세요.
- 닉네임을 조건으로 유저 목록을 검색하는 API를 만들어주세요.
    - 닉네임은 정확히 일치해야 검색이 가능해요.
- 여러가지 아이디어로 유저 검색 속도를 줄여주세요.
    - 조회 속도를 개선할 수 있는 여러 방법을 고민하고, 각각의 방법들을 실행해보세요.
    - `README.md` 에 각 방법별 실행 결과를 비교할 수 있도록 최초 조회 속도와 개선 과정 별 조회 속도를 확인할 수 있는 표 혹은 이미지를 첨부해주세요.
 

## [트러블슈팅]
[SpringBootTest와 ContextConfiguration 관련 트러블슈팅](https://coder-areum.tistory.com/100)

## EC2 인스턴스 설정
![image](https://github.com/user-attachments/assets/3d606754-181d-4058-b2d6-2f71292159fb)

## RDS 설정
![image](https://github.com/user-attachments/assets/1bc5f9cc-f18d-462c-a160-693b952e03ed)

## 대용량 데이터 처리
### 개선 전
![개선전](https://github.com/user-attachments/assets/b133d0f1-386a-4a29-8023-f5df0c561e94)

### 개선 후 (인덱스, 캐시 적용)
![image](https://github.com/user-attachments/assets/cb60e27b-9ffe-458b-91c6-8eb1f9ef718c)

