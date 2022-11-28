# We create a file and the copy the MockMock files in it
FROM eclipse-temurin:11
RUN mkdir /opt/app
COPY ./MockMock.jar /opt/app

EXPOSE 80
EXPOSE 25

# The docker server will listen on port 80
CMD ["java", "-jar", "/opt/app/MockMock.jar", "-h", "80"]
