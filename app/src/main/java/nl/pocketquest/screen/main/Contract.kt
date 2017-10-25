package nl.pocketquest.screen.main

import android.support.v7.widget.RecyclerView
import nl.pocketquest.base.IPresenter
import nl.pocketquest.base.IView

interface Contract {

    interface View : IView {
        fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>)
    }

    interface Presenter: IPresenter {
    }
}