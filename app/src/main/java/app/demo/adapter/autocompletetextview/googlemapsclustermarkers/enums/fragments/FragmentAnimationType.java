package app.demo.adapter.autocompletetextview.googlemapsclustermarkers.enums.fragments;

import app.demo.adapter.autocompletetextview.googlemapsclustermarkers.collections.enums.AbbreviationMap;

/**
 * Created by Dimitar Danailov on 5/28/15.
 * email: dimityr.danailov@gmail.com
 *
 * Post: http://stackoverflow.com/questions/1080904/how-can-i-lookup-a-java-enum-from-its-string-value
 */
public enum FragmentAnimationType {
    // we not have animation
    NONE ("NONE"),

    // fade between fragments
    FADE_IN_FADE_OUT ("FADE_IN_FADE_OUT"),

    // Move content and fade between fragments
    ENTER_RIGHT_AND_FADE_IN_EXIT_RIGHT_FADE_OUT ("ENTER_RIGHT_AND_FADE_IN_EXIT_RIGHT_FADE_OUT");

    private final String abbreviation;

    /**
     * Reverse-lookup map for getting a fragment tag from an abbreviation
     */
    private static final AbbreviationMap<String, FragmentAnimationType> lookup = new AbbreviationMap<String, FragmentAnimationType>();

    static {
        for (FragmentAnimationType tag : FragmentAnimationType.values()) {
            lookup.put(tag.getAbbreviation(), tag);
        }
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public static FragmentAnimationType get(String abbreviation) {
        return lookup.get(abbreviation);
    }

    private FragmentAnimationType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

}
