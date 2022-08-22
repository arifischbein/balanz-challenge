package com.example.balanzchallenge.core.utils

import java.text.Normalizer

val <T> T.exhaustive: T
    get() = this

// ------------------------------------------------------------------------------------------------

inline fun <T> MutableList<T>.mapInPlace(mutator: (T)->T) {
    val iterate = this.listIterator()
    while (iterate.hasNext()) {
        val oldValue = iterate.next()
        val newValue = mutator(oldValue)
        if (newValue !== oldValue) {
            iterate.set(newValue)
        }
    }
}

fun String.unaccent(): String {
    return Normalizer
        .normalize(this, Normalizer.Form.NFD)
        .replace("[^\\p{ASCII}]".toRegex(), "")
}