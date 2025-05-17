@file:JsExport

package krono

import symphony.properties.Typeable
import symphony.TransformingInputField
import kotlinx.JsExport

interface DateInputField : TransformingInputField<String, LocalDate>, DateOutputField, Typeable