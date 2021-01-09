FROM maven:3.5-jdk-8
WORKDIR /QA_Automation
COPY pom.xml .
RUN mvn dependency:go-offline -B
RUN mvn dependency:resolve-plugins
COPY apis_tests ./apis_tests/
RUN mvn clean install
CMD mvn integration-test -Papis_functional -Dprojectname=$projectname -Denvironment=$environment -Dexecution_group=$execution_group
