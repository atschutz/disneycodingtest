package com.disney.codingexercise.downloadmetadata

import com.disney.codingexercise.model.downloadmetadata.DownloadMetadata
import com.disney.codingexercise.model.downloadmetadata.DownloadMetaDataRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DownloadMetadataRepositoryTest {
    private lateinit var repository: DownloadMetaDataRepository

    @Before
    fun setup() {
        // TODO - Feed the repository a mock DBManager and Webservice so we can test that our
        // TODO - responses truly match.
        repository = DownloadMetaDataRepository()
    }

    @Test
    fun `verify get metadata from webservice`() = runTest {
        val metadataList = repository.getDownloadMetadata(listOf("three", "four"))

        // TODO - Compare actual data.
        assertEquals(metadataList.size, 2)
    }

    @Test
    fun `verify get metadata from database`() = runTest {
        val metadataList = repository.getDownloadMetadata(listOf("one"))

        // TODO - Compare actual data.
        assertEquals(metadataList.size, 1)
    }

    @Test
    fun `verify get metadata from webservice and database`() = runTest {
        val metadataList = repository.getDownloadMetadata(listOf("two", "three", "four"))

        // TODO - Compare actual data.
        assertEquals(metadataList.size, 3)
    }

    @Test
    fun `verify nonexistent entry yields no results`() = runTest {
        val metadataList = repository.getDownloadMetadata(listOf("six", "seven", "eight"))

        assertEquals(metadataList, listOf<DownloadMetadata>())
    }

    @Test
    fun `verify write to database`() = runTest {
        val newMetadata = DownloadMetadata(
            availId = "123",
            resourceId = "456",
            title = "test",
            runtimeMs = 123456L,
            elapsedMs = 4321L,
        )

        repository.storeDownloadMetadata(newMetadata)

        assert(repository.dbManager.existsInDatabase(newMetadata))
    }

    @Test
    fun `verify overwrite existing entry in database`() = runTest {
        val oldMetadata = DownloadMetadata(
            availId = "123",
            resourceId = "456",
            title = "old",
            runtimeMs = 123456L,
            elapsedMs = 4321L,
        )

        val newMetadata = DownloadMetadata(
            availId = "123",
            resourceId = "456",
            title = "new",
            runtimeMs = 123456L,
            elapsedMs = 4321L,
        )

        repository.storeDownloadMetadata(oldMetadata)
        repository.storeDownloadMetadata(newMetadata)

        assert(
            repository.dbManager.existsInDatabase(newMetadata) &&
            !repository.dbManager.existsInDatabase(oldMetadata)
        )
    }

    @Test
    fun `verify report proper error code`() {
        // TODO - Write this once we have proper error handling.
    }
}
