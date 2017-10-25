package nl.pocketquest.screen

import android.content.Context
import cn.nekocode.meepo.annotation.Bundle
import cn.nekocode.meepo.annotation.TargetClass
import cn.nekocode.meepo.Meepo
import nl.pocketquest.data.DO.Meizi
import nl.pocketquest.screen.page2.Page2Activity
import nl.pocketquest.screen.page2.Page2Presenter

interface UIRouter {

    companion object {
        val IMPL = Meepo.Builder().build().create(UIRouter::class.java)
    }

    @TargetClass(Page2Activity::class)
    fun gotoPage2(context: Context?, @Bundle(Page2Presenter.ARG_MEIZI) meizi: Meizi) {
        IMPL.gotoPage2(context, meizi)
    }
}