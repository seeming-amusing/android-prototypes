package com.deliveryhero.android.prototype.model.presentation.util;

import com.deliveryhero.android.prototype.model.presentation.Section;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.deliveryhero.android.prototype.data.util.RandomObjectGenerator.INSTANCE;

/**
 * Generates {@link Section} objects for consumption. This may be used by any prototype project to
 */
public final class SectionsGenerator {

  private static final Random R = new Random();

  private SectionsGenerator() {
  }

  public static List<Section> generateSections(int number) {
    List<String> sectionIds = INSTANCE.generateRandomStrings(number, number);
    List<Section> sections = new ArrayList<>(sectionIds.size());
    for (int i = 0; i < sectionIds.size(); i++) {
      String sectionId = "[SEC" + (i + 1) + "]";
      List<String> subsections = INSTANCE.generateRandomStrings(1, 5);
      String sectionName =
          sectionId + " " + sectionIds.get(i).substring(0, 8) + " (" + subsections.size() + ")";
      sections.add(generateSection(sectionId, sectionName, subsections, null));
    }
    return sections;
  }

  private static Section generateSection(String sectionId, String sectionName,
      List<String> subsections, List<String> items) {
    String description = "Description for the randomly-generated section: " + sectionId;
    Section section = new Section(sectionId, sectionName, description);
    if (items != null) section.items.addAll(items);
    if (subsections != null) {
      for (int i = 0; i < subsections.size(); i++) {
        int endIndex = R.nextInt(15) + 1;
        String subsectionId =
            sectionId + ":[" + (i + 1) + "] " + subsections.get(i).substring(0, endIndex);
        section.addSubsection(
            generateSection(subsectionId, subsectionId, null, INSTANCE.generateRandomStrings(15, 20)));
      }
    }
    return section;
  }
}