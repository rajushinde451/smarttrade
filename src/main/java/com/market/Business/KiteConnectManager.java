package com.market.Business;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;

import com.market.Core.ConditionInput;
import com.market.Core.DisplayObject;
import com.market.Core.LoginRequest;
import com.market.Core.LoginResponse;
import com.market.Indicator.Condition;
import com.neovisionaries.ws.client.WebSocketException;
import com.zerodhatech.kiteconnect.KiteConnect;
import com.zerodhatech.kiteconnect.kitehttp.SessionExpiryHook;
import com.zerodhatech.kiteconnect.kitehttp.exceptions.KiteException;
import com.zerodhatech.models.User;

public class KiteConnectManager {

	public static KiteConnect kiteConnect    ;
	public static String apiSecret    ;
	public TickManager tickManager = new TickManager();
	
	// static variable single_instance of type Singleton 
    private static KiteConnectManager _kiteConnectManager = null; 
    
    private KiteConnectManager() 
    {}
    
    // static method to create instance of Singleton class 
    public static KiteConnectManager getInstance() 
    { 
        if (_kiteConnectManager == null) 
        	_kiteConnectManager = new KiteConnectManager(); 
  
        return _kiteConnectManager; 
    } 
    
    public String doLoginRedirect(String request_token) 
    {
    	try {
    		User user =  kiteConnect.generateSession(request_token, apiSecret);
            kiteConnect.setAccessToken(user.accessToken);
            kiteConnect.setPublicToken(user.publicToken);
            CacheManager.GetInstance().LoadInstruments(kiteConnect);
            return "Success";
    	}
    	catch (KiteException e) {
            System.out.println(e.message+" "+e.code+" "+e.getClass().getName());
            return "Success";
        } catch (JSONException e) {
            e.printStackTrace();
            return "Success";
        }catch (IOException e) {
            e.printStackTrace();
            return "Success";
        }
    }

    public LoginResponse getLoginLink(LoginRequest request) 
    {
    	try{
			// First you should get request_token, public_token using kitconnect login and then use request_token, public_token, api_secret to make any kiteConnect api call.
         // Initialize KiteSdk with your apiKey.
         kiteConnect = new KiteConnect(request.apiKey, true);

         // Set userId
         kiteConnect.setUserId(request.userId);
         
         apiSecret=request.apiSecret;

         // Get login url
         LoginResponse response = new LoginResponse();
         response.url = kiteConnect.getLoginURL();

         // Set session expiry callback.
         kiteConnect.setSessionExpiryHook(new SessionExpiryHook() {
             @Override
             public void sessionExpired() {
                 System.out.println("session expired");
             }
         });

         return response;
		}
	 catch (JSONException e) {
	     e.printStackTrace();
	     return new LoginResponse();
	 	}
    }

    public void doUpdateCondition(ConditionInput conditionInput) {
		try{
			Condition condition = Condition.getInstance();
			condition.UpdateCondition(conditionInput);
		}
		catch (JSONException e) {
		     e.printStackTrace();
		}
    }

    public List<DisplayObject> getPotentialStocks() {
		System.out.println("Getting potential stocks ========= Thread Name :"+Thread.currentThread().getName());
		System.out.println("Getting potential stocks");
		List<DisplayObject> listOfDisplayObjects =  tickManager.getListOfDisplayItems();
     
		return listOfDisplayObjects;
    }

    public void subscribeStocks() 
    {
    	ArrayList<Long> nifty50Stocks = getNifty50Stocks();
    			
    	try {
			tickManager.tickerUsage(kiteConnect, nifty50Stocks);
		} catch (IOException | WebSocketException | KiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        		
    }
    
    public ArrayList<Long> getNifty50Stocks()
    {
    	ArrayList<Long> tokens = new ArrayList<>();
		tokens.add(Long.parseLong("492033"));
		tokens.add(Long.parseLong("424961"));
		tokens.add(Long.parseLong("1207553"));
		tokens.add(Long.parseLong("60417"));
		tokens.add(Long.parseLong("70401"));
		tokens.add(Long.parseLong("81153"));
		tokens.add(Long.parseLong("2714625"));
		tokens.add(Long.parseLong("340481"));
		tokens.add(Long.parseLong("348929"));
		tokens.add(Long.parseLong("2672641"));
		tokens.add(Long.parseLong("969473"));
		tokens.add(Long.parseLong("3050241"));
		tokens.add(Long.parseLong("2952193"));
		tokens.add(Long.parseLong("779521"));
		tokens.add(Long.parseLong("2977281"));
		tokens.add(Long.parseLong("519937"));
		tokens.add(Long.parseLong("2939649"));
		tokens.add(Long.parseLong("7712001"));
		tokens.add(Long.parseLong("345089"));
		tokens.add(Long.parseLong("738561"));
		tokens.add(Long.parseLong("895745"));
		tokens.add(Long.parseLong("408065"));
		tokens.add(Long.parseLong("356865"));
		tokens.add(Long.parseLong("1850625"));
		tokens.add(Long.parseLong("3834113"));
		tokens.add(Long.parseLong("177665"));
		tokens.add(Long.parseLong("359937"));
		tokens.add(Long.parseLong("1346049"));
		tokens.add(Long.parseLong("3861249"));
		tokens.add(Long.parseLong("5215745"));
		tokens.add(Long.parseLong("857857"));
		tokens.add(Long.parseLong("2953217"));
		tokens.add(Long.parseLong("2889473"));
		tokens.add(Long.parseLong("415745"));
		tokens.add(Long.parseLong("341249"));
		tokens.add(Long.parseLong("1510401"));
		tokens.add(Long.parseLong("232961"));
		tokens.add(Long.parseLong("784129"));
		tokens.add(Long.parseLong("1270529"));
		tokens.add(Long.parseLong("225537"));
		tokens.add(Long.parseLong("4267265"));
		tokens.add(Long.parseLong("2815745"));
		tokens.add(Long.parseLong("884737"));
		tokens.add(Long.parseLong("975873"));
		tokens.add(Long.parseLong("3465729"));
		tokens.add(Long.parseLong("325121"));
		tokens.add(Long.parseLong("134657"));
		tokens.add(Long.parseLong("633601"));
		tokens.add(Long.parseLong("558337"));
		tokens.add(Long.parseLong("7458561"));
		
		return tokens;
    }
}
