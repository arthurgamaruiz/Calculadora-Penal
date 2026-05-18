package com.projeto.calculadorapenal.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projeto.calculadorapenal.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding =
            ActivityContactBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnWhatsapp.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(
                    "https://wa.me/5511999999999"
                )
            )

            startActivity(intent)
        }
    }
}