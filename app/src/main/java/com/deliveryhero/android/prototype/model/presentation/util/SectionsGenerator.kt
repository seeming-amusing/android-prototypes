package com.deliveryhero.android.prototype.model.presentation.util

import com.deliveryhero.android.prototype.data.util.RandomObjectGenerator.generateRandomStrings
import com.deliveryhero.android.prototype.model.presentation.Section
import java.util.*

/**
 * Generates [Section] objects for consumption. This may be used by any prototype project to
 */
object SectionsGenerator {

    private val R = Random()

    fun generateSections(number: Int): List<Section> = generateRandomStrings(number, number).withIndex().map {
        val id = "[SEC ${it.index + 1}]"
        val subsectionIds = generateRandomStrings(1, 5)
        generateSection(id, sectionName = "$id ${it.value.substring(0, 8)} (${subsectionIds.size})",
                subsectionIds = subsectionIds)
    }

    private fun generateSection(sectionId: String, sectionName: String, subsectionIds: List<String>? = null,
                                items: List<String> = listOf()): Section = subsectionIds.toSubsections(sectionId).let {
        Section(sectionId, sectionName, items = items, subsections = it,
                description = "Description for the randomly-generated section: $sectionId")
    }

    private fun List<String>?.toSubsections(sectionId: String) = this?.withIndex()?.map {
        val subsectionId = "$sectionId:[${it.index + 1}] ${it.value.substring(0, R.nextInt(15) + 1)}"
        generateSection(subsectionId, subsectionId, items = generateRandomStrings(15, 20))
    } ?: listOf<Section>()
}