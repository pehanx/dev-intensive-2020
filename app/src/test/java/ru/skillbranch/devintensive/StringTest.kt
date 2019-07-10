package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.skillbranch.devintensive.extensions.stripHtml
import ru.skillbranch.devintensive.extensions.truncate

class StringTest {
	
	@Test
	fun truncate_Test(){
		assertEquals("Bender Bending R...", "Bender Bending Rodriguez".truncate())
		assertEquals("Bender Bending...", "Bender Bending Rodriguez".truncate(15))
		assertEquals("A", "A     ".truncate(3))
	}
	
	@Test
	fun `test stripHtml fun`(){
		assertEquals("Образовательное IT-сообщество Skill Branch", "<p class=\"title\">Образовательное IT-сообщество Skill Branch</p>".stripHtml())
		assertEquals("Образовательное IT-сообщество Skill Branch", "<p>Образовательное       IT-сообщество Skill Branch</p>".stripHtml())
	}
}