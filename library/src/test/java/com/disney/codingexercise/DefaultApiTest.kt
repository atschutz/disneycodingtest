package com.disney.codingexercise

import com.disney.codingexercise.model.downloadmetadata.DownloadMetadata
import org.junit.Before
import org.junit.Test


class DefaultApiTest {

    lateinit var api: Api

    @Before
    fun setup() {
        api = DefaultApi()
    }

    @Test
    fun `verify fetch`() {
        api.fetch(listOf("one", "two", "three", "four"))
            .test()
            .await()
            .assertValue {
                it.size == 4
            }
            .assertNoErrors()
    }

    @Test
    fun `verify store`() {
        val item = DownloadMetadata("newItem")

        api.store(
            DownloadMetadata(item.availId)
        ).andThen(
            api.fetch(listOf(item.availId))
        )
            .test()
            .await()
            .assertValue { it.contains(item) }
    }

    @Test
    fun `verify fetch error`() {
        api.fetch(listOf("one", "two", "three", "four"), ERROR_DOWNLOAD_METADATA)
            .test()
            .await()
    }
}
