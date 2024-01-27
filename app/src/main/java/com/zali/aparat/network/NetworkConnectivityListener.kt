package com.zali.aparat.network

interface NetworkConnectivityListener {
    fun onNetworkConnectivityChanged(isConnected: Boolean)
}
