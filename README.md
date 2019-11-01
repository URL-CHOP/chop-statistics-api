# chop-statistics-api
🔪Statistics Server for URL Shortener

[![HitCount](http://hits.dwyl.io/JunHoPark93/URL-CHOP/chop-statistics-api.svg)](http://hits.dwyl.io/JunHoPark93/URL-CHOP/chop-statistics-api)
[![](https://img.shields.io/github/contributors/URL-CHOP/chop-statistics-api)](https://github.com/URL-CHOP/chop-statistics-api/graphs/contributors)
[![](https://img.shields.io/github/issues-raw/URL-CHOP/chop-statistics-api)](https://github.com/URL-CHOP/chop-statistics-api/issues)
[![](https://img.shields.io/github/issues-closed-raw/URL-CHOP/chop-statistics-api)](https://github.com/URL-CHOP/chop-statistics-api/issues?q=is%3Aissue+is%3Aclosed)
![](https://img.shields.io/github/stars/URL-CHOP/chop-statistics-api?style=social)

<!-- TABLE OF CONTENTS -->
## Table of Contents

* [About the Project](#about-the-project)
  * [Built With](#built-with)
* [Refactoring Plan](#refactoring-plan)
* [Contributing](#contributing)
* [Contact](#contact)



<!-- ABOUT THE PROJECT -->
## About The Project

![Untitled Diagram](https://user-images.githubusercontent.com/20608121/68006010-8828c280-fcba-11e9-9411-31de8951bd4b.png)


Base62 인코딩을 사용한 URL 단축 서비스의 통계 서버 입니다. 통계 데이터는 Referrer(유입경로), Click Count(클릭 횟수), Click Date(클릭 날짜), Platform(유입 플랫폼) 입니다.

### Built With
프로젝트에 사용된 기술 목록입니다.

* [Spring Boot](https://spring.io/projects/spring-boot)
* [JPA](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference)
* [gRPC](https://grpc.io/)
* [NGINX](https://www.nginx.com/)


<!-- REFACTORING PLAN -->
## Refactoring Plan

- [ ] 부족한 테스트 케이스
- [ ] 비약한 도메인 설계
- [ ] 레이어드 아키텍쳐의 불분명함
- [ ] 함수와 클래스의 중복과 SRP 위반 


<!-- CONTRIBUTING -->
## Contributing

기여하는 법 입니다. 

1. 프로젝트를 Fork 한다
2. Feature Branch를 만든다 (`git checkout -b feature/[이슈번호]-SomeFeature`)
3. 변경을 Commit 한다 (`git commit -m '[이슈번호] [헤더]: Add Amazing Feature'`)
4. Branch를 푸시한다 (`git push origin [이슈번호] [헤더]: Add Amazing Feature`)
5. Pull Request를 한다

<!-- CONTACT -->
## Contact

Issue 탭에 이슈로 남기면 됩니다

