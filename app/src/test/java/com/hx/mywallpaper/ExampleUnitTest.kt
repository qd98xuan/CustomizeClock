package com.hx.mywallpaper

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun average() {
        var arr = arrayOf(6,2,7,5,1,2,0,3,10,2,5,0,5,5,0,8,7,6,8,0)
        arr.sort()
        print(arr.copyOfRange(arr.size / 20,arr.size-arr.size / 20).average())

    }
}