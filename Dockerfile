FROM azul/zulu-openjdk:17-latest
LABEL authors="Ignacio"
COPY build/libs/*.jar parcialProgramacion.jar
ENTRYPOINT ["jar", "-jar", "/parcialProgramacion.jar"]