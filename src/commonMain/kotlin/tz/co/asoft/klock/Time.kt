package tz.co.asoft.klock

import kotlin.math.abs

inline class Time(val encoded: TimeSpan) : Comparable<Time> {
	companion object {
		operator fun invoke(hour: Int, minute: Int = 0, second: Int = 0, millisecond: Int = 0): Time =
			Time(hour.hours + minute.minutes + second.seconds + millisecond.milliseconds)

		private const val DIV_MILLISECONDS = 1
		private const val DIV_SECONDS = DIV_MILLISECONDS * 1000
		private const val DIV_MINUTES = DIV_SECONDS * 60
		private const val DIV_HOURS = DIV_MINUTES * 60
	}
	val millisecond: Int get() = abs((encoded.millisecondsInt / DIV_MILLISECONDS) % 1000)
	val second: Int get() = abs((encoded.millisecondsInt / DIV_SECONDS) % 60)
	val minute: Int get() = abs((encoded.millisecondsInt / DIV_MINUTES) % 60)
	val hour: Int get() = (encoded.millisecondsInt / DIV_HOURS)

	override fun toString(): String = "${if (hour < 0) "-" else ""}${abs(hour).toString().padStart(2, '0')}:${abs(minute).toString().padStart(2, '0')}:${abs(second).toString().padStart(2, '0')}.${abs(millisecond).toString().padStart(3, '0')}"

	override fun compareTo(other: Time): Int = encoded.compareTo(other.encoded)
}

// @TODO: Do overflowing here instead of relying on DateTime. In fact, refact DateTime to handle the overflowing using this class
operator fun Time.plus(span: TimeSpan) = (DateTime.EPOCH + this.encoded + span).time
