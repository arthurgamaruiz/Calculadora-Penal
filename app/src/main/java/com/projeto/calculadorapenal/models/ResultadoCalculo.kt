package com.projeto.calculadorapenal.models

import java.time.LocalDate

data class ResultadoCalculo(

    val penaTotalDias: Int,

    val diasRemidos: Int,

    val dataSemiaberto: LocalDate,

    val dataAberto: LocalDate,

    val dataLivramento: LocalDate?,

    val terminoPena: LocalDate,

    val percentualProgressao: Double,

    val percentualLivramento: Double,

    val possuiLivramento: Boolean
)