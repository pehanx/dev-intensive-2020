package ru.skillbranch.devintensive

import org.junit.Assert
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.models.BaseMessage
import ru.skillbranch.devintensive.models.Chat
import ru.skillbranch.devintensive.models.User
import java.util.*

class MessageTest {
	
	@Test
	fun `make message test`(){
		val date = Date()
		val chat = Chat("0")
		val user = User("0","Василий", null)
		Assert.assertEquals("Василий отправил сообщение \"any text message\" только что", BaseMessage.makeMessage(user, chat, date, "any text message", "text").formatMessage())
		
		date.add(-2, TimeUnits.HOUR)
		Assert.assertEquals("Василий получил изображение \"https://anyurl.com\" 2 часа назад", BaseMessage.makeMessage(user, chat, date, "https://anyurl.com", "image", true).formatMessage())
	}
	
}