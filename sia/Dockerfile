FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/sia-1.0.jar sia-dk-1.0.jar
ENTRYPOINT ["java","-jar","sia-dk-1.0.jar"]
