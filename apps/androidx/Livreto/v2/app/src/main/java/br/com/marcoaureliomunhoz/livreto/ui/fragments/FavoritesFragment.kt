package br.com.marcoaureliomunhoz.livreto.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.marcoaureliomunhoz.livreto.R
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.StateAppViewModel
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.VisualComponent
import org.koin.android.viewmodel.ext.android.sharedViewModel

class FavoritesFragment : BaseFragment() {

    private val stateAppViewModel : StateAppViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        stateAppViewModel.hasAppVisualComponent = VisualComponent(true, true)

        activity?.title = getString(R.string.favorites)
    }

}
