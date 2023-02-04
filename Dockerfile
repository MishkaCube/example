FROM openjdk
EXPOSE 8843
ARG JAR_FILE=target/devcloudy-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} devcloudy.jar
ENTRYPOINT ["java","-jar","/devcloudy.jar"]