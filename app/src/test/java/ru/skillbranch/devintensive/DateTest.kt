package ru.skillbranch.devintensive

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.skillbranch.devintensive.extensions.TimeUnits
import ru.skillbranch.devintensive.extensions.add
import ru.skillbranch.devintensive.extensions.format
import ru.skillbranch.devintensive.extensions.humanizeDiff
import java.util.*

class DateTest {
	
	private lateinit var date: Date
	private lateinit var date2: Date
	
	@Before
	fun setUp() {
		val calendar = Calendar.getInstance()
		calendar.set(2019, 5, 1, 14, 10, 5)
		date = calendar.time
		date2 = calendar.time
	}
	
	@Test
	fun dateFormat_Test() {
		assertEquals("14:10:05 01.06.19", date.format())
		assertEquals("14:10", date.format("HH:mm"))
	}
	
	@Test
	fun dateAdd5Seconds_Test() {
		assertEquals("14:10:10 01.06.19", date.add(5, TimeUnits.SECOND).format())
	}
	
	@Test
	fun dateAdd5Minutes_Test() {
		assertEquals("14:15:05 01.06.19", date.add(5, TimeUnits.MINUTE).format())
	}
	
	@Test
	fun dateAdd5Hours_Test() {
		assertEquals("19:10:05 01.06.19", date.add(5, TimeUnits.HOUR).format())
	}
	
	@Test
	fun dateAdd5Days_Test() {
		assertEquals("14:10:05 06.06.19", date.add(5, TimeUnits.DAY).format())
	}
	
	@Test
	fun dateSub5Seconds_Test() {
		assertEquals("14:10:00 01.06.19", date.add(-5, TimeUnits.SECOND).format())
	}
	
	@Test
	fun dateSub5Minutes_Test() {
		assertEquals("14:05:05 01.06.19", date.add(-5, TimeUnits.MINUTE).format())
	}
	
	@Test
	fun dateSub5Hours_Test() {
		assertEquals("09:10:05 01.06.19", date.add(-5, TimeUnits.HOUR).format())
	}
	
	@Test
	fun dateSub5Days_Test() {
		assertEquals("14:10:05 27.05.19", date.add(-5, TimeUnits.DAY).format())
	}
	
	@Test
	fun toHumanizeDiff_2HoursAgo() {
		assertEquals("2 часа назад", date.add(-2, TimeUnits.HOUR).humanizeDiff(date2))
	}
	
	@Test
	fun toHumanizeDiff_5DaysAgo() {
		assertEquals("5 дней назад", date.add(-5, TimeUnits.DAY).humanizeDiff(date2))
	}
	
	@Test
	fun toHumanizeDiff_2MinutesAfter() {
		assertEquals("через 2 минуты", date.add(2, TimeUnits.MINUTE).humanizeDiff(date2))
	}
	
	@Test
	fun toHumanizeDiff_7DaysAfter() {
		assertEquals("через 7 дней", date.add(7, TimeUnits.DAY).humanizeDiff(date2))
	}
	
	@Test
	fun toHumanizeDiff_YearAgo() {
		assertEquals("более года назад", date.add(-400, TimeUnits.DAY).humanizeDiff(date2))
	}
	
	@Test
	fun toHumanizeDiff_YearAfter() {
		assertEquals("более чем через год", date.add(400, TimeUnits.DAY).humanizeDiff(date2))
	}
	
	@Test
	fun test_of_humanizeDiff_2() {
		// ----- Past -----
		assertEquals("только что", Date().add(-1, TimeUnits.SECOND).humanizeDiff())
		assertEquals("несколько секунд назад", Date().add(-45, TimeUnits.SECOND).humanizeDiff())
		assertEquals("минуту назад", Date().add(-46, TimeUnits.SECOND).humanizeDiff())
		assertEquals("1 минуту назад", Date().add(-76, TimeUnits.SECOND).humanizeDiff())
		assertEquals("минуту назад", Date().add(-1, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("2 минуты назад", Date().add(-2, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("3 минуты назад", Date().add(-3, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("45 минут назад", Date().add(-45, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("час назад", Date().add(-1, TimeUnits.HOUR).humanizeDiff())
		assertEquals("1 час назад", Date().add(-76, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("2 часа назад", Date().add(-120, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("3 часа назад", Date().add(-3, TimeUnits.HOUR).humanizeDiff())
		assertEquals("4 часа назад", Date().add(-4, TimeUnits.HOUR).humanizeDiff())
		assertEquals("5 часов назад", Date().add(-5, TimeUnits.HOUR).humanizeDiff())
		assertEquals("день назад", Date().add(-26, TimeUnits.HOUR).humanizeDiff())
		assertEquals("1 день назад", Date().add(-27, TimeUnits.HOUR).humanizeDiff())
		assertEquals("4 дня назад", Date().add(-4, TimeUnits.DAY).humanizeDiff())
		assertEquals("5 дней назад", Date().add(-5, TimeUnits.DAY).humanizeDiff())
		assertEquals("360 дней назад", Date().add(-360, TimeUnits.DAY).humanizeDiff())
		assertEquals("более года назад", Date().add(-361, TimeUnits.DAY).humanizeDiff())
		
		// ----- Future ------
		assertEquals("через несколько секунд", Date().add(1, TimeUnits.SECOND).humanizeDiff())
		assertEquals("через минуту", Date().add(1, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("через 2 минуты", Date().add(2, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("через 3 минуты", Date().add(3, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("через 5 минут", Date().add(5, TimeUnits.MINUTE).humanizeDiff())
		assertEquals("через час", Date().add(1, TimeUnits.HOUR).humanizeDiff())
		assertEquals("через 2 часа", Date().add(2, TimeUnits.HOUR).humanizeDiff())
		assertEquals("через 3 часа", Date().add(3, TimeUnits.HOUR).humanizeDiff())
		assertEquals("через 4 часа", Date().add(4, TimeUnits.HOUR).humanizeDiff())
		assertEquals("через 5 часов", Date().add(5, TimeUnits.HOUR).humanizeDiff())
		assertEquals("через день", Date().add(1, TimeUnits.DAY).humanizeDiff())
		assertEquals("через 4 дня", Date().add(4, TimeUnits.DAY).humanizeDiff())
		assertEquals("через 5 дней", Date().add(5, TimeUnits.DAY).humanizeDiff())
		assertEquals("через 148 дней", Date().add(148, TimeUnits.DAY).humanizeDiff())
		assertEquals("более чем через год", Date().add(400, TimeUnits.DAY).humanizeDiff())
	}
	
	@Test
	fun `test plural TimeUnits fun`() {
		assertEquals("1 секунду", TimeUnits.SECOND.plural(1))
		assertEquals("4 минуты", TimeUnits.MINUTE.plural(4))
		assertEquals("19 часов", TimeUnits.HOUR.plural(19))
		assertEquals("222 дня", TimeUnits.DAY.plural(222))
	}
}