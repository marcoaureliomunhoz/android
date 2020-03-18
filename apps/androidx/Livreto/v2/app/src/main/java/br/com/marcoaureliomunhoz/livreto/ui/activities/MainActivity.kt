package br.com.marcoaureliomunhoz.livreto.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import org.koin.android.viewmodel.ext.android.viewModel
import br.com.marcoaureliomunhoz.livreto.R
import br.com.marcoaureliomunhoz.livreto.ui.viewmodels.StateAppViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val controller by lazy {
        findNavController(R.id.nav_host_fragment)
    }

    private val viewModel: StateAppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        controller.addOnDestinationChangedListener(
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                Log.i("destination", destination.toString())
                Log.i("arguments", arguments.toString())

                /*when (destination.id) {
                    R.id.listBooksFragment -> supportActionBar?.show()
                    R.id.loginFragment -> supportActionBar?.hide()
                }*/

                viewModel.appVisualComponent.observe(this, Observer {
                    it?.let { hasVisualComponent ->

                        if (hasVisualComponent.appBar) {
                            supportActionBar?.show()
                        } else {
                            supportActionBar?.hide()
                        }

                        if (hasVisualComponent.bottomNavigation) {
                            main_activity_bottom_navigation.visibility = VISIBLE
                        } else {
                            main_activity_bottom_navigation.visibility = GONE
                        }
                    }
                })
        })

        main_activity_bottom_navigation.setupWithNavController(controller)
    }

}
