@file:JsExport

package krono

import kotlinx.JsExport

interface Dateable<out D : Dateable<D>> {
    fun atDate(date: Int): D

    fun atEndOfMonth(): D
}