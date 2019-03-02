package com.merricklabs.ktane

import org.testng.Assert
import org.testng.annotations.Test

class PasswordCrackerTest {

    @Test
    fun `Three column test`(){
        val columns = listOf(
                "dcwpjz",
                "niexrg",
                "nidsbz"
        )

        val cracker = PasswordCracker(columns)
        val solution = cracker.crackPassword()
        val candidates = cracker.words.find { it.startsWith(solution) }
        Assert.assertTrue(candidates!!.contains("write"))
    }

    @Test
    fun `Five column test`(){
        val columns = listOf(
                "dcwpjz",
                "niexrg",
                "nidsbz",
                "xxxtxx",
                "xxexxx"
        )

        val cracker = PasswordCracker(columns)
        val solution = cracker.crackPassword()
        Assert.assertEquals(solution, "write")
    }
}