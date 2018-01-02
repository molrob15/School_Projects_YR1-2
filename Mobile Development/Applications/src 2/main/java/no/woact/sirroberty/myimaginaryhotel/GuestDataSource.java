package no.woact.sirroberty.myimaginaryhotel;

/*
* Singleton class, that stores Guest objects
* Used throughout the application.
*
*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import no.woact.sirroberty.myimaginaryhotel.database.DBhelper;
import no.woact.sirroberty.myimaginaryhotel.database.GuestCursorWrapper;
import no.woact.sirroberty.myimaginaryhotel.database.HotelDbSchema.GuestTable;

import static no.woact.sirroberty.myimaginaryhotel.database.HotelDbSchema.GuestTable.Columns.CHECKIN_DATE;
import static no.woact.sirroberty.myimaginaryhotel.database.HotelDbSchema.GuestTable.Columns.NAME;
import static no.woact.sirroberty.myimaginaryhotel.database.HotelDbSchema.GuestTable.Columns.UUID;
import static no.woact.sirroberty.myimaginaryhotel.database.HotelDbSchema.GuestTable.TABLE_NAME;

public class GuestDataSource {
    private static GuestDataSource sGuestDataSource;


    private Context mContext;
    private SQLiteDatabase mDatabase;

    //Private Constructor
    private GuestDataSource(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DBhelper(mContext)
                .getWritableDatabase();

    }


    public static GuestDataSource get(Context context) {
        if (sGuestDataSource == null) {
            sGuestDataSource = new GuestDataSource(context);
        }
        return sGuestDataSource;
    }


    /**
     * This method makes a query for all guests
     * in the table and fills up the ArrayList with data
     * and returns the list
     */
    public List<Guest> getGuests() {
        List<Guest> guests = new ArrayList<>();
        GuestCursorWrapper cursor = guestQuery(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                guests.add(cursor.getGuest());
                cursor.moveToNext();
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            cursor.close();// Close to prevent leak
        }
        return guests;
    }


    /*This method returns guest if a valid id is found in the list*/
    public Guest getGuest(UUID id) {
        GuestCursorWrapper cursor = guestQuery(
                GuestTable.Columns.UUID + " = ?",
                new String[] { id.toString() }
        );

        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getGuest();
        }
        finally {
            cursor.close();
        }

    }//End getGuest

    //Insert row in DB
    public void addGuest(Guest g) {
        ContentValues values = getContentValues(g);
        mDatabase.insert(TABLE_NAME, null, values);

    }

    //Delete guest in DB
    public int deleteGuest(Guest guest) {
        String uuidString = guest.getId().toString();
        return mDatabase.delete(
                GuestTable.TABLE_NAME,
                GuestTable.Columns.UUID + " = ?",
                new String[] { uuidString }
        );
    }


    /**
     * This method updates specified row in DB
     * The third argument specifies the values in a where clause
     * (String [])
     */
    public void upDateGuestDetail(Guest guest) {
        String uuidString = guest.getId().toString();
        ContentValues values = getContentValues(guest);

        mDatabase.update(TABLE_NAME, values, GuestTable.Columns.UUID +
                " = ?", new String[]{uuidString}); //Prevents SQL injection attacks
    }

    /**
     * This method uses ContentValues to write to the database, on a key-value basis
     * It's job is to get a Guest instance into a ContentValues.
     */
    private static ContentValues getContentValues(Guest guest) {
        ContentValues values = new ContentValues();
        values.put(UUID, guest.getId().toString());
        values.put(NAME, guest.getName());
        values.put(CHECKIN_DATE, guest.getDate().getTime());
        //values.put(CHECKOUT_DATE, guest.getDate().getTime());
        //values.put(ROOMS, guest.......());
        return values;
    }

    /**
     * This method makes a query
     * for reading data from the database table.
     * The conditions for method public Cursor query are:
     * String table, String[] columns, String where,
     * String[] whereArgs, String groupBy, String having,
     * String orderBy,
     * String limit.
     */
    private GuestCursorWrapper guestQuery(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                GuestTable.TABLE_NAME,
                null, //null gets all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null  //orderBy
        );
        return new GuestCursorWrapper(cursor);
    }


}
