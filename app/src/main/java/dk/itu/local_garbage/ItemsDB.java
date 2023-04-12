package dk.itu.local_garbage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.ViewModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import dk.itu.local_garbage.database.DBCreate;
import dk.itu.local_garbage.database.ItemCursorWrapper;
import dk.itu.local_garbage.database.ItemsDbSchema;

public class ItemsDB extends ViewModel {

    private static SQLiteDatabase mDatabase;
//    private final ArrayList<Item> itemsDB = new ArrayList<>();

    public static void initialize(Context context){
        if (mDatabase == null){
            mDatabase = new DBCreate(context.getApplicationContext()).getWritableDatabase();
        }
    }

    public ArrayList<Item> getValues() {
        ArrayList<Item> items= new ArrayList<Item>();
        ItemCursorWrapper cursor= queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            items.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    /*    public ItemsDB() {
            itemsDB.add(new Item("coffee", "food waste"));
            itemsDB.add(new Item("battery", "toxic waste"));
            itemsDB.add(new Item("newspaper", "paper"));
            itemsDB.add(new Item("milk carton", "daily waste"));
            itemsDB.add(new Item("butter", "food waste"));
            itemsDB.add(new Item("book", "paper"));
            itemsDB.add(new Item("jar", "glass"));
            itemsDB.add(new Item("can", "metal"));
            itemsDB.add(new Item("meat package", "plastic"));
            itemsDB.add(new Item("phone", "electronics"));
            itemsDB.add(new Item("onion", "food waste"));
            itemsDB.add(new Item("paint", "chemical waste"));
            itemsDB.add(new Item("pasta", "food waste"));
            itemsDB.add(new Item("wood table", "wood"));
            itemsDB.add(new Item("rice", "food waste"));
            itemsDB.add(new Item("sugar", "food waste"));
            itemsDB.add(new Item("plastic cutlery", "plastic"));
            itemsDB.add(new Item("computer", "electronics"));
        }
    */
    public void addItem(String what, String place) {
        Item newItem= new Item(what, place);
        ContentValues values= getContentValues(newItem);
        mDatabase.insert(ItemsDbSchema.ItemTable.NAME, null, values);
    }

    public void removeItem(String what) {
        Item newItem= new Item(what, "");
        String selection= ItemsDbSchema.ItemTable.Cols.WHAT + " LIKE ?";
        int changed= mDatabase.delete(ItemsDbSchema.ItemTable.NAME, selection, new String[]{newItem.getWhat()});
    }

    public int size() {
        return getValues().size();
    }

    private static ContentValues getContentValues(Item item){
        ContentValues values =new ContentValues();
        values.put(ItemsDbSchema.ItemTable.Cols.WHAT,item.getWhat());
        values.put(ItemsDbSchema.ItemTable.Cols.PLACE,item.getPlace());
        return values;
    }

    static private ItemCursorWrapper queryItems(String placeClause, String[] placeArgs) {
        Cursor cursor = mDatabase.query(ItemsDbSchema.ItemTable.NAME,
                null,
                placeClause, placeArgs,
                null, null, null);

        return new ItemCursorWrapper(cursor);
    }

/*    public String searchForPlace(String item){
              String itemPlace = "not found";

              for (Item existingItem : itemsDB) {
                  if (existingItem.equals(item)){
                      itemPlace = existingItem.getPlace();
                  }
              }
              return itemPlace;
          }
*/
}