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
    // private static ItemsDB sItemsDB;
    private final ArrayList<Item> itemsDB= new ArrayList<>();

    public static void initialize(Context context){
        if (mDatabase == null){
            mDatabase = new DBCreate(context.getApplicationContext()).getWritableDatabase();
        }
    }

    public ArrayList<Item> getValues(){
        ItemCursorWrapper cursor = queryItems(null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            itemsDB.add(cursor.getItem());
            cursor.moveToNext();
        }
        cursor.close();
        return itemsDB;
    }
//    public ItemsDB() {
//        itemsDB.add(new Item("butter", "food waste"));
//        itemsDB.add(new Item("battery", "toxic waste"));
//        itemsDB.add(new Item("newspaper", "paper"));
//        itemsDB.add(new Item("milk carton", "general waste"));
//        itemsDB.add(new Item("bean can", "metal"));
//        itemsDB.add(new Item("butter", "food waste"));
//        itemsDB.add(new Item("juice bottle", "plastic"));
//    }

  //  public static void initialize() {
  //      if (sItemsDB == null) sItemsDB= new ItemsDB();
  //  }

  //  public static ItemsDB get() {
  //      if (sItemsDB == null) throw new IllegalStateException("ItemsDB must be initialized");
  //      return sItemsDB;
  //  }

    public void addItem(String what, String place){
        Item newItem= new Item(what, place);
        ContentValues values= getContentValues(newItem);
        mDatabase.insert(ItemsDbSchema.ItemTable.NAME, null, values);
    }

  //  public String searchForPlace(String item){
  //      String itemPlace = "not found";

  //      for (Item existingItem : itemsDB) {
  //          if (existingItem.equals(item)){
  //              itemPlace = existingItem.getPlace();
  //          }
  //      }
  //      return itemPlace;
  //  }

    public List<Item> getList(){
        return itemsDB;
    }

    public int size() {
        return itemsDB.size();
    }

    public String listItems() {
        String r= "";
        for (Item item: itemsDB)
            r= r+"\n Buy "+item.getWhat() + " in: "  + item.getPlace();
        return r;
    }

//    public void removeItem(String what) {
//        for (Item existingItem : itemsDB) {
//            if (existingItem.equals(what)) {
//                itemsDB.remove(existingItem);
//                break;
//            }
//        }
//    }

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
}