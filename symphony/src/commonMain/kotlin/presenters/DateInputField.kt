@file:JsExport

package presenters

import krono.LocalDate
import presenters.properties.Typeable
import kotlinx.JsExport

@Deprecated("use krono instead")
interface DateInputField : TransformingInputField<String, LocalDate>, DateOutputField, Typeable