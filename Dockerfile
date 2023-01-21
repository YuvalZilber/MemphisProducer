FROM openjdk:19
COPY ./out/production/Producer/ /tmp
WORKDIR /tmp
COPY ./dependencies/gson-2.10.1.jar /usr/local/lib/gson-2.10.1.jar
# ENTRYPOINT ["java", "Main"]
ENTRYPOINT ["bash"]