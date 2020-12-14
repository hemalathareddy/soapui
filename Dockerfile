FROM maven:3.5-jdk-8
ENV test_project=platform_loyalty_endtoend_tests
ENV env=nightly
ENV execution_group=sanity
WORKDIR /QA_Automation
COPY pom.xml .
RUN mvn dependency:go-offline -B
RUN mvn dependency:resolve-plugins
COPY apis_tests ./apis_tests/
RUN mvn clean install
CMD ["mvn","integration-test","-Papis_functional","-Dprojectname=$test_project","-Denvironment=$env","-Dexecution_group=$execution_group"]
