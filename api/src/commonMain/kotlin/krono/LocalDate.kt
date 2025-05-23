@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package krono

import kotlinx.serialization.Serializable
import krono.serializers.LocalDateIsoSerializer
import kotlinx.JsExport

@Serializable(with = LocalDateIsoSerializer::class)
interface LocalDate : DateLike, Dateable<LocalDate>, TemporalComparable<LocalDate>