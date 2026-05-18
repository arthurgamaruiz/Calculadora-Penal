package com.projeto.calculadorapenal.service

import com.projeto.calculadorapenal.models.*
import java.time.LocalDate

class CalculadoraPenalService {

    fun calcular(
        dados: PenaData
    ): ResultadoCalculo {

        val dataTerminoOriginal =

            dados.dataInicio
                .plusYears(dados.anos.toLong())
                .plusMonths(dados.meses.toLong())
                .plusDays(dados.dias.toLong())

        val diasTotaisPena =

            java.time.temporal.ChronoUnit.DAYS
                .between(
                    dados.dataInicio,
                    dataTerminoOriginal
                ).toInt()

        val diasRemidosTrabalho =

            if (dados.regimeInicial == Regime.ABERTO)
                0
            else
                dados.diasTrabalhados / 3

        val diasRemidosEstudo =
            dados.horasEstudo / 12

        val diasRemidos =
            diasRemidosTrabalho +
                    diasRemidosEstudo

        val terminoPena =

            dataTerminoOriginal
                .minusDays(diasRemidos.toLong())

        val fracaoProgressao =
            obterFracaoProgressao(dados)

        val percentualProgressao =
            fracaoProgressao * 100

        val diasProgressao =

            (diasTotaisPena *
                    fracaoProgressao).toLong()

        val dataSemiaberto =

            calcularDataSemiaberto(
                dados,
                diasProgressao
            )

        val dataAberto =

            calcularDataAberto(
                dados,
                diasProgressao
            )

        val possuiLivramento =
            possuiLivramentoCondicional(dados)

        val dataLivramento =

            if (possuiLivramento) {

                val fracaoLivramento =
                    obterFracaoLivramento(dados)

                val diasLivramento =

                    (diasTotaisPena *
                            fracaoLivramento).toLong()

                dados.dataInicio.plusDays(
                    diasLivramento
                )

            } else {

                null
            }

        val percentualLivramento =

            if (possuiLivramento)
                obterFracaoLivramento(dados) * 100
            else
                0.0

        return ResultadoCalculo(

            penaTotalDias = diasTotaisPena,

            diasRemidos = diasRemidos,

            dataSemiaberto = dataSemiaberto,

            dataAberto = dataAberto,

            dataLivramento = dataLivramento,

            terminoPena = terminoPena,

            percentualProgressao =
                percentualProgressao,

            percentualLivramento =
                percentualLivramento,

            possuiLivramento =
                possuiLivramento
        )
    }

    private fun calcularDataSemiaberto(
        dados: PenaData,
        dias: Long
    ): LocalDate {

        return when (dados.regimeInicial) {

            Regime.FECHADO -> {

                dados.dataInicio.plusDays(dias)
            }

            Regime.SEMIABERTO -> {

                dados.dataInicio
            }

            Regime.ABERTO -> {

                dados.dataInicio
            }
        }
    }

    private fun calcularDataAberto(
        dados: PenaData,
        dias: Long
    ): LocalDate {

        return when (dados.regimeInicial) {

            Regime.FECHADO -> {

                dados.dataInicio.plusDays(
                    dias * 2
                )
            }

            Regime.SEMIABERTO -> {

                dados.dataInicio.plusDays(
                    dias
                )
            }

            Regime.ABERTO -> {

                dados.dataInicio
            }
        }
    }

    private fun obterFracaoProgressao(
        dados: PenaData
    ): Double {

        return when (dados.tipoCrime) {

            TipoCrime.COMUM -> {

                if (dados.reincidente)
                    1.0 / 5.0
                else
                    1.0 / 6.0
            }

            TipoCrime.HEDIONDO -> {

                if (dados.reincidente)
                    3.0 / 5.0
                else
                    2.0 / 5.0
            }

            TipoCrime.HEDIONDO_RESULTADO_MORTE -> {

                7.0 / 10.0
            }

            TipoCrime.ORGANIZACAO_CRIMINOSA -> {

                1.0 / 2.0
            }

            TipoCrime.TRAFICO_DROGAS -> {

                if (dados.reincidente)
                    3.0 / 5.0
                else
                    2.0 / 5.0
            }
        }
    }

    private fun obterFracaoLivramento(
        dados: PenaData
    ): Double {

        return when (dados.tipoCrime) {

            TipoCrime.COMUM -> {

                if (dados.reincidente)
                    1.0 / 2.0
                else
                    1.0 / 3.0
            }

            else -> {

                2.0 / 3.0
            }
        }
    }

    private fun possuiLivramentoCondicional(
        dados: PenaData
    ): Boolean {

        return !(
                dados.reincidente &&
                        dados.tipoCrime != TipoCrime.COMUM
                )
    }
}