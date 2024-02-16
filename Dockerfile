FROM openjdk:17-jdk-slim AS build
WORKDIR /order-management-system
COPY . .
RUN ./mvnw -DskipTests clean package

FROM openjdk:17-jdk-slim
WORKDIR /order-management-system
COPY --from=build /order-management-system/target/*.jar ./order-management-system.jar
ENTRYPOINT ["java", "-jar", "order-management-system.jar"]