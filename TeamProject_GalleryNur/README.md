# Gallery Nur

**Nur Gallery**는 직접 갤러리 사이트를 만들고 운영하면서 사용자들이 해당 갤러리에서 열리는 전시회와 작가 정보, 전시회 예매를 할 수 있게 구현한 사이트입니다.

# 프로젝트 설명

컨셉에 맞는 아티스트들과 해당 아티스트의 전시회를 관리자가 추가하고 사용자가 직접 전시회를 예매할 수 있게 구현 한 사이트입니다. 사용자들은 전시회를 예약할 수 있고, 해당 전시회의 정보를 얻기 위해서 리뷰 게시판 또는 Q&A 게시판을 통해서 해당 전시회의 정보를 얻을 수 있습니다.

## 구성 팀원

![image](https://user-images.githubusercontent.com/92519295/187875064-cdec490f-0f96-40c9-ad4d-11cced729dd9.png)


## 기술 스택

 - Front-End : HTML, CSS3, JavaScript, jQuery
 - Back-End : Java8, JSP, Spring Boot 2.7.2, AWS
 - Build : Gradle
 - Library : Lombok, Mybatis, Spring Security, Ojdbc10

## 구현 기능

 - [x] Bean Validation을 통한 로그인/회원가입 검증
 - [x] SHA-256과 Salt를 이용한 회원가입 시 비밀번호 암호화
 - [x] Oauth 2.0을 이용한 구글/카카오 로그인
 - [x] Gmail SMTP 서버를 이용하여 이메일 인증
 - [x] Spring Interceptor를 이용하여 공통 관심사항 분리
 - [x] 토스 페이먼츠 API를 이용해서 전시회 예매 시 결제 기능
 - [x] 각 게시판 CRUD 및 전시회, 작가 게시판 사진 업로드
 - [x] Chart.js를 이용해서 전시회의 1년 매출, 진행 중인 전시회의 입장 수와 매출액 그래프
 - [x] 국제화 적용 (영어, 한국어)
 - [x] AWS를 이용한 프로젝트 배포
 - [x] SSL 인증서를 이용해서 HTTPS 보안 적용

## 아쉬웠던 점

프로젝트를 진행하면서 통합과 배포 과정도 코딩 못지않게 중요하다고 생각하여, 마지막 주에 배포 자동화를 구현하고자 **Travis CI와 AWS S3, AWS Code-Deploy를 통해서 배포 자동화**를 하고자 하였습니다.
프로젝트를 진행하면서 각 Brunch 별로 분리하여, 각 날 마지막 시간 혹은 그 다음 날 아침에 Pull Request 요청이 들어오면, 충돌이 없을 시 Merge를 진행하고, 이상이 없으면 main과 병합하고, main에서 Commit이 될 경우, 
**Travis CI -> AWS S3 -> AWS Code-Deploy -> EC2** 과정을 거쳐, 자동화를 구현하려고 하였으나, 
Code-Deploy에서 EC2로 배포하는 과정에서 계속 에러가 발생하였고, 약 3일이상을 투자했음에도 자동화를 실패하였던 것이 아쉽습니다.  
 
