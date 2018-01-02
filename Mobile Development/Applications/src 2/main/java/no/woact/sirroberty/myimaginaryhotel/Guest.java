package no.woact.sirroberty.myimaginaryhotel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Model class for myimaginaryhotel
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

public class Guest {

    private UUID mId; // Unique identifier
    private String mName;
    private Date mDate;
    private boolean mCheckedIn;
    private boolean mCheckedOut;
    private String Room;


    //Constructor @no param
    public Guest() {
       this(UUID.randomUUID());
    }

    //Constructor @param id
    public Guest(UUID id){
        mId = id;
        mDate = new Date();
    }



    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isCheckedIn() {
        return mCheckedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.mCheckedIn = checkedIn;
    }

    public boolean isCheckedOut() {
        return mCheckedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        mCheckedOut = checkedOut;
    }


    public UUID getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }
}
