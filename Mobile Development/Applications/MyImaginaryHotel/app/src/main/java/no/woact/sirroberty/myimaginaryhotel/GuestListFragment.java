package no.woact.sirroberty.myimaginaryhotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 *
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

public class GuestListFragment extends Fragment {

    private RecyclerView mGuestRecyclerView;
    private GuestAdapter mAdapter;
    private boolean mSubTitleVisible;


    //Lets the FragmentManager know that CrimeListFragment needs a menu item
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guest_list, container, false);
        mGuestRecyclerView = (RecyclerView) view.findViewById(R.id.guest_recycler_view);
        mGuestRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }


    //Inflates menu in fragment_guest_list.xml
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_guest_list, menu);

        //Recreates action items when user pushes Show Subtitle
        MenuItem subtitleItem = menu.findItem(R.id.menu_item_show_subtitle);
        if(mSubTitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        }else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }


    /*This method responds to selection in the options menu ( + )
    * And shows the number of guests in the hotel as a subtitle
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menu_item_new_guest:
                Guest guest = new Guest();
                GuestDataSource.get(getActivity()).addGuest(guest);
                Intent intent = GuestPagerActivity
                        .newIntent(getActivity(), guest.getId());
                startActivity(intent);
                return true;

            case R.id.menu_item_show_subtitle:
                mSubTitleVisible = !mSubTitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;

            default:
                return super.onOptionsItemSelected(item);//Calls super class if item ID is not found
        }

    }


    /**
     * This method sets the subtitle toolbar/actionbar
     * that displays the current number of guests in the hotel
     *
     */
    private void updateSubtitle(){
        GuestDataSource guestDataSource = GuestDataSource.get(getActivity());
        int guestCounter = guestDataSource.getGuests().size();//Returns list of guests
        String subtitle = getString(R.string.subtitle_format, guestCounter);
        if(!mSubTitleVisible){ subtitle = null; } //Hide subtitle if not set to visible.

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }


    //This method creates an Adapter and sets it to the RecyclerView
    private void updateUI() {
        GuestDataSource guestDataSource = GuestDataSource.get(getActivity());
        List<Guest> guests = guestDataSource.getGuests(); //Gets list from method in class GuestDataSource

        if (mAdapter == null) {
            mAdapter = new GuestAdapter(guests);
            mGuestRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setGuests(guests);
            mAdapter.notifyDataSetChanged();
        }
        updateSubtitle();//Shows the most recent state of the subtitle menu of guests

    }


    /*Inner classes GuestHolder and GuestAdapter*/
    private class GuestHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {

        private  Guest mGuest;
        private TextView mNameTextView;
        private TextView mDateTextView;
        private CheckBox mGuestCheckedInBox;


        //Constructor
        public GuestHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_guest_name_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_guest_date_text_view);
            mGuestCheckedInBox = (CheckBox) itemView.findViewById(R.id.list_item_guest_checkedIn_check_box);
        }

        //Help method for binding Guest objects to textviews and checkbox
        public void bindGuest(Guest guest){
            mGuest = guest;
            mNameTextView.setText(mGuest.getName());
            mDateTextView.setText(mGuest.getDate().toString());
            mGuestCheckedInBox.setChecked(mGuest.isCheckedIn());
        }

        @Override
        public void onClick(View v) {
            Intent intent = GuestPagerActivity.newIntent(getActivity(), mGuest.getId());
            startActivity(intent);
        }

    }//End inner class GuestHolder

    /**
     * Inner adapter class
     * This class is used to create a ViewHolder and connect it
     * with a Guest object.
     */
    private class GuestAdapter extends RecyclerView.Adapter<GuestHolder> {

        private List<Guest> mGuests;

        public GuestAdapter(List<Guest> guests) {
            mGuests = guests;
        }

        //Creates View and wraps it in a ViewHolder
        @Override
        public GuestHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_guest, parent, false);
            return new GuestHolder(view);
        }

        //Binds the ViewHolders view to a Guest object the model class
        @Override
        public void onBindViewHolder(GuestHolder holder, int position) {
            Guest guest = mGuests.get(position);
            holder.bindGuest(guest);
        }

        //This method returns the contents of the List mGuest containing dummy data.
        @Override
        public int getItemCount() {
            return mGuests.size();
        }
        //Swaps display of guests from DB
        public void setGuests(List<Guest> guests){
            mGuests = guests;

        }


    }//End inner class GuestAdapter


}//End GuestListFragment
