package com.example.miniekz;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {
    @POST ("auth/login")
    Call<AuthParam> doAuth(@Body AuthParam authParam);
    @POST ("auth/register")
    Call<RegParam> doReg(@Body RegParam regParam);
}
