package com.phjethva.rx.data.wonder.network;

import com.phjethva.rx.data.ApiEndPoints;
import com.phjethva.rx.data.model.WondersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface WondersRequest {

    @GET(ApiEndPoints.GET_ALL_WONDERS)
    Observable<WondersResponse> getAllWonders();

}
