package com.deliveryhero.android.prototype.model.presentation

data class Section(val id: String,
                   val name: String,
                   val description: String,
                   val subsections: List<Section>,
                   val items: List<String>) {
    override fun toString() = name
}