package com.zali.aparat.presentation.ui.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val isDownloadCanceled: MutableLiveData<Boolean> = MutableLiveData(false)
}
