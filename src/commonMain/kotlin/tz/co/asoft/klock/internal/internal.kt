package tz.co.asoft.klock.internal

import kotlin.math.*

internal const val MILLIS_PER_SECOND = 1000
internal const val MILLIS_PER_MINUTE = MILLIS_PER_SECOND * 60 // 60_000
internal const val MILLIS_PER_HOUR = MILLIS_PER_MINUTE * 60 // 3600_000
internal const val MILLIS_PER_DAY = MILLIS_PER_HOUR * 24 // 86400_000
internal const val MILLIS_PER_WEEK = MILLIS_PER_DAY * 7 // 604800_000

internal fun Int.padded(count: Int) = this.toString().padStart(count, '0')
internal fun Double.padded(intCount: Int, decCount: Int): String {
    val intPart = floor(this).toInt()
    val decPart = round((this - intPart) * 10.0.pow(decCount)).toInt()
    return "${intPart.padded(intCount).substr(-intCount, intCount)}.${decPart.toString().padEnd(decCount, '0').substr(0, decCount)}"
}

internal fun String.substr(start: Int, length: Int): String {
    val low = (if (start >= 0) start else this.length + start).clamp(0, this.length)
    val high = (if (length >= 0) low + length else this.length + length).clamp(0, this.length)
    return if (high < low) "" else this.substring(low, high)
}

internal fun Int.clamp(min: Int, max: Int): Int = if (this < min) min else if (this > max) max else this
internal fun Int.cycle(min: Int, max: Int): Int = ((this - min) umod (max - min + 1)) + min
internal fun Int.cycleSteps(min: Int, max: Int): Int = (this - min) / (max - min + 1)

internal fun String.splitKeep(regex: Regex): List<String> {
    val str = this
    val out = arrayListOf<String>()
    var lastPos = 0
    for (part in regex.findAll(this)) {
        val prange = part.range
        if (lastPos != prange.start) {
            out += str.substring(lastPos, prange.start)
        }
        out += str.substring(prange)
        lastPos = prange.endInclusive + 1
    }
    if (lastPos != str.length) {
        out += str.substring(lastPos)
    }
    return out
}

internal infix fun Int.umod(that: Int): Int {
    val remainder = this % that
    return when {
        remainder < 0 -> remainder + that
        else -> remainder
    }
}

internal class Moduler(val value: Double) {
    private var avalue = abs(value)
    private val sign = sign(value)

    fun double(count: Double): Double {
        val ret = (avalue / count)
        avalue %= count
        return floor(ret) * sign
    }

    inline fun double(count: Number): Double = double(count.toDouble())
    inline fun int(count: Number): Int = double(count.toDouble()).toInt()
}

internal infix fun Double.intDiv(other: Double) = floor(this / other)
