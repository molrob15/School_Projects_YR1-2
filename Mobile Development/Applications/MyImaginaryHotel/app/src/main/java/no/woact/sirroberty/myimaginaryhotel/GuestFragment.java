package no.woact.sirroberty.myimaginaryhotel;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 *
 *
 *  Inspired by: Android Programming,
 * The big nerd ranch guide 2:nd edition,
 * Chapters 7 to 14, Project Criminal Intent.
 *@authors Bill Phillips, Chris Stewart,
 * Brian Hardy and Kristin Marsicano
 *
 * @author Robert Mattias Molin
 * version: 2017.05.26
 *
 */
public class GuestFragment extends Fragment {

    private static final String ARG_GUEST_ID = "guest_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_CHECKIN_DATE = 0;

    private static final int REQUEST_CHECKOUT_DATE = 0;//Not yet implemented

    private Guest mGuest;
    private EditText mNameField;
    private Button mBtncheckInDate;
    private CheckBox mCheckedInBox;

    private CheckBox mCheckedOutBox;
    private Button mBtnPickRoom; //Not yet implemented
    private Button mBtnCheckOutDate;
    private Button mBtnMap;


    //This method attaches arguments bundle to fragment
    public static GuestFragment newInstance(UUID guestId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_GUEST_ID, guestId);

        GuestFragment fragment = new GuestFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID guestId = (UUID) getArguments().getSerializable(ARG_GUEST_ID);
        mGuest = GuestDataSource.get(getActivity()).getGuest(guestId);
    }

    //Push update on instance of Guest
    @Override
    public void onPause() {
        super.onPause();
        GuestDataSource.get(getActivity()).upDateGuestDetail(mGuest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_guest, container, false);

        //Check in Date button
        mBtncheckInDate = (Button) v.findViewById(R.id.checkIn_date);
        updateCheckInDate();//Update text on date button
        mBtncheckInDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DateSelectorFragment dialog = DateSelectorFragment
                        .newInstance(mGuest.getDate());
                //Sets up a relationship between GuestFragment and DateSelectorFragment
                dialog.setTargetFragment(GuestFragment.this, REQUEST_CHECKIN_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        //Check Out Date button
        mBtnCheckOutDate = (Button) v.findViewById(R.id.checkOut_date);
        updateCheckInDate();//Update text on date button
        mBtnCheckOutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DateSelectorFragment dialog = DateSelectorFragment
                        .newInstance(mGuest.getDate());
                //Sets up a relationship between GuestFragment and DateSelectorFragment
                dialog.setTargetFragment(GuestFragment.this, REQUEST_CHECKOUT_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });



        //Map Button
        mBtnMap = (Button) v.findViewById(R.id.map);
        mBtnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        //Marks checkbox Check In
        mCheckedInBox = (CheckBox) v.findViewById(R.id.guest_checkedIn);
        mCheckedInBox.setChecked(mGuest.isCheckedIn());
        mCheckedInBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mGuest.setCheckedIn(isChecked);//Sets status for checked in guest
            }
        });

        //Marks checkbox Check Out
        mCheckedOutBox = (CheckBox) v.findViewById(R.id.guest_checkedOut);
        mCheckedOutBox.setChecked(mGuest.isCheckedOut());
        mCheckedInBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mGuest.setCheckedOut(isChecked);//Sets status for checked out guest
            }
        });


        mNameField = (EditText) v.findViewById(R.id.guest_name);
        mNameField.setText(mGuest.getName());
        mNameField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mGuest.setName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return v;
    }//End onCreateView


    /**
     * This method sets the Guest's date of check in,
     * and calls method updateCheckInDate who updates the text on the date button.
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CHECKIN_DATE ) {
            Date date = (Date) data
                    .getSerializableExtra(DateSelectorFragment.EXTRA_DATE);
            mGuest.setDate(date);
            updateCheckInDate();
        }
        /*else if(requestCode == REQUEST_CHECKOUT_DATE){
            Date date = (Date) data
                    .getSerializableExtra(DateSelectorFragment.EXTRA_DATE);
            mGuest.setDate(date);
            updateCheckInDate();
        }*/
    }

    private void updateCheckInDate() {
        mBtncheckInDate.setText(mGuest.getDate().toString());
    }


}
