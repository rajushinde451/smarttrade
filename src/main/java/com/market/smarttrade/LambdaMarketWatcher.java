package com.market.smarttrade;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaMarketWatcher implements RequestHandler<Object, String>  {

	  @Override
	    public String handleRequest(Object request, Context context) {
	        context.getLogger().log("Input: " + request);

	        //CustomRequest ip12 = (CustomRequest)request;
	        // TODO: implement your handler
	        return "Hello from Raj !!!!  Your request token is "+ request;
	    }
}
