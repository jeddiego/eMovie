package com.rappi.splash.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.activityViewModels
import com.rappi.core.commons.visible
import com.rappi.core.fragments.BaseFragment
import com.rappi.splash.R
import com.rappi.splash.databinding.FragmentSplashBinding
import com.rappi.splash.presentation.viewmodel.SplashViewModel

class SplashFragment: BaseFragment<FragmentSplashBinding>() {
    private val viewModel: SplashViewModel by activityViewModels()

    override fun initBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        downloadConfigurations()
        Handler(Looper.getMainLooper()).postDelayed({
            bind.pbDownloading.visible = true
        }, 3000)
        showToast("¡Todo bien!")
    }

    private fun downloadConfigurations() {
        viewModel.downloadConfigurations.observe(viewLifecycleOwner) {
            if(it) {
                bind.pbDownloading.visible = true
            } else {
                showToast(getString(R.string.sync_error))
                Handler(Looper.getMainLooper()).postDelayed({
                   requireActivity().finish()
                }, 4200)
            }
        }
        viewModel.downloadConfigurations()
    }

/*    private fun navigate(){
        val extras = FragmentNavigatorExtras(bind.ivLogo to "logo")

        findNavController().navigate(
            R.id.action_splashFragment_to_moviesHomeFragment,
            null,
            null,
            extras
        )
    }*/
}