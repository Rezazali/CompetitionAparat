package com.zali.aparat.presentation.ui.aplash

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.zali.aparat.R
import com.zali.aparat.databinding.FragmentSplashBinding
import com.zali.aparat.network.NetworkChangeReceiver
import com.zali.aparat.network.NetworkConnectivityListener
import es.dmoral.toasty.Toasty


class SplashFragment : Fragment() , NetworkConnectivityListener {

    private lateinit var binding : FragmentSplashBinding

    private lateinit var networkChangeReceiver : NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(layoutInflater)

        return binding.root
    }


    private fun NavController.safeNavigate(direction: NavDirections) {
        currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
    }

    override fun onResume() {
        super.onResume()
        updateConnectivity()
    }


    private fun updateConnectivity(){
        networkChangeReceiver = NetworkChangeReceiver(this)
        val intentFilterNetwork = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        requireActivity().registerReceiver(networkChangeReceiver, intentFilterNetwork)
    }

    override fun onNetworkConnectivityChanged(isConnected: Boolean) {
        if (!isAdded) {
            // Fragment is no longer attached, do not proceed
            return
        }

        if (isConnected){

            binding.lottie.animate().setDuration(2000).startDelay = 2900


            Handler(Looper.getMainLooper()).postDelayed({
                findNavController().safeNavigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment("fg"))
            }, 3000)

        }else{
            Toasty.warning(requireContext(), "لطفا اینترنت خود را روشن کنید ", Toast.LENGTH_SHORT, true).show();
        }
    }


}