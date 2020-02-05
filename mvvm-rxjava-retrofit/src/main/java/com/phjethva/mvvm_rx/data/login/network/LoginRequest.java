package com.phjethva.mvvm_rx.data.login.network;

import com.phjethva.mvvm_rx.data.ApiEndPoints;
import com.phjethva.mvvm_rx.data.model.LoginResponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface LoginRequest {

    @Multipart
    @POST(ApiEndPoints.GET_LOGIN)
    Observable<LoginResponse> getLogin(@PartMap Map<String, RequestBody> map);

}
