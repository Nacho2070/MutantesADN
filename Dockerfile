FROM azul/zulu-openjdk:17-latest
VOLUME /tmp
COPY build/libs/*.jar parcialprogramacion.jar
ENTRYPOINT ["java", "-jar", "/parcialprogramacion.jar"]