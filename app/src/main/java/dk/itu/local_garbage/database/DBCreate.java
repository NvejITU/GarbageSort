package dk.itu.local_garbage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import dk.itu.local_garbage.database.ItemsDbSchema.ItemTable;

public class DBCreate extends  SQLiteOpenHelper{
    private static final int VERSION = 1;
    public static final String DATABASE_NAME = "shopping.db";

    public DBCreate(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemTable.NAME + "(" +
                ItemTable.Cols.WHAT + ", " + ItemTable.Cols.PLACE + ")"
        );

        //For testing purposes add some items to the database
        addItem(db,"coffee", "Irma");
        addItem(db,"carrots", "Netto");
        addItem(db,"milk", "Netto");
        addItem(db,"bread", "bakery");
        addItem(db,"butter", "Irma");
    }

    private void addItem(SQLiteDatabase db, String what,  String place) {
        db.execSQL("INSERT INTO Items (what, placeC) VALUES ('$what', '$place')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
