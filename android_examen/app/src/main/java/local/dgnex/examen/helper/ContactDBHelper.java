package local.dgnex.examen.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ContactDBHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "contact.db";

    public static final String CONTACT_KEY = "id";
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_SURNAME = "surname";
    public static final String CONTACT_COMPANY = "company";
    public static final String CONTACT_ADDRESS = "address";
    public static final String CONTACT_PHONE = "phone";
    public static final String CONTACT_MAIL = "mail";
    public static final String CONTACT_SECTOR = "sector";
    public static final String CONTACT_FAVORITE= "favorite";

    public static final String CONTACT_TABLE_NAME = "contact";

    public static final int CONTACT_KEY_COLUMN_INDEX = 0;
    public static final int CONTACT_NAME_COLUMN_INDEX = 1;
    public static final int CONTACT_SURNAME_COLUMN_INDEX = 2;
    public static final int CONTACT_COMPANY_COLUMN_INDEX = 3;
    public static final int CONTACT_ADDRESS_COLUMN_INDEX = 4;
    public static final int CONTACT_PHONE_COLUMN_INDEX = 5;
    public static final int CONTACT_MAIL_COLUMN_INDEX = 6;
    public static final int CONTACT_SECTOR_COLUMN_INDEX = 7;
    public static final int CONTACT_FAVORITE_COLUMN_INDEX = 8;

    private static final String CONTACT_TABLE_CREATE =
            "CREATE TABLE " + CONTACT_TABLE_NAME + " (" +
                    CONTACT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CONTACT_NAME + " TEXT, " +
                    CONTACT_SURNAME + " TEXT, " +
                    CONTACT_COMPANY + " TEXT, " +
                    CONTACT_ADDRESS + " TEXT, " +
                    CONTACT_PHONE + " TEXT, " +
                    CONTACT_MAIL + " TEXT, " +
                    CONTACT_SECTOR + " TEXT, " +
                    CONTACT_FAVORITE + " INTEGER);";

    private static final String CONTACT_TABLE_DROP = "DROP TABLE IF EXISTS " + CONTACT_TABLE_NAME + ";";

    public ContactDBHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONTACT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(CONTACT_TABLE_DROP);
        onCreate(db);
    }
}
