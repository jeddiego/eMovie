package com.rappi.emovie.splash.presentation.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.rappi.core.commons.visible
import com.rappi.core.fragments.BaseFragment
import com.rappi.emovie.R
import com.rappi.emovie.databinding.FragmentSplashBinding
import com.rappi.emovie.splash.presentation.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val viewModel: SplashViewModel by activityViewModels()

    override fun initBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
        downloadConfigurations()
        lifecycleScope.launchWhenStarted {
            delay(3000)
            bind.pbDownloading.visible = true
        }
    }

    private fun downloadConfigurations() {
        viewModel.downloadConfigurations.observe(viewLifecycleOwner) {
            if (it) {
                Handler(Looper.getMainLooper()).postDelayed({
                    navigate()
                }, 700)
            } else {
                showToast(getString(R.string.sync_error))
                Handler(Looper.getMainLooper()).postDelayed({
                    requireActivity().finish()
                }, 2000)
            }
        }
        viewModel.downloadConfigurations()
    }

    private fun navigate() {
        val extras = FragmentNavigatorExtras(bind.ivLogo to "logo")

        findNavController().navigate(
            R.id.action_splashFragment_to_moviesHomeFragment,
            null,
            null,
            extras
        )
    }
}