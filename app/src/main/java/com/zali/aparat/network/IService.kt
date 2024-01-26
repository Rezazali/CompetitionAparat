package com.zali.aparat.network


import com.zali.aparat.domain.models.BaseMovie
import com.zali.aparat.domain.models.MovieCollection
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface IService {

    @GET("api/fa/v1/video/video/list/tagid/1")
    fun getAllVideo() : Observable<MovieCollection>


    @GET("api/fa/v1/video/video/show/videohash/{videoHash}")
    fun getVideo(@Path("videoHash") videoHash: String): Observable<BaseMovie>
}