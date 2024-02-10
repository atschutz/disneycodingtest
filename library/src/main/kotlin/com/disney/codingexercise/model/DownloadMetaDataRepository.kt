package com.disney.codingexercise.model

import com.disney.codingexercise.DownloadMetadata

class DownloadMetaDataRepository(
    private val dbManager: DBManager = DBManager(),
    private val webService: DownloadMetaDataWebService = DownloadMetaDataWebService()
) {
    suspend fun getDownloadMetadata(ids: List<String>): List<DownloadMetadata> {
        val dbList = mutableListOf<DownloadMetadata>()
        val notFoundIdList = mutableListOf<String>()

        ids.forEach { id ->
            if (dbManager.existsInDatabase(id)) {
                dbList += dbManager.getFromLocalDataBase(id).blockingGet()
            } else {
                notFoundIdList += id
            }
        }

        // TODO - Error handling.
        return (webService.getDownloadMetadata(notFoundIdList).body()?.data
            // If we build DownloadMetadataApi correctly, we don't need to filter here.
            ?.filter {
                notFoundIdList.contains(it.availId)
            } ?: listOf()) + dbList
    }

    suspend fun storeDownloadMetadata(metadata: DownloadMetadata) =
        dbManager.writeToDatabase(metadata)
}