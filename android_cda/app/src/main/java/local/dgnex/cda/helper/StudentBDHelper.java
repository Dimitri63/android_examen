package local.dgnex.cda.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentBDHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "cda.db";

    public static final String STUDENT_KEY = "id";
    public static final String STUDENT_FIRSTNAME = "firstName";
    public static final String STUDENT_LASTNAME = "lastName";
    public static final String STUDENT_NICKNAMES = "nicknames";
    public static final String STUDENT_AGE = "age";
    public static final String STUDENT_PHOTO = "photo";
    public static final String STUDENT_POWER = "power";
    public static final String STUDENT_STRENGTHS = "strengths";
    public static final String STUDENT_WEAKNESSES = "weaknesses";

    public static final String VIDEO_TABLE_NAME = "student";

    public static final int STUDENT_KEY_COLUMN_INDEX = 0;
    public static final int STUDENT_FIRSTNAME_COLUMN_INDEX = 1;
    public static final int STUDENT_LASTNAME_COLUMN_INDEX = 2;
    public static final int STUDENT_NICKNAMES_COLUMN_INDEX = 3;
    public static final int STUDENT_AGE_COLUMN_INDEX = 4;
    public static final int STUDENT_PHOTO_COLUMN_INDEX = 5;
    public static final int STUDENT_POWER_COLUMN_INDEX = 6;
    public static final int STUDENT_STRENGTHS_COLUMN_INDEX = 7;
    public static final int STUDENT_WEAKNESSES_COLUMN_INDEX = 8;

    private static final String VIDEO_TABLE_CREATE =
            "CREATE TABLE " + VIDEO_TABLE_NAME + " (" +
                    STUDENT_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    STUDENT_FIRSTNAME + " TEXT, " +
                    STUDENT_LASTNAME + " TEXT, " +
                    STUDENT_NICKNAMES + " TEXT, " +
                    STUDENT_AGE + " INT, " +
                    STUDENT_PHOTO + " INT, " +
                    STUDENT_AGE + " INT, " +
                    STUDENT_POWER + " INT, " +
                    STUDENT_STRENGTHS + " INT, " +
                    STUDENT_WEAKNESSES + " TEXT);";

    private static final String VIDEO_TABLE_DROP = "DROP TABLE IF EXISTS " + VIDEO_TABLE_NAME + ";";

    public StudentBDHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(VIDEO_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(VIDEO_TABLE_DROP);
        onCreate(sqLiteDatabase);
    }
}
