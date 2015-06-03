package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.activities;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.MapFragment;

import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.R;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.enums.fragments.FragmentAnimationType;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.enums.fragments.FragmentTag;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments.BaseFragment;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments.googleMaps.widgets.CustomGoogleMapFragment;

public class MapsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (savedInstanceState == null) {
            BaseFragment.loadFragmentTransaction(this, R.id.map_container, FragmentTag.GOOGLE_MAP.getAbbreviation(), FragmentAnimationType.NONE);
        }
    }
}
