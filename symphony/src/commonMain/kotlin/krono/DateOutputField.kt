@file:JsExport

package krono

import symphony.SerializableLiveFormattedData
import symphony.properties.Bounded
import symphony.properties.Patterned
import kotlinx.JsExport

interface DateOutputField : SerializableLiveFormattedData<String, LocalDate>, Bounded<LocalDate>, Patterned