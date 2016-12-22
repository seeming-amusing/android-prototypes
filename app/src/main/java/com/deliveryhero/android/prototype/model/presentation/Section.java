package com.deliveryhero.android.prototype.model.presentation;

import java.util.ArrayList;
import java.util.List;

public class Section {

  public final String id;
  public final String name;
  public final String description;
  public final List<Section> subsections = new ArrayList<>();
  public final List<String> items = new ArrayList<>();

  public Section(String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.description = description;
  }

  public void addSubsection(Section section) {
    this.subsections.add(section);
  }

  @Override public String toString() {
    return name;
  }
}