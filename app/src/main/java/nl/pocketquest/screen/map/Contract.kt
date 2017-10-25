package nl.pocketquest.screen.map

import com.mapbox.mapboxsdk.annotations.Marker
import com.mapbox.mapboxsdk.geometry.LatLng
import nl.pocketquest.base.IPresenter
import nl.pocketquest.base.IView

interface Contract {

    interface View : IView {
        fun setLocationDisplayedAtCenterOfScreen(location: LatLng)

    }

    interface Presenter : IPresenter {
//        fun onMarkerClicked(marker: Marker)
    }
}