package krono.forms

import kommander.expect
import kase.Submitting
import kase.Success
import kase.Validating
import kollections.toIList
import koncurrent.Later
import kotlinx.serialization.Serializable
import krono.LocalDate
import krono.date
import cinematic.expect
import cinematic.toHaveGoneThrough3
import symphony.Option
import symphony.Fields
import symphony.Form
import symphony.FormConfig
import symphony.name
import symphony.selectSingle
import kotlin.test.Test

class FormWithManyInputsTest {

    @Serializable
    enum class Color {
        Red, Green, Blue
    }

    class AllFields : Fields() {
        val name = name(isRequired = true)
        val dob = date(name = "dob", isRequired = true)
        val color = selectSingle(
            name = "color",
            items = Color.values().toIList(),
            mapper = { Option(it.name) },
            isRequired = true
        )
    }

    @Serializable
    class AllParams(
        val name: String,
        val dob: LocalDate,
        val color: Color
    )

    @Test
    fun should_be_able_to_submit_fields() {
        var params: AllParams? = null
        val form = Form<AllFields, AllParams, Any?>(
            heading = "The god form",
            details = "A form to test things out",
            fields = AllFields(),
            config = FormConfig(exitOnSubmitted = false)
        ) {
            onSubmit {
                params = it
                Later(it)
            }
        }
        form.fields.apply {
            name.type("Andy")
            dob.set("2022-01-01")
            color.selectItem(Color.Red)
        }
        form.submit()
        expect(form.ui).toHaveGoneThrough3<Validating, Submitting, Success<Any?>>()
        expect(params?.name).toBe("Andy")
        expect(params?.dob).toBe(LocalDate(2022, 1, 1))
        expect(params?.color).toBe(Color.Red)
    }
}