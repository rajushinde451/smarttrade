package com.market.Core;

public class CustomRequest {

	public String requestType;
	public ConditionInput conditionInput;
	public LoginRequest loginRequest;
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public ConditionInput getConditionInput() {
		return conditionInput;
	}
	public void setConditionInput(ConditionInput conditionInput) {
		this.conditionInput = conditionInput;
	}
	public LoginRequest getLoginRequest() {
		return loginRequest;
	}
	public void setLoginRequest(LoginRequest loginRequest) {
		this.loginRequest = loginRequest;
	}
	
	
}
