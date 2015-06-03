package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments.googleMaps.widgets;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

/**
 * Created by Dimitar Danailov on 6/2/15.
 * email: dimityr.danailov@gmail.com
 */
public class CustomGoogleMapFragment extends MapFragment {

    // Get class name
    private static final String TAG = CustomGoogleMapFragment.class.getName();

    /**
     * We added a new fragment, only if you have fragment is not added and replace with
     * new {@link CustomGoogleMapFragment} child fragment if getBackStackEntryCount > 0
     *
     * @param activity
     * @param containerViewId - Resource id
     *
     */
    public static CustomGoogleMapFragment loadFragment(Activity activity, Integer containerViewId) {
        CustomGoogleMapFragment fragment = (CustomGoogleMapFragment) activity.getFragmentManager().findFragmentById(containerViewId);
        FragmentManager fragmentManager = activity.getFragmentManager();

        if (fragment == null) {
            fragment = new CustomGoogleMapFragment();
        }

        if (fragment != null) {
            // We start a new fragment transaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            int numberEntriesCurrentlyBackStackCount = fragmentManager.getBackStackEntryCount();

            if (numberEntriesCurrentlyBackStackCount == 0) {
                fragmentTransaction.add(containerViewId, fragment);
            } else {
                fragmentTransaction.replace(containerViewId, fragment);
            }

            // Add a new fragment in our back stack
            fragmentTransaction.addToBackStack(String.valueOf(containerViewId));
            Log.d(TAG, "We added a new fragment in BackStack:");

            try {
                fragmentTransaction.commit();
            } catch (Exception exception) {
                Log.e(TAG, "We have problem with loadFragmentTransaction ", exception);
            }
        }

        return fragment;
    }
}
