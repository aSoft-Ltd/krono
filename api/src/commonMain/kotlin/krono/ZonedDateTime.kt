@file:Suppress("NON_EXPORTABLE_TYPE")

package krono

import kotlinx.JsExport

@JsExport
interface ZonedDateTime : DateLike, TimeLike, Dateable<ZonedDateTime>, TemporalComparable<ZonedDateTime> {
    val zone: TimeZone
    val date: LocalDate
    val time: LocalTime
}