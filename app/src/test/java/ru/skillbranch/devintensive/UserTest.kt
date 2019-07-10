package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.models.User
import java.util.*


class UserTest {

    val john = "John"
    val doe = "Doe"

    @Before
    fun setUp(){
        User::class.java.getDeclaredField("lastId").also {
            it.isAccessible = true
            it.setInt(null, -1)
        }
    }

    @Test
    fun makeUserAndParseFullName_Test() {
        User.makeUser(null).apply {
            assertEquals("0", id)
            assertEquals(null, firstName)
            assertEquals(null, lastName)
        }
        User.makeUser("").apply {
            assertEquals("1", id)
            assertEquals(null, firstName)
            assertEquals(null, lastName)
        }
        User.makeUser(" ").apply {
            assertEquals("2", id)
            assertEquals(null, firstName)
            assertEquals(null, lastName)
        }
        User.makeUser(john).apply {
            assertEquals("3", id)
            assertEquals(john, firstName)
            assertEquals(null, lastName)
        }
        User.makeUser("$john $doe").apply {
            assertEquals("4", id)
            assertEquals(john, firstName)
            assertEquals(doe, lastName)
        }
        User.makeUser("    $john     $doe  ").apply {
            assertEquals("5", id)
            assertEquals(john, firstName)
            assertEquals(doe, lastName)
        }
    }

    @Test
    fun Builder_Test(){
        User.makeUser(null).apply {
            assertEquals("0", id)
            assertEquals(null, firstName)
            assertEquals(null, lastName)
        }
        assertEquals("1", User.Builder().build().id)
        val lastVisit = Date().add(-5, TimeUnits.DAY)
        val user = User.Builder()
                .id("11")
                .firstName(john)
                .lastName(doe)
                .avatar("ss")
                .isOnline(true)
                .lastVisit(lastVisit)
                .rating(10)
                .respect(11)
                .build()

        assertEquals("11", user.id)
        assertEquals(john, user.firstName)
        assertEquals(doe, user.lastName)
        assertEquals("ss", user.avatar)
        assertEquals(true, user.isOnline)
        assertEquals(lastVisit, user.lastVisit)
        assertEquals(10, user.rating)
        assertEquals(11, user.respect)

    }
}
