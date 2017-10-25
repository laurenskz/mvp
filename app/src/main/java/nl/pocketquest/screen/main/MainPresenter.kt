package nl.pocketquest.screen.main

import android.os.Bundle
import cn.nekocode.itempool.Item
import cn.nekocode.itempool.ItemPool
import nl.pocketquest.base.BasePresenter
import nl.pocketquest.data.DO.Meizi
import nl.pocketquest.data.service.GankService
import nl.pocketquest.item.MeiziItem
import com.github.yamamotoj.pikkel.Pikkel
import com.github.yamamotoj.pikkel.PikkelDelegate
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.rxkotlin.zipWith
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList

class MainPresenter : BasePresenter<Contract.View>(), Contract.Presenter, Pikkel by PikkelDelegate() {
    var list by state<ArrayList<Meizi>?>(null)
    var itemPool = ItemPool()
    var viewBehavior = BehaviorProcessor.create<Contract.View>()!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restoreInstanceState(savedInstanceState)

        itemPool.addType(MeiziItem::class.java)
        itemPool.onEvent(MeiziItem::class.java) { event ->
            val meizi = (event.data as MeiziItem.ViewObject).DO as Meizi
            when (event.action) {
                Item.EVENT_ITEM_CLICK -> {
                    gotoPage2(context, meizi)
                }
            }
        }

        if (list == null) {
            GankService.getMeizis(50, 1)
        } else {
            Observable.just(list ?: return)
        }
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map { meizis ->
                    list = meizis
                    meizis.map { MeiziItem.ViewObject.fromMeizi(it) }
                }
                .zipWith(viewBehavior.toObservable()) { voList: List<MeiziItem.ViewObject>, view: Contract.View ->
                    Pair(voList, view)
                }
                .bindToLifecycle(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ (voList, view) ->
                    itemPool.clear()
                    itemPool.addAll(voList)
                    view.setAdapter(itemPool.adapter)
                }, this::onError)
    }

    override fun onViewCreated(view: Contract.View, savedInstanceState: Bundle?) {
        viewBehavior.onNext(view)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        saveInstanceState(outState ?: return)
    }
}