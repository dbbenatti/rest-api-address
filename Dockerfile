FROM maven:3.6.0-jdk-8-slim AS build 
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean install 

FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /usr/src/app/target/*.jar /usr/app/interviewRest-SNAPSHOT.jar  
EXPOSE 8080  
ENTRYPOINT ["java","-jar","/usr/app/interviewRest-SNAPSHOT.jar"]  