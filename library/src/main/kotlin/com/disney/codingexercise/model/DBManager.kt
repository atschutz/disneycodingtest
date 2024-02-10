package com.disney.codingexercise.model

import com.disney.codingexercise.DownloadMetadata
import io.reactivex.rxjava3.core.Completable
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
                30L
            )
        )
        fakeDatabase.add(
            DownloadMetadata(
                "two",
                "resourceTwo",
                "titleTwo",
                1001L,
                31L
            )
        )
    }

    // TODO - Coroutines.
    fun existsInDatabase(id: String): Boolean =
        fakeDatabase.firstOrNull { it.availId == id } != null

    fun getFromLocalDataBase(id: String): Single<DownloadMetadata> =
        Single.just(fakeDatabase.first { it.availId == id })

    fun writeToDatabase(item: DownloadMetadata): Completable =
        Completable.fromCallable { fakeDatabase.add(item) }
}