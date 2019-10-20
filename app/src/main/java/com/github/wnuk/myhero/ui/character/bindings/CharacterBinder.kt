package com.github.wnuk.myhero.ui.character.bindings

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR

class CharacterBinder : BaseObservable() {

    @get:Bindable
    var name: String = ""
        set(value) {
            field = value
           notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var status: String = ""
        set(value) {
            field = value
                notifyPropertyChanged(BR.status)
        }

    @get:Bindable
    var species: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.species)
        }

    @get:Bindable
    var type: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.type)
        }

    @get:Bindable
    var gender: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.gender)
        }

    @get:Bindable
    var location: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.location)
        }

    @get:Bindable
    var origin: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.origin)
        }

}