package ru.skillbranch.devintensive

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.models.*
import java.util.*

class MessageTest {
	
	@Test
	fun `make message test`(){
		val date = Date()
		val chat = Chat("0")
		val user = User("0","Василий", null)
		assertEquals("Василий отправил сообщение \"any text message\" только что", BaseMessage.makeMessage(user, chat, date, "text", "any text message").formatMessage())
		
		date.add(-2, TimeUnits.HOUR)
		assertEquals("Василий получил изображение \"https://anyurl.com\" 2 часа назад", BaseMessage.makeMessage(user, chat, date, "image", "https://anyurl.com", true).formatMessage())
	}
	
	@Test
	fun `another makeMessage test`(){
		val user = User.makeUser("Иван Кузьмин")
		var message = BaseMessage.makeMessage(user, Chat("0"), Date(), "text", "any text message")
		assertTrue(message is TextMessage)
		assertEquals("0", message.id)
		assertTrue(message.from === user)
		message = BaseMessage.makeMessage(user, Chat("0"), Date(), "image", "https://anyurl.com",true)
		assertTrue(message is ImageMessage)
		assertEquals("1", message.id)
		assertTrue(message.isIncoming)
		assertEquals((message as ImageMessage).image, "https://anyurl.com")
	}
	
}