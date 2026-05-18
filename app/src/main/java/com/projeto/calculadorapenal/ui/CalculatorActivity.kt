package com.projeto.calculadorapenal.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projeto.calculadorapenal.databinding.ActivityCalculatorBinding
import com.projeto.calculadorapenal.models.*
import com.projeto.calculadorapenal.service.CalculadoraPenalService
import java.time.LocalDate
import android.widget.ArrayAdapter
import com.projeto.calculadorapenal.utils.DateUtils

class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding:
            ActivityCalculatorBinding

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)

        binding =
            ActivityCalculatorBinding.inflate(
                layoutInflater
            )

        setContentView(binding.root)

        supportActionBar
            ?.setDisplayHomeAsUpEnabled(true)

        configurarSpinners()

        configurarBotoes()
    }

    private fun configurarSpinners() {

        val regimes = listOf(

            "Fechado",

            "Semiaberto",

            "Aberto"
        )

        binding.spRegime.adapter = ArrayAdapter(

            this,

            android.R.layout
                .simple_spinner_dropdown_item,

            regimes
        )

        val crimes = listOf(

            "Comum",

            "Hediondo",

            "Hediondo com Resultado Morte",

            "Organização Criminosa",

            "Tráfico de Drogas"
        )

        binding.spCrime.adapter = ArrayAdapter(

            this,

            android.R.layout
                .simple_spinner_dropdown_item,

            crimes
        )
    }

    private fun configurarBotoes() {

        binding.btnCalcular.setOnClickListener {

            calcularPena()
        }

        binding.btnContato.setOnClickListener {

            startActivity(

                Intent(
                    this,
                    ContactActivity::class.java
                )
            )
        }
    }

    private fun calcularPena() {

        try {

            val anos =
                binding.etAnos.text.toString().toInt()

            val meses =
                binding.etMeses.text.toString().toInt()

            val dias =
                binding.etDias.text.toString().toInt()

            val diasTrabalhados =

                if (
                    binding.spRegime.selectedItemPosition == 2
                ) {
                    0
                } else {

                    binding.etDiasTrabalhados
                        .text.toString()
                        .ifEmpty { "0" }
                        .toInt()
                }

            val horasEstudo =

                binding.etHorasEstudo
                    .text.toString()
                    .ifEmpty { "0" }
                    .toInt()

            val dataInicio =

                DateUtils.converterData(

                    binding.etDataInicio
                        .text.toString()
                )

            val regime = when (

                binding.spRegime
                    .selectedItemPosition

            ) {

                0 -> Regime.FECHADO

                1 -> Regime.SEMIABERTO

                else -> Regime.ABERTO
            }

            val crime = when (

                binding.spCrime
                    .selectedItemPosition

            ) {

                0 -> TipoCrime.COMUM

                1 -> TipoCrime.HEDIONDO

                2 -> TipoCrime.HEDIONDO_RESULTADO_MORTE

                3 -> TipoCrime.ORGANIZACAO_CRIMINOSA

                else -> TipoCrime.TRAFICO_DROGAS
            }

            val dados = PenaData(

                anos = anos,

                meses = meses,

                dias = dias,

                regimeInicial = regime,

                tipoCrime = crime,

                reincidente =
                    binding.cbReincidente.isChecked,

                violenciaGrave =
                    binding.cbViolencia.isChecked,

                dataInicio = dataInicio,

                diasTrabalhados =
                    diasTrabalhados,

                horasEstudo =
                    horasEstudo
            )

            val resultado =

                CalculadoraPenalService()
                    .calcular(dados)

            val textoLivramento =

                if (resultado.possuiLivramento) {

                    """

────────────────────

Liberdade Condicional

Data prevista:
${DateUtils.formatarData(resultado.dataLivramento!!)}

(${String.format("%.1f", resultado.percentualLivramento)}% da pena)

                """.trimIndent()

                } else {

                    """

────────────────────

Liberdade Condicional

Não possui direito devido à reincidência
em crime equiparado a hediondo.

                """.trimIndent()
                }

            binding.tvResultado.text = """

Término da Pena sem Progressões e Remições

Data prevista:
${DateUtils.formatarData(resultado.terminoPena)}

(100.0% da pena)

────────────────────

Progressão para Regime Semiaberto

Data prevista:
${DateUtils.formatarData(resultado.dataSemiaberto)}

(${String.format("%.1f", resultado.percentualProgressao)}% da pena)

────────────────────

Progressão para Regime Aberto

Data prevista:
${DateUtils.formatarData(resultado.dataAberto)}

(${String.format("%.1f", resultado.percentualProgressao)}% da pena)

$textoLivramento

────────────────────

Dias Remidos:
${resultado.diasRemidos}

        """.trimIndent()

        } catch (e: Exception) {

            e.printStackTrace()

            binding.tvResultado.text = """

Erro no preenchimento:

${e.message}

        """.trimIndent()
        }
    }

    override fun onSupportNavigateUp():
            Boolean {

        finish()

        return true
    }
}