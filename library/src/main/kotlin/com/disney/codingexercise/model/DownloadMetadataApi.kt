package com.disney.codingexercise.model

import com.disney.codingexercise.ResponseData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DownloadMetadataApi {

    // TODO - Use an actual GET url string to query `data` member of ResponseData directly.
    // Would look something like this:
    /*
        @GET("/data")
        suspend fun getDownloadMetadata(
            @Query("availId") ids: List<String>
        ): Response<List<DownloadMetadata>>
    */

    // Passing list of IDs here to emulate what it would look like further up the pipeline if the
    // above GET was used.
    @GET("/")
    suspend fun getDownloadMetadata(
        @Query("availId") ids: List<String>
    ): Response<ResponseData>
}