package com.example.kumpr4a.todoapp.parser;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by kumpr4a on 6/2/2017.
 */

public class ParserHelper {

    public static <T> T deserializeObject(Type objectClass, String json) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, objectClass);
        } catch (JsonParseException jsonParseException) {
            jsonParseException.printStackTrace();
            return null;
        }
    }
}
