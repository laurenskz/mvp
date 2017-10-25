package nl.pocketquest.screen.page2

import nl.pocketquest.base.IPresenter
import nl.pocketquest.base.IView

interface Contract {

    interface View : IView {
        fun setMeizi(vo: MeiziVO)
    }

    interface Presenter: IPresenter {
        fun onImageClick()
    }
}