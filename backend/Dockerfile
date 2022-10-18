# Build
FROM maven:3.8.3-openjdk-17 AS build

COPY src /home/app/src
COPY src/main/resources/application-docker.properties /home/app/src/main/resources/application.properties

COPY scrabble_resources /scrabble_resources
COPY python_scripts /python_scripts
COPY pom.xml /home/app/pom.xml

RUN mvn -f /home/app/pom.xml clean package -Dmaven.test.skip



# Production
FROM ubuntu:latest
COPY --from=build /home/app/target/*.jar /app.jar
COPY --from=build /scrabble_resources /scrabble_resources
COPY --from=build /python_scripts /python_scripts

RUN apt update
RUN apt install -y python3 pip openjdk-17-jre
RUN pip install numpy==1.23.2 opencv-python==4.5.4.60 easyocr==1.6.2

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

