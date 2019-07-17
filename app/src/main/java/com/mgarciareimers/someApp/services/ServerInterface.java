package com.mgarciareimers.someApp.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mgarciareimers.someApp.commons.Constants;

import java.util.HashMap;
import java.util.Map;

public class ServerInterface {
    private String BASE_URL = "";
    private String LOGIN = "";
    private String USERS_URL = "https://jsonplaceholder.typicode.com/users";

    private RequestQueue queue;
    private static ServerInterface serverInterface;

    // Constructor.
    private ServerInterface(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    // Method that gets the serverInterface.
    public static ServerInterface getServerInterface(Context context) {
        if (serverInterface == null) {
            serverInterface = new ServerInterface(context);
        }

        return serverInterface;
    }

    // Method that connects with the login endpoint.
    public void login(final String email, final String password, Response.Listener<String> callback, Response.ErrorListener errorCallback){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, this.BASE_URL.concat(this.LOGIN), callback, errorCallback){
            protected Map<String, String> getParams(){
                Map <String,String> params = new HashMap<>();
                params.put(Constants.EMAIL, email);
                params.put(Constants.PASSWORD, password);

                return params;
            }
        };

        queue.add(stringRequest);
    }

    // Method that gets the users list.
    public void getUsersList(Response.Listener<String> callback, Response.ErrorListener errorCallback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, this.USERS_URL, callback, errorCallback) { };

        queue.add(stringRequest);
    }
}
