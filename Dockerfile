FROM openjdk:11

WORKDIR /usr/app

COPY . /usr/app

RUN ./mvnw package -Dmaven.javadoc.skip=true -DskipTests verify

EXPOSE 8080

CMD ["java", "-jar", "app/target/app-0.0.1-SNAPSHOT.jar"]

