package com.teewhydope.app.data.common.mapper

fun <INPUT : Any, OUTPUT : Any> INPUT.toData(mapper: ApiToDataMapper<INPUT, OUTPUT>) =
    mapper.toData(this)