String response = messageExchange.getResponseContent();
String reqBody = messageExchange.getRequestContent();
String endPoint = messageExchange.getEndpoint()

def status = messageExchange.getResponseHeaders().get('#status#');
String code = (status =~ "[1-5]\\d\\d")[0];
requestStatus = "UNKNOWN";
if(code != '200' && code != '201'){
	requestStatus = "OK";
}
else{
	requestStatus = "FAILED"
}
// get request call information to log.
HashMap<String,String> properties = messageExchange.getProperties();
def parameterJson = [:];
properties.each{ key,value ->
  parameterJson[(key)] = value;
  }
parameterString = parameterJson.toMapString()
def timeTaken = messageExchange.getTimeTaken();
def testStepName = context.testCase.getTestStepAt(context.getCurrentStepIndex()).getLabel();
def securityTestName = context.getCurrentScan().getParent().getName();
def scan = context.getCurrentScan().getName();

if(context.INDEX == null){
	context.INDEX = 0;
}
context.INDEX = context.INDEX + 1;
KEY = securityTestName + "_" + testStepName + "_" + scan;
context.JsonContent << [(KEY): [(context.INDEX): ["request": reqBody, "response": response, "timeTaken": timeTaken.toString(), "parameters": parameterString, "changedFieldKey": context.changedParamsField, "changedFieldValue": context.changedParamsValue, "status": requestStatus]]]