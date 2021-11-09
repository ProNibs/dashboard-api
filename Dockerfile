FROM openjdk:11-jdk-slim as build
WORKDIR /app

# I wish there was a way to cache these dependencies but oh well
COPY ./gradle/ /app/gradle/
# Copy multiple files into the /app directory
COPY build.gradle gradlew settings.gradle ./
#COPY flyway.conf ./flyway.conf
COPY src/ ./src
# Gradle build, but skip tests
RUN ./gradlew build -x test
RUN mkdir -p build/libs/dependency && (cd build/libs/dependency; jar -xf ../*.jar)

FROM openjdk:11-jre-slim
VOLUME /tmp
ARG DEPENDENCY=/app/build/libs/dependency
# Copy the DB migrations into the Flyway migrations location as defined in the flyways.conf file
COPY ./src/main/resources/db/migration/ /app/lib/db/migration/
#COPY flyway.conf /app/lib/flyway.conf

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
# Default Spring Boot port is 8080
EXPOSE 8080
ENTRYPOINT ["java","-cp","app:app/lib/*","com.flatironschool.dashboard.DashboardApplication"]