FROM amazoncorretto:17.0.0

ARG appJar
ENV APP_JAR=${appJar}
RUN echo "java -jar $APP_JAR"
ENTRYPOINT java  -Xms1G -Xmx1G -Dspring.profiles.active=docker -Doracle.jdbc.timezoneAsRegion=false -jar $APP_JAR
