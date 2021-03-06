/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.iod.client.api.textindexing;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hp.autonomy.iod.client.error.IodError;
import com.hp.autonomy.iod.client.error.IodErrorException;
import com.hp.autonomy.iod.client.job.Action;
import com.hp.autonomy.iod.client.job.JobId;
import com.hp.autonomy.iod.client.job.JobStatus;
import com.hp.autonomy.iod.client.job.Status;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import java.util.List;

/**
 * Interface representing the DeleteTextIndex API.
 */
public interface DeleteTextIndexService {

    String syncURL = "/api/sync/deletetextindex/v1";
    String asyncURL = "/api/async/deletetextindex/v1";

    /**
     * Delete a text index using a hash code obtained by queryDeleteTextIndex using an API key provided by a {@link retrofit.RequestInterceptor}
     * @param index The name of the index
     * @return A response relaying information about the attempt to delete the index
     */
    @GET(syncURL)
    DeleteTextIndexResponse initialDeleteTextIndex(
            @Query("index") String index
    ) throws IodErrorException;

    /**
     * Delete a text index using a hash code obtained by queryDeleteTextIndex using the given API key {@link retrofit.RequestInterceptor}
     * @param apiKey The API key to use to authenticate the request
     * @param index The name of the index
     * @return A response relaying information about the attempt to delete the index
     */
    @GET(syncURL)
    DeleteTextIndexResponse initialDeleteTextIndex(
            @Query("apiKey") String apiKey,
            @Query("index") String index
    ) throws IodErrorException;

    /**
     * Delete a text index using a hash code obtained by queryDeleteTextIndex using an API key provided by a {@link retrofit.RequestInterceptor}
     * @param index The name of the index
     * @param confirm The hash code to confirm the deletion
     * @return A response relaying information about the attempt to delete the index
     */
    @GET(asyncURL)
    JobId deleteTextIndex(
            @Query("index") String index,
            @Query("confirm") String confirm
    ) throws IodErrorException;

    /**
     * Delete a text index using a hash code obtained by queryDeleteTextIndex using the given API key {@link retrofit.RequestInterceptor}
     * @param apiKey The API key to use to authenticate the request
     * @param index The name of the index
     * @param confirm The hash code to confirm the deletion
     * @return A response relaying information about the attempt to delete the index
     */
    @GET(asyncURL)
    JobId deleteTextIndex(
            @Query("apiKey") String apiKey,
            @Query("index") String index,
            @Query("confirm") String confirm
    ) throws IodErrorException;

    /**
     * Get the status of an AddToTextIndex job using an API key provided by a {@link retrofit.RequestInterceptor}
     * @param jobId The id of the job
     * @return An object containing the status of the job along with the result if the job has finished
     * @throws IodErrorException If an error occurred retrieving the status
     */
    @GET("/job/status/{jobId}")
    DeleteTextIndexJobStatus getJobStatus(
            @Path("jobId") JobId jobId
    ) throws IodErrorException;

    /**
     * Get the status of an AddToTextIndex job using the given API key
     * @param apiKey The API key to use to authenticate the request
     * @param jobId The id of the job
     * @return An object containing the status of the job along with the result if the job has finished
     * @throws IodErrorException If an error occurred retrieving the status
     */
    @GET("/job/status/{jobId}")
    DeleteTextIndexJobStatus getJobStatus(
            @Query("apiKey") String apiKey,
            @Path("jobId") JobId jobId
    ) throws IodErrorException;

    /**
     * {@link com.hp.autonomy.iod.client.job.JobStatus} subtype which encodes the generic type for JSON parsing
     */
    class DeleteTextIndexJobStatus extends JobStatus<DeleteTextIndexResponse> {

        public DeleteTextIndexJobStatus(
                @JsonProperty("jobID") final String jobId,
                @JsonProperty("status") final Status status,
                @JsonProperty("actions") final List<DeleteTextIndexJobAction> actions
        ) {
            super(jobId, status, actions);
        }
    }

    /**
     * {@link com.hp.autonomy.iod.client.job.Action} subtype which encodes the generic type for JSON parsing
     */
    class DeleteTextIndexJobAction extends Action<DeleteTextIndexResponse> {
        // need these @JsonProperty or it doesn't work
        public DeleteTextIndexJobAction(
                @JsonProperty("action") final String action,
                @JsonProperty("status") final Status status,
                @JsonProperty("errors") final List<IodError> errors,
                @JsonProperty("result") final DeleteTextIndexResponse result,
                @JsonProperty("version") final String version
        ) {
            super(action, status, errors, result, version);
        }
    }
}
