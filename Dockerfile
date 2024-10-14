FROM amazoncorretto:17
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} vladimirtest.jar
ENTRYPOINT ["java","-jar","/vladimirtest.jar"]