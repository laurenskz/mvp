package nl.pocketquest.base

import nl.pocketquest.screen.UIRouter
import org.jetbrains.anko.toast

interface IPresenter : IContextProvider, UIRouter {

    fun onError(err: Throwable) {
        getContext()?.toast(err.message ?: "Unknown Error")
    }
}