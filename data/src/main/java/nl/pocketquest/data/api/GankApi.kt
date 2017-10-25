package nl.pocketquest.data.api

import nl.pocketquest.data.DO.Meizi
import nl.pocketquest.data.DO.Response
import nl.pocketquest.data.DataLayer
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GankApi {

    companion object {
        val IMPL: GankApi = DataLayer.RETROFIT_GANK!!.create(GankApi::class.java)
    }

    @GET("{count}/{pageNum}")
    fun getMeizis(@Path("count") count: Int, @Path("pageNum") pageNum: Int): Observable<Response<Meizi>>
}