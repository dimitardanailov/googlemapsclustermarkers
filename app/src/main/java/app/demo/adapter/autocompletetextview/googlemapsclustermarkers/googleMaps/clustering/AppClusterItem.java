package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.googleMaps.clustering;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Dimitar Danailov on 6/3/15.
 * email: dimityr.danailov@gmail.com
 *
 * Documentation: https://developers.google.com/maps/documentation/android/utility/marker-clustering
 */
public class AppClusterItem implements ClusterItem {

    private final LatLng mPosition;

    public AppClusterItem(double latitude, double longitude) {
        mPosition = new LatLng(latitude, longitude);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
