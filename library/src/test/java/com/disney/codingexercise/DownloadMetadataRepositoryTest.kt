package com.disney.codingexercise

import com.disney.codingexercise.model.DownloadMetaDataRepository
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
}