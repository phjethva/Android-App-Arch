package com.phjethva.mvvm_rx.data.wonder.network;

import com.phjethva.mvvm_rx.data.ApiEndPoints;
import com.phjethva.mvvm_rx.data.model.WondersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WondersRequest {

    @GET(ApiEndPoints.GET_ALL_WONDERS)
    Observable<WondersResponse> getAllWonders();

}
