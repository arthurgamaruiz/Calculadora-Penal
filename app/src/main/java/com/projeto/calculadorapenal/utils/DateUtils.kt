package com.projeto.calculadorapenal.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUtils {

    private val formatter =
        DateTimeFormatter.ofPattern("dd/MM/yyyy")

    fun formatarData(
        data: LocalDate
    ): String {

        return data.format(formatter)
    }

    fun converterData(
        texto: String
    ): LocalDate {

        return LocalDate.parse(
            texto,
            formatter
        )
    }
}