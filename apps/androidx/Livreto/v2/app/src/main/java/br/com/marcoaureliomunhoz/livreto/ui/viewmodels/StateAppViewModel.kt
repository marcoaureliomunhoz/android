package br.com.marcoaureliomunhoz.livreto.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateAppViewModel : ViewModel() {

    val appVisualComponent: LiveData<VisualComponent> get() = _appVisualComponent

    private var _appVisualComponent: MutableLiveData<VisualComponent> =
        MutableLiveData<VisualComponent>().also {
            it.value = hasAppVisualComponent
        }

    var hasAppVisualComponent: VisualComponent = VisualComponent()
        set(value) {
            field = value
            _appVisualComponent.value = value
        }



}

class VisualComponent (
    val appBar: Boolean = false,
    val bottomNavigation: Boolean = false
)