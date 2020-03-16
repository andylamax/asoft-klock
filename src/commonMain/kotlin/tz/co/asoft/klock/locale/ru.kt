package tz.co.asoft.klock.locale

import tz.co.asoft.klock.DayOfWeek
import tz.co.asoft.klock.KlockLocale

val KlockLocale.Companion.russian get() = RussianKlockLocale

open class RussianKlockLocale : KlockLocale() {
	companion object : RussianKlockLocale()

	override val ISO639_1 = "ru"

	override val h12Marker = listOf("ДП", "ПП")

	override val firstDayOfWeek: DayOfWeek = DayOfWeek.Monday

	override val daysOfWeek = listOf(
		"воскресенье", "понедельник", "вторник", "среда", "четверг", "пятница", "суббота"
	)
	override val months = listOf(
		"января", "февраля", "марта", "апреля", "мая", "июня",
		"июля", "августа", "сентября", "октября", "ноября", "декабря"
	)

	override val formatDateTimeMedium = format("d MMM y г. H:mm:ss")
	override val formatDateTimeShort = format("dd.MM.y H:mm")

	override val formatDateFull = format("EEEE, d MMMM y г.")
	override val formatDateLong = format("d MMMM y г.")
	override val formatDateMedium = format("d MMM y г.")
	override val formatDateShort = format("dd.MM.y")

	override val formatTimeMedium = format("H:mm:ss")
	override val formatTimeShort = format("H:mm")
}
