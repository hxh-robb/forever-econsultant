FROM java:8-jdk
ARG app_jar=target/*.jar
COPY ${app_jar} /app/springboot-executable.jar
EXPOSE 8080
WORKDIR /app
ENV JAVA_OPTS "-Xmx128m -Xms64m -XX:MaxRAM=128m -XX:+UseSerialGC -Xss512k"
CMD /app/springboot-executable.jar