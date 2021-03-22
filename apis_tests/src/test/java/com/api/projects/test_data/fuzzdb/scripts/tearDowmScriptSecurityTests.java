import groovy.json.JsonOutput;
import com.eviware.soapui.security.*;

String project_dir = context.expand('${projectDir}');

def newJsonStr = JsonOutput.toJson(context.JsonContent)

def newPrettyJson = JsonOutput.prettyPrint(newJsonStr)

String outputFolder = context.testCase.testSuite.project.getPropertyValue("outputFolder");

String reportFileName = context.testCase.getSecurityTestAt(0).getName();

File reportFileObj = new File(outputFolder + File.separator + reportFileName + ".json");

saveToFile(reportFileObj, newPrettyJson)

log.info("Status details == " + context.lstOfStatus)

//Save the contents to a file
def saveToFile(file, content) {
    if (!file.parentFile.exists()) {
         file.parentFile.mkdirs()
         log.info "Directory did not exist, created"
    }
    file.write(content)
    assert file.exists(), "${file.name} not created"
}