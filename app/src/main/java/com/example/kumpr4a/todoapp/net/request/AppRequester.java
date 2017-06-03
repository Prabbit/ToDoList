package com.example.kumpr4a.todoapp.net.request;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.kumpr4a.todoapp.net.response.ToDoResponse;
import com.example.kumpr4a.todoapp.parser.ParserHelper;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by kumpr4a on 6/2/2017.
 */

public class AppRequester {

    public void requestData(Context context, String url) {
        RequestQueue queue = VolleySingleton.getInstance(context).getRequestQueue();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Type listType = new TypeToken<List<ToDoResponse>>() {
                        }.getType();
                        List<ToDoResponse> listItems = ParserHelper.deserializeObject(listType,
                                response);
                        EventBus.getDefault().postSticky(listItems);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }
}
