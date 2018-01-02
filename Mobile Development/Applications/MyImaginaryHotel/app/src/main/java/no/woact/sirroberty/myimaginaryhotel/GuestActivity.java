package no.woact.sirroberty.myimaginaryhotel;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import java.util.UUID;

/**
 * Parent class for GuestFragment
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

public class GuestActivity extends SingleFragmentActivity {

    //Made static for global access in app.
    private static final String EXTRA_GUEST_ID = "no.woact.sirroberty.myimaginaryhotel.guest_id";

    //This method passes guest id to display a specific guest.
    public static Intent newIntent(Context packageContext, UUID guestId){
        Intent intent = new Intent(packageContext, GuestActivity.class);
        intent.putExtra(EXTRA_GUEST_ID, guestId);
        return intent;
    }



    @Override
    protected Fragment createFragment() {
        UUID guestId = (UUID) getIntent().getSerializableExtra(EXTRA_GUEST_ID);
        return GuestFragment.newInstance(guestId);
    }

}
