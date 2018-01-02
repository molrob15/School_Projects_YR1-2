package no.woact.sirroberty.myimaginaryhotel;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * This is a generic template class
 * for fragments to avoid code duplication.
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

public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment(); //Abstract method for subclasses to implement

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        /*Check for fragment in FragmentManager, if it doesn't exist create and add it */
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

    }//End onCreate



}
