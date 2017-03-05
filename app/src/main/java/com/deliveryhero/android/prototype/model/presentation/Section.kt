package com.deliveryhero.android.prototype.model.presentation

data class Section(val id: String,
                   val name: String,
                   val description: String) {

    val subsections: MutableList<Section> = mutableListOf()
    var items: List<String>? = null
}