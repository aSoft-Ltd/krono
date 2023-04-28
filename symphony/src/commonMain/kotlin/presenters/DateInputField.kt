@file:JsExport

package presenters

import krono.LocalDate
import presenters.properties.Typeable
import kotlin.js.JsExport

@Deprecated("use krono instead")
interface DateInputField : TransformingInputField<String, LocalDate>, DateOutputField, Typeable