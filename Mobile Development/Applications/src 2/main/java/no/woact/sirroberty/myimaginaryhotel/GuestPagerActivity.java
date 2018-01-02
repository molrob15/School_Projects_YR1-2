package no.woact.sirroberty.myimaginaryhotel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;


/**
 * This class holds a FragmentStatePagerAdapter.. phew! and it's job is to provide views
 * It gets the position for a guest in the records
 * and displays the guest details from that position.
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
public class GuestPagerActivity extends AppCompatActivity {

    private static final String EXTRA_GUEST_ID = "no.woact.sirroberty.myimaginaryhotel.guest_id";

    private ViewPager mViewPager;
    private List<Guest> mGuests;

    public static Intent newIntent(Context packageContext, UUID guestId){

        Intent intent = new Intent(packageContext, GuestPagerActivity.class);
        intent.putExtra(EXTRA_GUEST_ID, guestId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_pager);

        UUID guestId = (UUID) getIntent().getSerializableExtra(EXTRA_GUEST_ID);

        mViewPager = (ViewPager) findViewById(R.id.activity_guest_pager_view_pager);
        mGuests = GuestDataSource.get(this).getGuests();//Get list of guests from model class GuestDataSource
        FragmentManager fragmentManager = getSupportFragmentManager();

        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Guest guest = mGuests.get(position);
                return GuestFragment.newInstance(guest.getId());
            }

            @Override
            public int getCount() {
                return mGuests.size();
            }
        });
        //This loop finds the right guest item in the list
        for (int i = 0; i < mGuests.size(); i++){
            if(mGuests.get(i).getId().equals(guestId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }




}
