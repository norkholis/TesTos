package com.example.norkholis.testtos;


/**
 * Created by norkholis on 02/02/18.
 */

public class UtilApi {
    public static final String BASE_URL_API = "http://80.211.135.231/mobile_parking/public/api/";


    public static BaseApiService getApiService(){
        return ApiClient.getClient(BASE_URL_API).create(BaseApiService.class);
    }
}
