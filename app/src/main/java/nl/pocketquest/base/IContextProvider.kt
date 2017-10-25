package nl.pocketquest.base

import android.content.Context

interface IContextProvider {

    fun getContext(): Context?
}