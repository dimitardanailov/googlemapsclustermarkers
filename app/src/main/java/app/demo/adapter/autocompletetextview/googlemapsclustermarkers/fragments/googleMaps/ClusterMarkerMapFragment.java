package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments.googleMaps;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.R;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments.BaseFragment;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments.googleMaps.widgets.CustomGoogleMapFragment;

/**
 * Created by Dimitar Danailov on 6/2/15.
 * email: dimityr.danailov@gmail.com
 */
public class ClusterMarkerMapFragment extends BaseFragment implements OnMapReadyCallback {

    // Get class name
    private static final String TAG = ClusterMarkerMapFragment.class.getName();

    // Instance of our Google Map
    private GoogleMap googleMap;

    // Google Map Zoom default zoom level
    private static final int DEFAULT_ZOOM_LEVEL = 4;

    /**
     * User should to have large zoom value to get values from server.
     * User will get information after zoom level >= {MINIMUM_ZOOM_LEVEL_SERVER_REQUEST}
     */
    private static final int MINIMUM_ZOOM_LEVEL_SERVER_REQUEST = 7;

    // Initialize to a non-valid zoom value
    private float previousZoomLevel = -1.0f;

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     * <p/>
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setActivity();

        Log.d(TAG, "Map is loaded");

        View view = inflater.inflate(R.layout.map_fragment, container, false);

        loadGoogleMapFragment();

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        setupMap(googleMap);
    }

    /**
     * Try to load {@Link CustomGoogleMapFragment#loadGoogleMapFragment}.
     *
     * We loaded our {@link GoogleMap} via async listener. When everything is ready we go to setup our map.
     */
    private void loadGoogleMapFragment() {
        Log.d(TAG, "Try to load Fragment");

        CustomGoogleMapFragment fragment = CustomGoogleMapFragment.loadFragment(this.getActivity(), R.id.google_map_container);
        fragment.getMapAsync(this);
    }

    /**
     * When Async task is finish, whe start to setup our map settings.
     *
     * We change zoom from 1 to {@link ClusterMarkerMapFragment#DEFAULT_ZOOM_LEVEL}.
     *
     * @param map
     */
    private void setupMap(GoogleMap map) {
        Log.d(TAG, "Google Map is loaded");

        // Previous Zoom level will be equal to our Default Zoom Level
        previousZoomLevel = DEFAULT_ZOOM_LEVEL;

        // googleMap our application will use to store all information
        googleMap = map;

        // Move Google Map Camera to Default Zoom Level
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(DEFAULT_ZOOM_LEVEL));

        // The My Location button will be visible on the top right of the map.
        googleMap.setMyLocationEnabled(true);

        // Enable Zoom UI Controls
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        /*** Listeners ***/

        // Add Listener, when user change camera
        // googleMap.setOnCameraChangeListener(getCameraChangeListener());

        /*** Listeners ***/
    }
 }
