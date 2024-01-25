package com.disney.codingexercise

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface FetchDataManager {
    fun store(item: DownloadMetadata): Completable
    fun fetchDownloadMetadata(ids: List<String>, operationName: String): Single<List<DownloadMetadata>>
}

class DefaultFetchDataManager(val operationClient: OperationClient) : FetchDataManager {

    override fun fetchDownloadMetadata(ids: List<String>, operationName: String): Single<List<DownloadMetadata>> {
        return Single.fromCallable {

            var metaList: List<DownloadMetadata> = emptyList()

            ids.forEach { id ->
                if (existsInDatabase(id)) {
                    getFromLocalDataBase(id).blockingGet()
                } else {
                    operationClient.executeOperation(operationName).blockingGet().first { it.availId == id }
                }.also {
                    metaList += it
                }
            }

            metaList
        }

    }

    override fun store(item: DownloadMetadata): Completable {
        return writeToDatabase(item)
    }

    private fun existsInDatabase(id: String): Boolean {
        return fakeDatabase.firstOrNull { it.availId == id } != null
    }

    private fun getFromLocalDataBase(id: String): Single<DownloadMetadata> {
        return Single.just(fakeDatabase.first { it.availId == id })
    }

    private fun writeToDatabase(item: DownloadMetadata): Completable {
        return Completable.fromCallable {
            fakeDatabase.add(item)
        }
    }

    private val fakeDatabase = mutableSetOf(
        DownloadMetadata(
            "one",
            "resourceOne",
            "titleOne",
            1000L,
            30L
        ),
        DownloadMetadata(
            "two",
            "resourceTwo",
            "titleTwo",
            1001L,
            31L
        )
    )
}
