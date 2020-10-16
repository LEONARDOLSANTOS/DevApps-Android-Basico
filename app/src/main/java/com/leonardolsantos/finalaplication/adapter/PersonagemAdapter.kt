package com.leonardolsantos.finalaplication.adapter

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.leonardolsantos.finalaplication.R
import com.leonardolsantos.finalaplication.model.Personagem
import kotlinx.android.synthetic.main.item_personagem.view.*


class PersonagemAdapter (private val context: Context,
                    private val personagens: List<Personagem>,
                    private val callback: (Personagem) -> Unit) : RecyclerView.Adapter<PersonagemAdapter.VH>() {

    private val avatares: TypedArray by lazy {
        context.resources.obtainTypedArray(R.array.personagens)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_personagem
            , parent, false)
        val vh = VH(v)
        vh.itemView.setOnClickListener {
            val personagem = personagens[vh.adapterPosition]
            callback(personagem)
        }
        return vh
    }

    override fun getItemCount() = personagens.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val (nome, avatar) = personagens[position]
        holder.imgAvatar.setImageDrawable(avatares.getDrawable(avatar))
        holder.txtName.text = nome
    }

    class VH(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgAvatar : ImageView = itemView.imageView
        val txtName: TextView = itemView.textView
    }
}
