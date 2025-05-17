@file:JsExport

package krono.utils

import kotlinx.JsExport
import kotlin.js.JsName
import kotlin.math.floor

@JsName("centuryOf")
fun Century(year: Int): Int = floor(year.toDouble() / 100).toInt()