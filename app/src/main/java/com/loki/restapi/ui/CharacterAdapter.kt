package com.loki.restapi.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.loki.restapi.data.response.Character
import com.loki.restapi.databinding.CharacterItemLayoutBinding

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {


    private var characters = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CharacterItemLayoutBinding.inflate(inflater, parent ,false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {

        val character = characters[position]
        holder.bind(character)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, CharacterDetailActivity::class.java)
            intent.putExtra("id", character.id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = characters.size

    fun setCharacterList(character: List<Character>) {
        this.characters = character.toMutableList()
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(val binding: CharacterItemLayoutBinding): ViewHolder(binding.root) {

        fun bind(character: Character) {
            binding.apply {
                Glide.with(binding.root).load(character.image).into(ivCharacterImg)
                tvCharacterName.text = character.name
                tvCharacterGender.text = character.gender
                tvCharacterHouse.text  = character.house
            }
        }
    }
}