package no.woact.sirroberty.myimaginaryhotel.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

import no.woact.sirroberty.myimaginaryhotel.Guest;
import no.woact.sirroberty.myimaginaryhotel.database.HotelDbSchema.GuestTable;


/**
 * This class receives a Wrapper from class GuestDataSource
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
public class GuestCursorWrapper extends CursorWrapper {

    public GuestCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    //This method pulls out relevant column data from DB
    public Guest getGuest(){
        String uuidString = getString(getColumnIndex(GuestTable.Columns.UUID));
        String name = getString(getColumnIndex(GuestTable.Columns.NAME));
        long date = getLong(getColumnIndex(GuestTable.Columns.CHECKIN_DATE));
        //int checkedin = getInt(getColumnIndex(GuestTable.Columns.CHECKEDIN));

        Guest guest = new Guest(UUID.fromString(uuidString));
        guest.setName(name);
        guest.setDate(new Date(date));
        //guest.setCheckedIn(checkedin != 0);

        return guest;
    }





}
