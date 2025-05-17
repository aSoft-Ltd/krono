@file:JsExport

package presenters

import krono.LocalDate
import presenters.properties.Bounded
import presenters.properties.Patterned
import kotlinx.JsExport

@Deprecated("use krono instead")
interface DateOutputField : SerializableLiveFormattedData<String, LocalDate>, Bounded<LocalDate>, Patterned