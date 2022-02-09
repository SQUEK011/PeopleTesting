package com.example.peopletesting.network


import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PeopleApiServiceTests : BaseTest() {

    private lateinit var service: PeopleApiService

    @Before
    fun setup() {
        val url = mockWebServer.url("/")
        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build().create(PeopleApiService::class.java)
    }

    @Test
    fun api_service(){
        enqueue("peope_test.json")
        runBlocking {
            val apiResponse = service.getPeople()

            assertNotNull(apiResponse)
            assertTrue("The List was empty", apiResponse.isNotEmpty())
            assertEquals("The First ID did not match", 1, apiResponse[0].id)
            assertEquals("The Second ID did not match", 2, apiResponse[1].id)
        }
    }

    @Test
    fun api_users(){
        enqueue("peope_test.json")
        runBlocking {
            val apiResponse = service.getPeople()

            assertEquals("The first name did not match", "Milton Braun", apiResponse[0].name)
            assertEquals("The second name did not match", "Marcos Nitzsche II", apiResponse[1].name)
            assertEquals("The first job title did not match", "Product Mobility Coordinator", apiResponse[0].jobTitle)
            assertEquals("The second job title did not match", "Senior Usability Designer", apiResponse[1].jobTitle)
        }
    }

}