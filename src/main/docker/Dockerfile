FROM openjdk:11.0.14.1-oracle

MAINTAINER Daniel Huang

VOLUME /tmp

ADD /target/cathayDemo-0.0.1-SNAPSHOT.jar springboot.jar

RUN bash -c 'touch /springboot.jar'

EXPOSE 80

ENTRYPOINT ["java", "-jar", "springboot.jar"]