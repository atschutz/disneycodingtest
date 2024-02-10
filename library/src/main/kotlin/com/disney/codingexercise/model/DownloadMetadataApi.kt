package com.disney.codingexercise.model

import com.disney.codingexercise.DownloadMetadata
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DownloadMetadataApi {

    // TODO - Unsure if this endpoint is right or will work with mock data.
    @GET("/data")
    suspend fun getDownloadMetadata(
        @Query("availId") ids: List<String>
    ): Response<List<DownloadMetadata>>
}