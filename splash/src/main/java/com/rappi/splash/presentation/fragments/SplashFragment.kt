package com.rappi.splash.presentation.fragments

import android.os.Bundle
import android.view.View
import com.rappi.core.fragments.BaseFragment
import com.rappi.splash.databinding.FragmentSplashBinding

class SplashFragment: BaseFragment<FragmentSplashBinding>() {
//    private val viewModel: SplashViewModel by activityViewModels()

    override fun initBinding(): FragmentSplashBinding =
        FragmentSplashBinding.inflate(layoutInflater)

    override fun initView(view: View, savedInstanceState: Bundle?) {
//        downloadConfigurations()
    }
/*    private fun downloadConfigurations() {
        viewModel.downloadConfigurations.observe(viewLifecycleOwner) {
            if(it) {
                navigate()
            } else {
                Toast.makeText(requireContext(), getString(R.string.sync_error), Toast.LENGTH_LONG).show()
                requireActivity().finish()
            }
        }
/*        lifecycleScope.collectFlow(viewModel.downloadConfigurations) {
            if(it) {
                delay(1000)
                navigate()
                delay(3000)
                bind.pbDownloading.visible = true
            } else {
                Toast.makeText(requireContext(), getString(R.string.sync_error), Toast.LENGTH_LONG).show()
                delay(1000)
                requireActivity().finish()
            }
        }*/
        viewModel.downloadData()
    }

    private fun navigate(){
        val extras = FragmentNavigatorExtras(bind.ivLogo to "logo")

        findNavController().navigate(
            R.id.action_splashFragment_to_moviesHomeFragment,
            null,
            null,
            extras
        )
    }*/
}