package com.zali.aparat.presentation.ui.main.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zali.aparat.domain.models.MovieCollection
import com.zali.aparat.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel : ViewModel() {

    private val TAG = "HomeViewModel"

    lateinit var mutableAllVideo: MutableLiveData<MovieCollection>

    fun getAllVideo(): MutableLiveData<MovieCollection> {
        if (!::mutableAllVideo.isInitialized) {
            mutableAllVideo = MutableLiveData()
            homeWebService()
        }
        return mutableAllVideo
    }

    private fun homeWebService() {
        ApiClient.getMovieApi().getAllVideo().subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MovieCollection> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: MovieCollection) {
                    mutableAllVideo.value = t
                    Log.d(TAG, "onNext: ")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError: ", e)
                }

                override fun onComplete() {}
            })
    }

}
