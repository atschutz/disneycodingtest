package com.disney.codingexercise.model.downloadmetadata

import io.reactivex.rxjava3.core.Single

class DBManager {
    private var fakeDatabase = mutableSetOf<DownloadMetadata>()

    init {
        // TODO - Make a real DB, if possible.
        fakeDatabase.add(
            DownloadMetadata(
                "one",
                "resourceOne",
                "titleOne",
                1000L,
                30L,
            )
        )
        fakeDatabase.add(
            DownloadMetadata(
                "two",
                "resourceTwo",
                "titleTwo",
                1001L,
                31L,
            )
        )
    }

    // Suspends are here because if we were using a real database, the reads/writes would be async.
    suspend fun existsInDatabase(id: String): Boolean =
        fakeDatabase.firstOrNull { it.availId == id } != null

    suspend fun existsInDatabase(metaData: DownloadMetadata): Boolean =
        fakeDatabase.contains(metaData)

    suspend fun getFromLocalDataBase(id: String): Single<DownloadMetadata> =
        Single.just(fakeDatabase.first { it.availId == id })

    suspend fun getFromLocalDatabase(ids: List<String>): List<DownloadMetadata> =
        fakeDatabase.filter { ids.contains(it.availId) }

    suspend fun writeToDatabase(metaData: DownloadMetadata) {
        fakeDatabase.firstOrNull { it.availId == metaData.availId }?.let { fakeDatabase.remove(it) }
        fakeDatabase.add(metaData)
    }
}
