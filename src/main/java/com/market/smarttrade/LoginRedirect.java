package com.market.smarttrade;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.market.Business.KiteConnectManager;

public class LoginRedirect implements RequestStreamHandler {

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {

    	KiteConnectManager kiteConnectManager = KiteConnectManager.getInstance();
        final ObjectMapper objectMapper= new ObjectMapper();

        JsonNode node = null;
		try {
			node = objectMapper.readTree(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        String requestToken = node.path("params").path("querystring").path("request_token").asText();
        
        System.out.println("Request Token:"+requestToken);
        kiteConnectManager.doLoginRedirect(requestToken);
    	
        }
    }

