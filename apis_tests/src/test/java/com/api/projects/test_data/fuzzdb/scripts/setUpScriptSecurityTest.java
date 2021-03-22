import com.eviware.soapui.model.testsuite.TestStepResult.TestStepStatus;
import groovy.json.JsonSlurper;

context.JsonContent = [];
context.lstOfStatus = []

//Accessing the fuzz data for tests.
String project_dir = context.expand('${projectDir}');
fuzzDBLocation = project_dir + File.separator + "test_data" + File.separator + "fuzzdb";
filepath = fuzzDBLocation + File.separator + "evaluatePromotionAPIFuzzData.json";
def reader = new BufferedReader(new InputStreamReader(new FileInputStream(filepath),"UTF-8"))
def json = new JsonSlurper().parse(reader);


def lstOfInvalidJsons = [];
new File(fuzzDBLocation + File.separator + "JSON_fuzzing.txt").withReader('UTF-8') { readerfile ->
def line
    while ((line = readerfile.readLine()) != null) {
        lstOfInvalidJsons.add(line);
    }
}

def lstOfFuzzForAmountField = json.amount;
def lstOfFuzzForCustomerIdField = json.customerId;
def lstOfFuzzForCartItemsAmountField = json.cartItemsAmount;
def lstOfFuzzForCartItemsQtyField = json.cartItemsQty;

context.lstOfFuzzForAmountField = lstOfFuzzForAmountField;
context.lstOfFuzzForCustomerIdField = lstOfFuzzForCustomerIdField;
context.lstOfFuzzForCartItemsAmountField = lstOfFuzzForCartItemsAmountField;
context.lstOfFuzzForCartItemsQtyField = lstOfFuzzForCartItemsQtyField;
context.lstOfInvalidJsons = lstOfInvalidJsons;