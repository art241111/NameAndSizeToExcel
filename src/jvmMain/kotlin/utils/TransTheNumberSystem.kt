package utils

import kotlin.math.pow
import kotlin.math.roundToInt

private fun round(num: Double, roundNum: Int) = (num * 10.0.pow(roundNum)).roundToInt() / 10.0.pow(roundNum)

fun Long.toKbait(roundNum: Int = 2) = round(this * 0.0009766, roundNum)
fun Long.toMbait(roundNum: Int = 2) = round(this * 9.5367431 * 10.0.pow(-7), roundNum)