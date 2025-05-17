@file:JsExport

package krono

import kotlinx.JsExport

interface TemporalFormattable {
    fun format(pattern: String): String

    fun toIsoString(): String
}