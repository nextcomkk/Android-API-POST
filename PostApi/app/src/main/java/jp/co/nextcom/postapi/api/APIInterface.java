package jp.co.nextcom.postapi.api;

import jp.co.nextcom.postapi.model.Response;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APIInterface {
    @Headers({
            "Accept: application/json",
            "Content-type: application/json"
    })
    @POST()
    Call<Response> getPoint(
            @Url String url,
            @Body JsonObject request
            );
}