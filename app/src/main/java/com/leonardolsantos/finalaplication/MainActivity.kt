package com.leonardolsantos.finalaplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.leonardolsantos.finalaplication.adapter.PersonagemAdapter
import com.leonardolsantos.finalaplication.model.Personagem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val arrayPersonagens =  mutableListOf(
        Personagem("Aang",0,  108)
    )

    private val mPersonagemAdapter = PersonagemAdapter(this,arrayPersonagens, this::onPersonagemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerview()
        setupInsertButton()
        floatingActionButtonAdiciona.setOnClickListener {
            val telaCadastro = Intent(this, MainActivity2::class.java)
            startActivity(telaCadastro)
        }
    }

    private fun setupRecyclerview() {
        recyclerView.adapter = mPersonagemAdapter
        val layoutManager = GridLayoutManager(this, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return 2
            }
        }
        recyclerView.layoutManager = layoutManager
    }


    private fun setupInsertButton() {
        button.setOnClickListener {
            val name = editTextTextPersonName.text.toString()
            arrayPersonagens.add(0,Personagem(name, (0..4).random(),0))
            mPersonagemAdapter.notifyItemInserted(0)
            editTextTextPersonName.text.clear()
            editTextTextPersonName.clearFocus()
        }
    }

    private fun onPersonagemClickListener(personagem: Personagem) {
        Toast.makeText(this, "Personagem: ${personagem.nome} Avatar: ${personagem.avatar}",
            Toast.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Toast.makeText(this, "Retornou", Toast.LENGTH_SHORT).show()
    }

}