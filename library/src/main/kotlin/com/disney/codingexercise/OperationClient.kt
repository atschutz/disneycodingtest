package com.disney.codingexercise

import com.disney.codingexercise.model.downloadmetadata.DownloadMetadata
import com.disney.codingexercise.model.downloadmetadata.ResponseData
import com.squareup.moshi.Moshi
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

interface OperationClient {
    fun executeOperation(requestName: String): Single<List<DownloadMetadata>>
}

class DefaultOperationClient(val networkClient: NetworkClient): OperationClient {

    override fun executeOperation(requestName: String): Single<List<DownloadMetadata>> {
        val client = OkHttpClient()
        val mockWebServer = networkClient.startMockWebServer(requestName)

        val request = Request.Builder()
            .url(mockWebServer.url("/"))
            .build()

        val response: Response = client.newCall(request).execute()
        val jsonString = response.body?.string() ?: ""

        val responseMock = Moshi.Builder()
            .build()
            .adapter(ResponseData::class.java)
            .fromJson(jsonString)!!

        /* pretend to load remote JSON data */
        if(responseMock.code == 200) {
            return Single.just(responseMock.data!!).doFinally { mockWebServer.shutdown() }
        } else {
            mockWebServer.shutdown()
            throw Throwable(message = responseMock.errorMessage)
        }
    }
}
