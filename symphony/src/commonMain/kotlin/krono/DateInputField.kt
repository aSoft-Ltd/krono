@file:JsExport

package krono

import symphony.properties.Typeable
import symphony.TransformingInputField
import kotlin.js.JsExport

interface DateInputField : TransformingInputField<String, LocalDate>, DateOutputField, Typeable