package com.teewhydope.app.common.mapper

abstract class PresentationToUiMapper<INPUT : Any, OUTPUT : Any> {

    fun toUi(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw UiMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}

abstract class UiToPresentationMapper<INPUT : Any, OUTPUT : Any> {

    fun toPresentation(input: INPUT): OUTPUT = try {
        map(input)
    } catch (throwable: Throwable) {
        throw PresentationMapperException("Could not map ${input::class.simpleName}", throwable)
    }

    protected abstract fun map(input: INPUT): OUTPUT
}