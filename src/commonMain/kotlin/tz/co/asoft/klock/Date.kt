package tz.co.asoft.klock

import kotlin.math.abs

inline class Date(val encoded: Int) : Comparable<Date> {
	companion object {
		operator fun invoke(year: Int, month: Int, day: Int) = Date((year shl 16) or (month shl 8) or (day shl 0))
		operator fun invoke(year: Int, month: Month, day: Int) = Date(year, month.index1, day)
		operator fun invoke(year: Year, month: Month, day: Int) = Date(year.year, month.index1, day)
		operator fun invoke(yearMonth: YearMonth, day: Int) = Date(yearMonth.yearInt, yearMonth.month1, day)
	}

	val year: Int get() = encoded shr 16
	val month1: Int get() = (encoded ushr 8) and 0xFF
	val month: Month get() = Month[month1]
	val day: Int get() = (encoded ushr 0) and 0xFF
	val yearYear: Year get() = Year(year)

	val dateTimeDayStart get() = DateTime(year, month, day)

	val dayOfYear get() = dateTimeDayStart.dayOfYear
	val dayOfWeek get() = dateTimeDayStart.dayOfWeek
	val dayOfWeekInt get() = dateTimeDayStart.dayOfWeekInt

	fun format(format: String) = dateTimeDayStart.format(format)
	fun format(format: DateFormat) = dateTimeDayStart.format(format)

	override fun toString(): String = "${if (year < 0) "-" else ""}${abs(year).toString()}-${abs(month1).toString().padStart(2, '0')}-${abs(day).toString().padStart(2, '0')}"
	override fun compareTo(other: Date): Int = this.encoded.compareTo(other.encoded)
}

operator fun Date.plus(time: TimeSpan) = (this.dateTimeDayStart + time).date
operator fun Date.plus(time: MonthSpan) = (this.dateTimeDayStart + time).date
operator fun Date.plus(time: DateTimeSpan) = (this.dateTimeDayStart + time).date
operator fun Date.plus(time: Time) = DateTime.createAdjusted(year, month1, day, time.hour, time.minute, time.second, time.millisecond)
