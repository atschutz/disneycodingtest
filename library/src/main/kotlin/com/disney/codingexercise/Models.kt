package com.disney.codingexercise

import com.squareup.moshi.JsonClass
@JsonClass(generateAdapter = true)
data class ResponseData(
    val code: Int = 0,
    val data: List<DownloadMetadata>? = emptyList(),
    val errorMessage: String? = ""
)

@JsonClass(generateAdapter = true)
data class DownloadMetadata(
    val availId: String = "",
    val resourceId: String = "",
    val title: String = "",
    val runtimeMs: Long = 0L,
    val elapsedMs: Long = 0L
)
