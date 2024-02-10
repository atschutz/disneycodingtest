package com.disney.codingexercise.model.downloadmetadata

import com.disney.codingexercise.FETCH_DOWNLOAD_METADATA
import com.disney.codingexercise.NetworkClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DownloadMetaDataWebService {
    private var api: DownloadMetadataApi

    init  {
        val mockWebServer = NetworkClient().startMockWebServer(FETCH_DOWNLOAD_METADATA)

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(DownloadMetadataApi::class.java)
    }

    suspend fun getDownloadMetadata(ids: List<String>) = api.getDownloadMetadata(ids)
}