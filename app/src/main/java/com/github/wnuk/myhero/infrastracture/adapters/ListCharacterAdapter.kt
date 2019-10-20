package com.github.wnuk.myhero.infrastracture.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.github.wnuk.myhero.BR
import com.github.wnuk.myhero.R
import com.github.wnuk.myhero.model.character.Character
import com.github.wnuk.myhero.ui.character.CharacterItemViewModel
import kotlinx.android.synthetic.main.characters_list_item.view.*

class ListCharacterAdapter (private val list: ArrayList<Character>) : RecyclerView.Adapter<ListCharacterAdapter.DataBindingViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return DataBindingViewHolder(binding)
    }

    fun addData(listItems: ArrayList<Character>) {
        var size = list.size
        list.addAll(listItems)
        var sizeNew =  listItems.size
        notifyItemRangeChanged(size, sizeNew)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.bind(list[position], position)
    }

    override fun getItemViewType(position: Int) = R.layout.characters_list_item

    class DataBindingViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(character: Character, position: Int){
            binding.setVariable(BR.model, CharacterItemViewModel(character))
            itemView.character_list_item__item.setOnClickListener { view ->
                var bundle = bundleOf("idCharacter" to character.id)
                Log.d("ListCharacterAdapter", "Argument: ${position} i ${character.id}")

                view.findNavController().navigate(R.id.character_list_action, bundle)
            }
        }
    }
}