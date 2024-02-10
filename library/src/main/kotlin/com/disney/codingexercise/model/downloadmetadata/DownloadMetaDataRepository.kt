package com.disney.codingexercise.model.downloadmetadata

import android.util.Log
import org.jetbrains.annotations.VisibleForTesting

class DownloadMetaDataRepository(
    @VisibleForTesting val dbManager: DBManager = DBManager(),
    private val webService: DownloadMetaDataWebService = DownloadMetaDataWebService(),
) {
    suspend fun getDownloadMetadata(ids: List<String>): List<DownloadMetadata> {
        val dbList = dbManager.getFromLocalDatabase(ids)
        val notFoundIdList = ids.filterNot { id -> dbList.map { it.availId }.contains(id) }

        val webResponse = webService.getDownloadMetadata(notFoundIdList)

        return if (webResponse.isSuccessful) {
            // If we build DownloadMetadataApi correctly, we don't need to filter here.
            val metadata =
                webResponse.body()?.data?.filter {
                    notFoundIdList.contains(it.availId)
                } ?: listOf()

            metadata + dbList
        } else {
            //TODO - Proper error handling here.
            Log.e("Error fetching DownloadMetadata", "${webResponse.code()}")
            listOf()
        }
    }

    suspend fun storeDownloadMetadata(metadata: DownloadMetadata) =
        dbManager.writeToDatabase(metadata)
}
