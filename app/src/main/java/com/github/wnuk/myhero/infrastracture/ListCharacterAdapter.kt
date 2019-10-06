package com.github.wnuk.myhero.infrastracture

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.infrastracture.dto.Character
import kotlinx.android.synthetic.main.characters_list_item.view.*

class ListCharacterAdapter (private var list: List<Character>) : RecyclerView.Adapter<ListCharacterAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.characters_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        fun bind(character: Character, position: Int){
            itemView.character_list_item__item.setOnClickListener { view ->
                view.findNavController().navigate(R.id.character_list_action)
            }
        }
    }
}