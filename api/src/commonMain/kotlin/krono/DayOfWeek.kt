@file:JsExport

package krono

import kotlinx.JsExport

enum class DayOfWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    val number get() = ordinal + 1
}