package com.projeto.calculadorapenal.models

import java.time.LocalDate

data class PenaData(

    val anos: Int,

    val meses: Int,

    val dias: Int,

    val regimeInicial: Regime,

    val tipoCrime: TipoCrime,

    val reincidente: Boolean,

    val violenciaGrave: Boolean,

    val dataInicio: LocalDate,

    val diasTrabalhados: Int,

    val horasEstudo: Int
)