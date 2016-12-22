package com.deliveryhero.android.prototype.model.raw;

import java.util.List;

public class RandomObject {

  public final String id;
  public final String name;
  public final String bannerUrl;
  public final String description;
  public final List<String> recommendations;

  public RandomObject(String id, String name, String bannerUrl, String description,
      List<String> recommendations) {
    this.id = id;
    this.name = name;
    this.bannerUrl = bannerUrl;
    this.description = description;
    this.recommendations = recommendations;
  }
}