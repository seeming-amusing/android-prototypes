package com.deliveryhero.android.prototype.data.util

import com.deliveryhero.android.prototype.model.raw.RandomObject
import java.math.BigInteger
import java.security.SecureRandom
import java.util.*

/**
 * This is a factory class for [RandomObject]. This may also be used to help generate random
 * strings, which may be useful for other prototyping purposes.
 */
object RandomObjectGenerator {

    val RANDOM_ID_GENERATOR = SecureRandom()

    fun generateForId(id: String): RandomObject {
        val name = "Random $id"
        val bannerUrl = "http://example.com/$id.jpg"
        return RandomObject(id, name, bannerUrl, "Lorem ipsum", generateRandomStrings())
    }

    @JvmOverloads fun generateRandomStrings(min: Int = 4, max: Int = 10): List<String> {
        val count = when {
            min < max -> Random().nextInt(max - min) + min
            else -> max
        }
        return (0..count)
                .map { BigInteger(130, RANDOM_ID_GENERATOR).toString(24) }
                .toList()
    }
}