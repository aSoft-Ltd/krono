@file:JsExport

package krono

import kotlinx.serialization.Serializable
import krono.serializers.LocalTimeIsoSerializer
import kotlinx.JsExport

@Serializable(with = LocalTimeIsoSerializer::class)
interface LocalTime : TimeLike, TemporalComparable<LocalTime>