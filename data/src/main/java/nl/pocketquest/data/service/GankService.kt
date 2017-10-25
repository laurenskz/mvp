package nl.pocketquest.data.service

import nl.pocketquest.data.DO.Meizi
import nl.pocketquest.data.exception.GankServiceException
import nl.pocketquest.data.api.GankApi
import io.paperdb.Paper
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

object GankService {

    fun getMeizis(count: Int, pageNum: Int): Observable<ArrayList<Meizi>> =
            GankApi.IMPL.getMeizis(count, pageNum)
                    .subscribeOn(Schedulers.io())
                    .map {
                        Paper.book().write("meizis-$pageNum", it.results)
                        it.results
                    }
                    .onErrorResumeNext { err: Throwable ->
                        val list: ArrayList<Meizi> = Paper.book().read("meizis-$pageNum")
                                ?: throw GankServiceException(err.message)
                        Observable.just(list)
                    }

}