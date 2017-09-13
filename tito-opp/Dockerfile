FROM dominionconsulting-tito.jfrog.io/ubuntu:16.04
LABEL maintainer "rtighe@dominionconsulting.com"
RUN apt-get update
RUN apt-get install default-jdk mysql-client -qq
COPY tito-opp-application/build/libs/*.war /tito-opp/tito-opp.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tito-opp/tito-opp.war"]
