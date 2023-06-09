package krono.internal

import kotlinx.serialization.KSerializer
import krono.LocalDate
import krono.serializers.LocalDateIsoSerializer
import cinematic.MutableLive
import cinematic.mutableLiveOf
import krono.DateInputField
import symphony.InputFieldState
import symphony.Label
import symphony.Formatter
import symphony.internal.TransformedDataField
import symphony.internal.utils.Clearer
import symphony.internal.utils.DataTransformer
import symphony.internal.utils.FormattedOutputSetter
import symphony.internal.utils.Typer
import symphony.internal.validators.CompoundValidator
import symphony.internal.validators.LambdaValidator
import symphony.internal.validators.ClippingValidator
import symphony.internal.validators.RequirementValidator

@PublishedApi
internal class DateInputFieldImpl(
    override val name: String,
    override val isRequired: Boolean,
    override val label: Label,
    override val hint: String,
    private val value: LocalDate?,
    override val isReadonly: Boolean,
    override val pattern: String,
    override val max: LocalDate?,
    override val min: LocalDate?,
    validator: ((LocalDate?) -> Unit)?
) : TransformedDataField<String, LocalDate>(value), DateInputField {

    override val serializer: KSerializer<LocalDate> = LocalDateIsoSerializer

    override val cv = CompoundValidator(
        data, feedback,
        RequirementValidator(data, feedback, label.capitalizedWithoutAstrix(), isRequired),
        ClippingValidator(data, feedback, label.capitalizedWithoutAstrix(), max, min),
        LambdaValidator(data, feedback, validator)
    )

    override val transformer = DATE_DATE_TRANSFORMER

    override fun type(text: String) = Typer(data.value.input, setter).type(text)

    companion object {
        val DEFAULT_PATTERN = "{MMM} {D}, {YYYY}"
        val DEFAULT_FORMATTER = Formatter<LocalDate> { it?.toIsoString() }
        val DEFAULT_DATE_TRANSFORMER = { iso: String? -> if (iso != null) LocalDate(iso) else null }
        val DATE_DATE_TRANSFORMER = DataTransformer(DEFAULT_FORMATTER, DEFAULT_DATE_TRANSFORMER)
    }
}