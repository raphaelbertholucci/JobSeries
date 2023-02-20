package com.bertholucci.home.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin

@RunWith(AndroidJUnit4::class)
class SearchFragmentTest {

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun checkIfStaticTextsAreDisplayed() {
        setupView {
        } execute {
        } check {
            checkStaticViews()
        }
    }

    @Test
    fun typeOneTrackName_AndCheckIfSongIsShown() {
        setupView {
            mockShows()
        } execute {
            typeShow("Arrow")
        } check {
            checkTracks()
        }
    }
}