FROM openjdk:17
COPY build/libs/Auto-Trade-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app/
ENTRYPOINT ["java"]
CMD ["-jar", "/app/Auto-Trade-0.0.1-SNAPSHOT.jar"]
