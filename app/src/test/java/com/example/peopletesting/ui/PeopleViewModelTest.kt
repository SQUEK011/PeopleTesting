package com.example.peopletesting.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.peopletesting.network.People
import com.example.peopletesting.network.PeopleApiService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class PeopleViewModelTest {

    @get:Rule
    val testInstantExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var retrofit: PeopleApiService

    @Mock
    private lateinit var retrofitObserver: Observer<List<People>>

    @Mock
    private lateinit var statusObserver: Observer<PeopleApiStatus>

    @Test
    fun retrofitService_shouldReturnSuccess(){
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<People>()).`when`(retrofit).getPeople()
            val viewModel = PeopleViewModel(retrofit)
            viewModel.getPeople().observeForever(retrofitObserver)
            viewModel.getStatus().observeForever(statusObserver)
            verify(retrofit).getPeople()
            verify(retrofitObserver).onChanged(emptyList())
            verify(statusObserver).onChanged(PeopleApiStatus.DONE)
            viewModel.getPeople().removeObserver(retrofitObserver)
            viewModel.getStatus().removeObserver(statusObserver)
        }
    }

    @Test
    fun retrofitService_shouldReturnFailure() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage)).`when`(retrofit).getPeople()
            val viewModel = PeopleViewModel(retrofit)
            viewModel.getPeople().observeForever(retrofitObserver)
            viewModel.getStatus().observeForever(statusObserver)
            verify(retrofit).getPeople()
            verifyNoInteractions(retrofitObserver)
            verify(statusObserver).onChanged(PeopleApiStatus.ERROR)
            viewModel.getPeople().removeObserver(retrofitObserver)
            viewModel.getStatus().removeObserver(statusObserver)
        }
    }
}