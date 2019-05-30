package com.market.smarttrade;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.market.Business.KiteConnectManager;
import com.market.Core.CustomRequest;

public class MarketWatch implements RequestHandler<CustomRequest, Object> {

    @Override
    public Object handleRequest(CustomRequest input, Context context) {
        context.getLogger().log("Input: " + input);

        KiteConnectManager kiteConnectManager = KiteConnectManager.getInstance();
        
        if (input.requestType.equals("health"))
        	return "All Good...!! Nikhil Elliddiappa";
        
        else if (input.requestType.equals("loginRedirect"))
        	kiteConnectManager.doLoginRedirect("");
        
        else if (input.requestType.equals("updateCondition"))
        	kiteConnectManager.doUpdateCondition(input.conditionInput);
        
        else if (input.requestType.equals("getKiteLogin"))
        	return kiteConnectManager.getLoginLink(input.loginRequest);
        
        else if (input.requestType.equals("subscribe"))
        	kiteConnectManager.subscribeStocks();
        
        else if (input.requestType.equals("getPotentialStocks"))
        	return kiteConnectManager.getPotentialStocks();
        // TODO: implement your handler
        return "Hello from Lambda! " + input.requestType;
    }

}
