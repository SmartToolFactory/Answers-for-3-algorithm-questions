package com.smarttoolfactory.answers

import com.google.common.truth.Truth
import org.junit.Test

class HTMLParseTest {

    @Test
    fun `Parse valid answer with Regex`() {
        val expected = "<h3>Title</h3>"

        val actual = parseStringWithRegex("### Title")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Parse valid answer with spaces with Regex`() {
        val expected = "<h2>This is a test</h2>"

        val actual = parseStringWithRegex("    ##    This is a test       ")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given no hashes should return invalid answer with Regex`() {
        val expected = "Invalid String"

        val actual = parseStringWithRegex("Title")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given no space after hashes should return invalid answer with Regex`() {
        val expected = "Invalid String"

        val actual = parseStringWithRegex("##MissingSpace")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given hash count greater than 6 should return invalid answer with Regex`() {
        val expected = "Invalid String"

        val actual = parseStringWithRegex("########## Too Many")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Parse valid answer with default Kotlin funcs`() {
        val expected = "<h3>Title</h3>"

        val actual = parseStringAssisted("### Title")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Parse valid answer with spaces with default Kotlin funcs`() {
        val expected = "<h2>This is a test</h2>"

        val actual = parseStringAssisted("    ##    This is a test       ")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given no hashes should return invalid answer with default Kotlin funcs`() {
        val expected = "Invalid String, Missing Space or Headers!"

        val actual = parseStringAssisted("Title")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given no space after hashes should return invalid answer with default Kotlin funcs`() {
        val expected = "Invalid String, Missing Space or Headers!"

        val actual = parseStringAssisted("##MissingSpace")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given hash count greater than 6 should return invalid answer with default Kotlin funcs`() {
        val expected = "Invalid String, Too many hashes!"

        val actual = parseStringAssisted("########## Too Many")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Parse valid answer with default`() {
        val expected = "<h3>Title</h3>"

        val actual = parseString("### Title")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Parse valid answer with spaces`() {
        val expected = "<h2>This is a test</h2>"

        val actual = parseString("    ##    This is a test       ")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given no hashes should return invalid answer`() {
        val expected = "Invalid String, no headers!"

        val actual = parseString("Title")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given no space after hashes should return invalid answer`() {
        val expected = "Invalid String, Missing Space!"

        val actual = parseString("##MissingSpace")
        Truth.assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Given hash count greater than 6 should return invalid answer`() {
        val expected = "Invalid String, too many hashes!"

        val actual = parseString("########## Too Many")
        Truth.assertThat(actual).isEqualTo(expected)
    }

}