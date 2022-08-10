package com.picpay.desafio.android

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.picpay.desafio.android.di.BaseUrlModule
import com.picpay.desafio.android.extensions.asJsonString
import com.picpay.desafio.android.presenter.activity.MainActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@UninstallModules(BaseUrlModule::class)
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    private lateinit var server: MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer().apply {
            start(8080)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            server.enqueue(MockResponse().setBody("user_success_response.json".asJsonString()))

            val expectedTitle = "Contatos"

            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayRecyclerView() {
        launchActivity<MainActivity>().apply {
            server.enqueue(MockResponse().setBody("user_success_response.json".asJsonString()))

            onView(
                withId(R.id.recyclerView)
            ).check(
                matches(isDisplayed())
            )
        }
    }

    @Test
    fun shouldDisplayMockContact() {
        launchActivity<MainActivity>().apply {
            server.enqueue(MockResponse().setBody("user_success_response.json".asJsonString()))

            onView(
                withText("Eduardo Santos")
            ).check(
                matches(isDisplayed())
            )
        }
    }

    @Test
    fun whenServerReturnError_shouldNotShowRecyclerView()  {
        launchActivity<MainActivity>().apply {
            server.enqueue(MockResponse().setResponseCode(400))
            onView(
                withId(R.id.recyclerView)
            ).check(
                matches(not(isDisplayed()))
            )
        }
    }
}