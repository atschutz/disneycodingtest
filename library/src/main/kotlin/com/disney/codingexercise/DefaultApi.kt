package com.disney.codingexercise

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

const val FETCH_DOWNLOAD_METADATA = "download_metadata_response"
const val ERROR_DOWNLOAD_METADATA = "download_metadata_response_error"

class DefaultApi: Api {

    private val manager: DefaultFetchDataManager by lazy { DefaultFetchDataManager(DefaultOperationClient(NetworkClient())) }

    override fun fetch(ids: List<String>, operationName: String): Single<List<DownloadMetadata>> {
        return manager.fetchDownloadMetadata(ids, operationName)
    }

    override fun store(item: DownloadMetadata): Completable {
        return manager.store(item)
    }
}
