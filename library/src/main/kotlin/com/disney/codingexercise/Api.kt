package com.disney.codingexercise

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface Api {
    fun fetch(ids: List<String>, operationName: String = FETCH_DOWNLOAD_METADATA ) : Single<List<DownloadMetadata>>

    fun store(item: DownloadMetadata): Completable
}
