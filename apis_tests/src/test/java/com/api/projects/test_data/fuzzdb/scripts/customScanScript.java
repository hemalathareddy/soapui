import groovy.json.JsonSlurper;
import groovy.json.JsonBuilder;

//create a builder for each request created.
def req = testStep.getPropertyValue("Request");
def requestJson = new JsonSlurper().parseText(req);
JsonBuilder builder = new JsonBuilder(requestJson);

int amountFieldFuzzDataSize = context.lstOfFuzzForAmountField.size -1;
int customerIdFieldFuzzDataSize = context.lstOfFuzzForCustomerIdField.size -1;
int cartItemsAmountFieldFuzzDataSize = context.lstOfFuzzForCartItemsAmountField.size -1;
int cartItemsQtyFieldFuzzDataSize = context.lstOfFuzzForCartItemsQtyField.size -1;
int invalidFuzzJsonDataSize = context.lstOfInvalidJsons.size - 1;

//initializing the index variable to iterate.
if(context.amountFieldFuzzDataIndex == null){
  context.amountFieldFuzzDataIndex = 0;
}
if(context.customerIdFieldFuzzDataIndex == null){
  context.customerIdFieldFuzzDataIndex = 0;
}
if(context.cartItemsAmountFieldFuzzDataIndex == null){
  context.cartItemsAmountFieldFuzzDataIndex = 0;
}
if(context.cartItemsQtyFieldFuzzDataIndex == null){
  context.cartItemsQtyFieldFuzzDataIndex = 0;
}
if(context.invalidFuzzJsonDataIndex == null){
  context.invalidFuzzJsonDataIndex = 0;
}

if(context.invalidFuzzJsonDataIndex <= invalidFuzzJsonDataSize){
  log.info("Content at index - " + context.invalidFuzzJsonDataIndex + "==> " + context.lstOfInvalidJsons.get(context.invalidFuzzJsonDataIndex));
  builder.content = context.lstOfInvalidJsons.get(context.invalidFuzzJsonDataIndex);
  parameters.payload = builder.toString();
  context.changedParamsField = "json body";
  context.changedParamsValue = context.lstOfInvalidJsons.get(context.invalidFuzzJsonDataIndex);
  context.status = securityScan.getSecurityScanResult().getStatusString();
  context.invalidFuzzJsonDataIndex++;
  return true;
}
//modify the "amount" field in request body using fuzz data captured.
if(context.amountFieldFuzzDataIndex <= amountFieldFuzzDataSize){
  log.info("Content at index - " + context.amountFieldFuzzDataIndex + "==> " + context.lstOfFuzzForAmountField.get(context.amountFieldFuzzDataIndex));
  builder.content.amount = context.lstOfFuzzForAmountField.get(context.amountFieldFuzzDataIndex);
  parameters.payload = builder.toString();
  context.changedParamsField = "amount";
  context.changedParamsValue = context.lstOfFuzzForAmountField.get(context.amountFieldFuzzDataIndex);
  context.status = securityScan.getSecurityScanResult().getStatusString();
  context.amountFieldFuzzDataIndex++;
  return true;
}
if(context.customerIdFieldFuzzDataIndex <= customerIdFieldFuzzDataSize){
  log.info("Content at index - " + context.customerIdFieldFuzzDataIndex + "==> " + context.lstOfFuzzForCustomerIdField.get(context.customerIdFieldFuzzDataIndex));
  builder.content.customerId = context.lstOfFuzzForCustomerIdField.get(context.customerIdFieldFuzzDataIndex);
  parameters.payload = builder.toString();
  context.changedParamsField = "customerId";
  context.changedParamsValue = context.lstOfFuzzForCustomerIdField.get(context.customerIdFieldFuzzDataIndex);
  context.status = securityScan.getSecurityScanResult().getStatusString();
  context.customerIdFieldFuzzDataIndex++;
  return true;
}
if(context.cartItemsAmountFieldFuzzDataIndex <= cartItemsAmountFieldFuzzDataSize){
  log.info("Content at index - " + context.cartItemsAmountFieldFuzzDataIndex + "==> " + context.lstOfFuzzForCartItemsAmountField.get(context.cartItemsAmountFieldFuzzDataIndex));
  builder.content.cartItems[0].amount = context.lstOfFuzzForCartItemsAmountField.get(context.cartItemsAmountFieldFuzzDataIndex)
  parameters.payload = builder.toString();
  context.changedParamsField = "cartItems.amount";
  context.changedParamsValue = context.lstOfFuzzForCartItemsAmountField.get(context.cartItemsAmountFieldFuzzDataIndex);
  context.status = securityScan.getSecurityScanResult().getStatusString();
  context.cartItemsAmountFieldFuzzDataIndex++;
  return true;
}
if(context.cartItemsQtyFieldFuzzDataIndex <= cartItemsQtyFieldFuzzDataSize){
  log.info("Content at index - " + context.cartItemsQtyFieldFuzzDataIndex + "==> " + context.lstOfFuzzForCartItemsQtyField.get(context.cartItemsQtyFieldFuzzDataIndex));
  builder.content.cartItems[0].qty = context.lstOfFuzzForCartItemsQtyField.get(context.cartItemsQtyFieldFuzzDataIndex);
  parameters.payload = builder.toString();
  context.changedParamsField = "cartItems.qty";
  context.changedParamsValue = context.lstOfFuzzForCartItemsQtyField.get(context.cartItemsQtyFieldFuzzDataIndex);
  context.status = securityScan.getSecurityScanResult().getStatusString();
  context.cartItemsQtyFieldFuzzDataIndex++;
  return true;
}
else{
  def lstOfStatus = securityScan.getSecurityScanResult().getSecurityRequestResultList();
  for(def statusObj : lstOfStatus){
    context.lstOfStatus.add(statusObj.getStatus());
  }
  return false;
}