package no.woact.sirroberty.myimaginaryhotel.database;

/**
 * This class defines the DB schema/table for myimaginaryhotel
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

public class HotelDbSchema {

    public static final class GuestTable {

        public static final String TABLE_NAME = "rooms";

        public static final class Columns {
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String CHECKIN_DATE = "checkindate";
            //public static final String CHECKOUT_DATE = "checkoutdate";
            //public static final String CHECKEDIN = "checkedin";
            //public static final String CHECKEDOUT = "checkedout";
            //public static final String ROOM = "room";
        }
    }
}
