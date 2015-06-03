package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.R;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.enums.fragments.FragmentAnimationType;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.enums.fragments.FragmentTag;
import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.fragments.googleMaps.ClusterMarkerMapFragment;

/**
 * Created by Dimitar Danailov on 5/21/15.
 * email: dimityr.danailov@gmail.com
 */
public class BaseFragment extends Fragment {

    // Get class name
    private static final String TAG = BaseFragment.class.getName();

    protected Activity activity = null;

    public void setActivity() {
        this.activity = getActivity();
    }

    /**
     * If calling Activity is not equal to Attach requirements we throw exception.
     * Our goal is protection of fragments layer structure.
     *
     * @param activity
     * @param className
     */
    protected void validateOnAttachMethod(Activity activity, String className) {
        super.onAttach(activity);

        if (!activity.getClass().getName().equals(className)) {
            throw new ClassCastException(activity + " must be of type " + className);
        }
    }

    /**
     * We added a new fragment, only if you have fragment is not added and replace with
     * new {@link BaseFragment} child fragment if getBackStackEntryCount > 0
     *
     * @param activity
     * @param containerViewId - Resource id
     * @param fragmentTag     - Information for fragment tag. By fragment tag we initialize necessary fragment
     * @param animationType   - {@link FragmentAnimationType} we load animation, if you type is not equal to {@link FragmentAnimationType#NONE}
     */
    public static BaseFragment loadFragmentTransaction(Activity activity, Integer containerViewId, String fragmentTag, FragmentAnimationType animationType) {
        BaseFragment fragment = BaseFragment.getOrCreateFragmentByFragmentTag(activity, fragmentTag);
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragment != null) {
            // We start a new fragment transaction
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            // We load animation only if you want
            if (!animationType.equals(FragmentAnimationType.NONE)) {
                fragmentTransaction = BaseFragment.animateFragmentTransactionByAnimationType(fragmentTransaction, animationType);
            }

            int numberEntriesCurrentlyBackStackCount = fragmentManager.getBackStackEntryCount();

            if (numberEntriesCurrentlyBackStackCount == 0) {
                fragmentTransaction.add(containerViewId, fragment, fragmentTag);
            } else {
                fragmentTransaction.replace(containerViewId, fragment, fragmentTag);
            }

            // Add a new fragment in our back stack
            fragmentTransaction.addToBackStack(fragmentTag);
            //Log.d(TAG, "We added a new fragment in BackStack:" + fragmentTag);

            try {
                fragmentTransaction.commit();
            } catch (Exception exception) {
                Log.e(TAG, "We have problem with loadFragmentTransaction ", exception);
            }
        }

        return fragment;
    }

    /**
     * Try to access fragment from current XML or backs tack.
     * If you {@link FragmentManager} can't access fragment, we create a new
     * {@link BaseFragment} by {@link FragmentTag}
     *
     * @param activity
     * @param fragmentTag
     * @return
     */
    public static BaseFragment getOrCreateFragmentByFragmentTag(Activity activity, String fragmentTag) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag);
        BaseFragment BaseFragment = null;

        if (fragment == null) {
            BaseFragment = BaseFragment.initializeFragmentByTagName(fragmentTag);
        } else {
            BaseFragment = (BaseFragment) fragment;
            //Log.d(TAG, "Fragment exist:" + fragmentTag);
        }


        return BaseFragment;
    }

    /**
     * Try to update fragment information. We update fragment only if exist in XML or back stack.
     *
     * @param activity
     * @param fragmentTag
     */
    public void saveFragmentInstanceState(Activity activity, FragmentTag fragmentTag) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(fragmentTag.getAbbreviation());

        if (fragment != null) {
            Log.d(TAG, "Fragment exist:" + fragmentTag);
            fragmentManager.saveFragmentInstanceState(this);
        }
    }

    /**
     * We try to initialize a new BaseFragment child class by {@link FragmentTag} value
     *
     * @param fragmentTag
     * @return
     */
    public static BaseFragment initializeFragmentByTagName(String fragmentTag) {
        BaseFragment BaseFragment = null;

        if (fragmentTag.equals(FragmentTag.GOOGLE_MAP.getAbbreviation())) {
            BaseFragment = new ClusterMarkerMapFragment();
        }

        return BaseFragment;
    }

    /**
     * By {@link FragmentAnimationType} animation type, we load a different animation.
     * <p/>
     * Default animation type is enter from right to exit to right
     *
     * @param fragmentTransaction
     * @param animationType       - {@link FragmentAnimationType}
     */
    public static FragmentTransaction animateFragmentTransactionByAnimationType(FragmentTransaction fragmentTransaction, FragmentAnimationType animationType) {

        String abbreviation = animationType.getAbbreviation();

        if (abbreviation.equals(FragmentAnimationType.FADE_IN_FADE_OUT.getAbbreviation())) {
            fragmentTransaction.setCustomAnimations(R.anim.object_animator_fadein, R.anim.object_animator_fadeout);
        } else if (abbreviation.equals(FragmentAnimationType.ENTER_RIGHT_AND_FADE_IN_EXIT_RIGHT_FADE_OUT.getAbbreviation())) {
            fragmentTransaction.setCustomAnimations(
                    R.anim.object_animator_enter_from_right_fadein,
                    R.anim.object_animator_exit_to_right_fadeout
            );
        } else {
            fragmentTransaction.setCustomAnimations(
                    R.anim.object_animator_enter_from_right,
                    R.anim.object_animator_exit_to_right

            );
        }

        return fragmentTransaction;
    }

    /*
     * Try to hide keyboard.
     * Post: http://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
     * Post @author: ekjyot
     *
     */
    public void hideKeyboard() {
        // Check if no view has focus:
        View view = this.activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
