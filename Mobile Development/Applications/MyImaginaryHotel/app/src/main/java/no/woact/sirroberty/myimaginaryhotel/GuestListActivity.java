package no.woact.sirroberty.myimaginaryhotel;

import android.support.v4.app.Fragment;

/**
 * Parent class for GuestListFragment
 *
 * Inspired by: Android Programming,
 * The big nerd ranch guide 2:nd edition,
 * Chapters 7 to 14, Project Criminal Intent.
 *@authors Bill Phillips, Chris Stewart,
 * Brian Hardy and Kristin Marsicano
 *
 * @author Robert Mattias Molin
 * version: 2017.05.26
 */

public class GuestListActivity extends SingleFragmentActivity  {
    @Override
    protected Fragment createFragment() {
        return new GuestListFragment();
    }


}
