/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.iod.client.api.search;

import com.hp.autonomy.iod.client.error.IodErrorException;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;

/**
 * Interface representing the CreateQueryProfile API.
 */
public interface CreateQueryProfileService {

    String URL = "/api/sync/createqueryprofile/v1";

    /**
     * Create a query profile in IDOL OnDemand for a specified query manipulation index using the given API key
     * @param apiKey The API key to use to authenticate the request
     * @param name The name of the query profile
     * @param config The content of the query profile
     * @return The name of the created query profile
     */
    @POST(URL)
    @Multipart
    CreateDeleteQueryProfileResponse createQueryProfile(
            @Part("apiKey") String apiKey,
            @Part("query_profile") String name,
            @Part("config") QueryProfile config
    ) throws IodErrorException;
}
