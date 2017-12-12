package org.diedrik.uhunt.utils

import org.diedrik.uhunt.model.Problem

/**
 * Created by jquispe on 11/12/2017.
 */
class ProblemCache private constructor() {
    private val problemCacheMap: MutableMap<Int, Problem> = mutableMapOf()

    companion object {
        private val myInstance: ProblemCache = ProblemCache()

        @Synchronized
        fun getInstance(): ProblemCache {
            return myInstance
        }
    }
}