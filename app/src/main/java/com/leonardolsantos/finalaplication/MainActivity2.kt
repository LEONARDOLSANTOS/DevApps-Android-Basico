package com.leonardolsantos.finalaplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.leonardolsantos.finalaplication.model.Personagem
import kotlinx.android.synthetic.main.activity_adiciona_personagem.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adiciona_personagem)
        trataButtonSalvar()
        trataButtonCancelar()
    }

    private fun trataButtonCancelar() {
     buttonCancelar.setOnClickListener {
         setResult(Activity.RESULT_CANCELED, intent)
         finish()
     }
    }

    private fun trataButtonSalvar() {
        buttonSalvar.setOnClickListener {
            val nome = editNome.text.toString()
            val idRadio = radioGroup.checkedRadioButtonId
            val valor = resources.getResourceEntryName(idRadio)
            var avatar = -1
            if (valor=="rbAang"){
                avatar = 0
            }else if(valor=="rbAppa"){
                avatar = 1
            }else if(valor=="rbKatara"){
                avatar = 2
            }else if(valor=="rbSokka"){
                avatar = 3
            }else {
                avatar = 4
            }

            val idade = editIdade.text.toString().toInt();
            intent.putExtra("nome", nome )
            intent.putExtra("avatar", avatar )
            intent.putExtra("idade", idade )
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }


}