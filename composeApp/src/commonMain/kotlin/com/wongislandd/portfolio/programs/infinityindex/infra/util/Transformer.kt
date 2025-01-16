package com.wongislandd.portfolio.programs.infinityindex.infra.util

interface Transformer<I, O> {
    fun transform(input: I): O?
}