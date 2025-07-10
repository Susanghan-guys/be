# Java 17을 사용하는 Spring Boot 애플리케이션 빌드
FROM openjdk:17-jdk-slim

# 필요한 패키지 설치 (wget for wait-for-it.sh)
RUN apt-get update && apt-get install -y wget

# wait-for-it.sh 스크립트 다운로드 및 실행 권한 부여
RUN wget -O /usr/local/bin/wait-for-it.sh https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh && \
    chmod +x /usr/local/bin/wait-for-it.sh

COPY soosanghan.jar app.jar

EXPOSE 8080

# Redis 준비 완료 후 앱 실행
ENTRYPOINT ["/usr/local/bin/wait-for-it.sh", "redis:6379", "--", "java", "-jar", "/app.jar"]