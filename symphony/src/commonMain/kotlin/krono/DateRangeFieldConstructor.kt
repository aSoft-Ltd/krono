@file:Suppress("NOTHING_TO_INLINE")

package krono

import krono.internal.DateInputFieldImpl
import krono.internal.DateRangeInputFieldImpl
import symphony.Fields
import symphony.Label
import symphony.Range
import symphony.getOrCreate
import kotlin.reflect.KProperty


inline fun DateRangeInputField(
    name: String,
    label: String = name,
    hint: String = label,
    value: Range<LocalDate>? = null,
    pattern: String = DateInputFieldImpl.DEFAULT_PATTERN,
    isReadonly: Boolean = false,
    isRequired: Boolean = false,
    maxDate: LocalDate? = null,
    minDate: LocalDate? = null,
    noinline validator: ((Range<LocalDate>?) -> Unit)? = null
): DateRangeInputField = DateRangeInputFieldImpl(
    name = name,
    label = Label(label, isRequired),
    hint = hint,
    defaultStart = value?.start,
    defaultEnd = value?.end,
    isReadonly = isReadonly,
    isRequired = isRequired,
    max = maxDate,
    min = minDate,
    pattern = pattern,
    validator = validator,
)

fun Fields.dateRange(
    name: String,
    label: String = name,
    hint: String = label,
    value: Range<LocalDate>? = null,
    pattern: String = DateInputFieldImpl.DEFAULT_PATTERN,
    isReadonly: Boolean = false,
    isRequired: Boolean = false,
    maxDate: LocalDate? = null,
    minDate: LocalDate? = null,
    validator: ((Range<LocalDate>?) -> Unit)? = null
): DateRangeInputField = getOrCreate(name) {
    DateRangeInputField(name, label, hint, value, pattern, isReadonly, isRequired, maxDate, minDate, validator)
}

inline fun Fields.dateRange(
    name: KProperty<Any?>,
    label: String = name.name,
    hint: String = label,
    value: Range<LocalDate>? = null,
    pattern: String = DateInputFieldImpl.DEFAULT_PATTERN,
    isReadonly: Boolean = false,
    isRequired: Boolean = false,
    maxDate: LocalDate? = null,
    minDate: LocalDate? = null,
    noinline validator: ((Range<LocalDate>?) -> Unit)? = null
) = dateRange(name.name, label, hint, value, pattern, isReadonly, isRequired, maxDate, minDate, validator)