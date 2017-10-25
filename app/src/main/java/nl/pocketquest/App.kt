package nl.pocketquest

import android.app.Application
import nl.pocketquest.data.DataLayer

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        DataLayer.init(this)
    }

}
