package com.disney.codingexercise


import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import java.io.InputStream


class NetworkClient {

    fun readJsonFromFile(filename: String): String {
        val inputStream: InputStream? = javaClass.classLoader?.getResourceAsStream(filename)
        return inputStream?.bufferedReader().use { it?.readText() } ?: ""
    }

    fun startMockWebServer(requestName: String): MockWebServer {
        val mockWebServer = MockWebServer()
        val jsonString = readJsonFromFile("$requestName.json")

        mockWebServer.enqueue(MockResponse().setBody(jsonString))
        mockWebServer.start()

        return mockWebServer
    }
}
