package dk.itu.local_garbage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import dk.itu.local_garbage.database.ItemsDbSchema.ItemTable;

public class DBCreate extends  SQLiteOpenHelper{
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "garbage.db";

    public DBCreate(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemTable.NAME + "(" +
                ItemTable.Cols.WHAT + ", " + ItemTable.Cols.PLACE + ")"
        );

        //For testing purposes add some items to the database
        addItem(db,"coffee", "food waste");
        addItem(db,"battery", "toxic waste");
        addItem(db,"milk carton", "daily waste");
        addItem(db,"newspaper", "paper");
        addItem(db,"butter", "food waste");
    }

    private void addItem(SQLiteDatabase db, String what,  String place) {
        db.execSQL("INSERT INTO Items (what, placeC) VALUES ('" + what + "', '" + place + "')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
