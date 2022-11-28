# Use maeven to compile MockMock
mvn package -f  MockMock/pom.xml

# Copy the executable (.jar) on the directory docker
cp ./MockMock/release/MockMock.jar ./docker

# Finally, docker build it
docker build --tag dai/mockmock ./docker