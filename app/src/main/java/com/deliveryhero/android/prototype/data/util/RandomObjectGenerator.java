package com.deliveryhero.android.prototype.data.util;

import com.deliveryhero.android.prototype.model.raw.RandomObject;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This is a factory class for {@link RandomObject}. This may also be used to help generate random
 * strings, which may be useful for other prototyping purposes.
 */
public final class RandomObjectGenerator {

  private static final SecureRandom RANDOM_ID_GENERATOR = new SecureRandom();

  public static RandomObject generateForId(String id) {
    String name = "Random " + id;
    String bannerUrl = "http://example.com/" + id + ".jpg";
    return new RandomObject(id, name, bannerUrl, "Lorem ipsum", generateRandomStrings());
  }

  public static List<String> generateRandomStrings() {
    return generateRandomStrings(4, 10);
  }

  public static List<String> generateRandomStrings(int min, int max) {
    int count;
    if (min < max) {
      count = new Random().nextInt(max - min) + min;
    } else {
      count = max;
    }
    String[] recommendations = new String[count];
    for (int i = count; i-- > 0; ) {
      recommendations[i] = new BigInteger(130, RANDOM_ID_GENERATOR).toString(24);
    }
    return Arrays.asList(recommendations);
  }
}