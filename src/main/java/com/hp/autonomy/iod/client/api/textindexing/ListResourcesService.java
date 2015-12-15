package com.hp.autonomy.iod.client.api.textindexing;

import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;

import java.util.Map;

public interface ListResourcesService {
    String URL = "/api/sync/listresources/v1";

    @GET(URL)
    Resources listResources(
            @QueryMap Map<String, Object> params
    );

    @GET(URL)
    Resources listResources(
            @Query("apiKey") String apiKey,
            @QueryMap Map<String, Object> params
    );
}
