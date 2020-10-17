package com.leonardolsantos.finalaplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.leonardolsantos.finalaplication.adapter.PersonagemAdapter
import com.leonardolsantos.finalaplication.model.Personagem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        const val NOVO_PERSONAGEM = 1
        const val ALTERAR_PERSONAGEM = 2
    }
    private val arrayPersonagens =  mutableListOf(
        Personagem("Aang",0,  108)
    )

    private val mPersonagemAdapter = PersonagemAdapter(this,arrayPersonagens, this::onPersonagemClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerview()
        floatingActionButtonAdiciona.setOnClickListener {
            val telaCadastro = Intent(this, MainActivity2::class.java)
            startActivityForResult(telaCadastro, NOVO_PERSONAGEM)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var nome = data?.getStringExtra("nome")
        var avatar = data?.getIntExtra("avatar",0)
        var idade = data?.getIntExtra("idade",0)
        if(requestCode == NOVO_PERSONAGEM && resultCode == Activity.RESULT_OK){
            if(nome!= null && avatar!=null && idade!=null){
                arrayPersonagens.add(0,Personagem(nome, avatar,idade))
                mPersonagemAdapter.notifyItemInserted(0)
            }
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

    private fun onPersonagemClickListener(personagem: Personagem) {
        Toast.makeText(this, "Personagem: ${personagem.nome} Avatar: ${personagem.avatar}",
            Toast.LENGTH_SHORT).show()
    }



}