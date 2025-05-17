@file:JsExport

package krono

import kotlinx.JsExport

object TimeZones {
    val UTC = TimeZone("Z")
    val System = TimeZone(kotlinx.datetime.TimeZone.currentSystemDefault().id)
}