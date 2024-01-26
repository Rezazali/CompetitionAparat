package com.zali.aparat.presentation.ui.moviedetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zali.aparat.domain.models.BaseMovie
import com.zali.aparat.domain.models.MovieCollection
import com.zali.aparat.network.ApiClient
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel : ViewModel(){

    private val TAG = "MovieDetailViewModel"

    lateinit var mutableAllVideo: MutableLiveData<BaseMovie>

    fun getAllVideo(videoHash : String): MutableLiveData<BaseMovie> {
        if (!::mutableAllVideo.isInitialized) {
            mutableAllVideo = MutableLiveData()
            homeWebService(videoHash)
        }
        return mutableAllVideo
    }

    private fun homeWebService(videoHash : String) {
        ApiClient.getMovieApi().getVideo(videoHash).subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseMovie> {
                override fun onSubscribe(d: Disposable) {}

                override fun onNext(t: BaseMovie) {
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
