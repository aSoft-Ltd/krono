@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package krono

import symphony.properties.Bounded
import symphony.properties.Patterned
import symphony.TransformingRangeInputField
import kotlinx.JsExport

interface DateRangeInputField : TransformingRangeInputField<String, LocalDate>, Bounded<LocalDate>, Patterned {
    override val start: DateOutputField
    override val end: DateInputField
}