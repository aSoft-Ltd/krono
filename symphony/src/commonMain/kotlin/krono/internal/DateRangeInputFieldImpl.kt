package krono.internal

import kotlinx.serialization.KSerializer
import krono.LocalDate
import krono.serializers.LocalDateIsoSerializer
import cinematic.MutableLive
import cinematic.mutableLiveOf
import krono.DateInputField
import krono.DateRangeInputField
import symphony.Label
import symphony.Range
import symphony.InputFieldState
import symphony.internal.FormattedData
import symphony.internal.TransformedDataField
import symphony.internal.utils.Clearer
import symphony.internal.utils.DataTransformer
import symphony.internal.validators.CompoundValidator
import symphony.internal.validators.LambdaValidator
import symphony.internal.validators.RangeValidator
import symphony.internal.validators.RequirementValidator

@PublishedApi
internal class DateRangeInputFieldImpl(
    override val name: String,
    override val isRequired: Boolean,
    override val label: Label,
    override val hint: String,
    private val defaultStart: LocalDate?,
    private val defaultEnd: LocalDate?,
    override val isReadonly: Boolean,
    override val pattern: String,
    override val max: LocalDate?,
    override val min: LocalDate?,
    validator: ((Range<LocalDate>?) -> Unit)?
) : TransformedDataField<String, Range<LocalDate>>(Range.of(defaultStart, defaultEnd)), DateRangeInputField {
    override val transformer: DataTransformer<String, Range<LocalDate>> get() = error("Setter should be overriden")

    override val start = DateInputField(
        name = "$name-start", isRequired,
        label = "$name start",
        hint = "Start Date",
        value = defaultStart,
        isReadonly = isReadonly,
        pattern = pattern,
        maxDate = max,
        minDate = min
    )

    override val end = DateInputField(
        name = "$name-end", isRequired,
        label = "$name end",
        hint = "End Date",
        value = defaultEnd,
        isReadonly = isReadonly,
        pattern = pattern,
        maxDate = max,
        minDate = min
    )

    override val cv = CompoundValidator(
        data, feedback,
        RequirementValidator(data, feedback, label.capitalizedWithoutAstrix(), isRequired),
        RangeValidator(data, feedback, isRequired, label.capitalizedWithoutAstrix(), max, min),
        LambdaValidator(data, feedback, validator)
    )

    private fun update(s: LocalDate?, e: LocalDate?) {
        if (s != null && e != null) {
            if (s <= e) {
                data.value = FormattedData("", "", Range(s, e))
                feedback.value = InputFieldState.Empty
            } else {
                data.value = FormattedData("", "", null)
                val message = "${label.capitalizedWithoutAstrix()} can't range from $s to $e"
                feedback.value = InputFieldState.Warning(message, IllegalArgumentException(message))
            }
        } else if (s == null) {
            val message = "${label.capitalizedWithoutAstrix()} start is required"
            feedback.value = InputFieldState.Warning(message, IllegalStateException(message))
        } else if (e == null) {
            val message = "${label.capitalizedWithoutAstrix()} end is required"
            feedback.value = InputFieldState.Warning(message, IllegalStateException(message))
        }
    }

    override fun set(value: String?) = error("Use setStart / setEnd methods")

    private val clearer = Clearer(default, data, feedback)
    override fun clear() {
        start.clear()
        end.clear()
        clearer.clear()
    }

    override fun setStart(value: String?) {
        start.set(value)
        val s = start.data.value.output
        val e = end.data.value.output
        update(s, e)
    }

    override fun setEnd(value: String?) {
        end.set(value)
        val s = start.data.value.output
        val e = end.data.value.output
        update(s, e)
    }

    override val serializer: KSerializer<Range<LocalDate>> = inputSerializer

    private companion object {
        val inputSerializer = Range.serializer(LocalDateIsoSerializer)
    }
}