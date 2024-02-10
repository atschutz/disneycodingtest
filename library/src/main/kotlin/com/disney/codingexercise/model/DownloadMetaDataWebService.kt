package com.disney.codingexercise.model

import com.disney.codingexercise.NetworkClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DownloadMetaDataWebService {
    private var api: DownloadMetadataApi

    init  {
        val mockWebServer = NetworkClient().startMockWebServer("TODO")

        // TODO - Gson shouldn't care about moshi annotation?
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(DownloadMetadataApi::class.java)
    }

    suspend fun getDownloadMetadata(ids: List<String>) = api.getDownloadMetadata(ids)
}