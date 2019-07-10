package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.utils.Utils

class UtilsTest {

    val john = "John"
    val doe = "Doe"

    @Test
    fun transliteration_Test() {
        assertEquals("", Utils.transliteration(""))
        assertEquals("", Utils.transliteration("", "_"))
        assertEquals("_bv", Utils.transliteration(" бв", "_"))
        assertEquals("@1121@1121s@1121@1121@1121vab@1121@1121", Utils.transliteration("  с   ваб  ", "@1121"))
        assertEquals("_YAbV", Utils.transliteration(" ЯбВ", "_"))

    }

    @Test
    fun toInitials_Test() {
        assertEquals(null, Utils.toInitials(null, null))
        assertEquals("JD", Utils.toInitials(john, doe))
        assertEquals("J", Utils.toInitials(john, null))
        assertEquals(null, Utils.toInitials(" ", ""))
    }
}