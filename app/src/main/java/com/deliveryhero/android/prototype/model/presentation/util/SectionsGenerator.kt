package com.deliveryhero.android.prototype.model.presentation.util

import com.deliveryhero.android.prototype.data.util.RandomObjectGenerator.generateRandomStrings
import com.deliveryhero.android.prototype.model.presentation.Section
import java.util.ArrayList
import java.util.Random

/**
 * Generates [Section] objects for consumption. This may be used by any prototype project to
 */
object SectionsGenerator {

    private val R = Random()

    fun generateSections(number: Int): List<Section> {
        val sectionIds = generateRandomStrings(number, number)
        val sections = ArrayList<Section>(sectionIds.size)
        for (i in sectionIds.indices) {
            val sectionId = "[SEC" + (i + 1) + "]"
            val subsections = generateRandomStrings(1, 5)
            val sectionName = sectionId + " " + sectionIds.get(i).substring(0, 8) + " (" + subsections.size + ")"
            sections.add(generateSection(sectionId, sectionName, subsections))
        }
        return sections
    }

    private fun generateSection(sectionId: String, sectionName: String,
                                subsections: List<String>? = null, items: List<String>? = null): Section {
        val description = "Description for the randomly-generated section: $sectionId"
        val section = Section(sectionId, sectionName, description)
        if (items != null) section.items = items
        if (subsections != null) {
            for (i in subsections.indices) {
                val endIndex = R.nextInt(15) + 1
                val subsectionId = "$sectionId:[" + (i + 1) + "] " + subsections[i].substring(0, endIndex)
                section.subsections.add(generateSection(subsectionId, subsectionId,
                        items = generateRandomStrings(15, 20)))
            }
        }
        return section
    }
}