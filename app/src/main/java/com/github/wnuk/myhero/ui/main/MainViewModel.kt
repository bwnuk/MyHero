package com.github.wnuk.myhero.ui.main

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.github.wnuk.myhero.R

class MainViewModel : ViewModel() {
    fun onCharacterClick(view: View){
        view.findNavController().navigate(R.id.character_action)
    }
}
