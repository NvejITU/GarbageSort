package dk.itu.local_garbage;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ItemsDB extends ViewModel {
    // private static ItemsDB sItemsDB;
    private final List<Item> itemsDB= new ArrayList<>();

    public ItemsDB() {
        itemsDB.add(new Item("butter", "food waste"));
        itemsDB.add(new Item("battery", "toxic waste"));
        itemsDB.add(new Item("newspaper", "paper"));
        itemsDB.add(new Item("milk carton", "general waste"));
        itemsDB.add(new Item("bean can", "metal"));
        itemsDB.add(new Item("butter", "food waste"));
        itemsDB.add(new Item("juice bottle", "plastic"));
    }

  //  public static void initialize() {
  //      if (sItemsDB == null) sItemsDB= new ItemsDB();
  //  }

  //  public static ItemsDB get() {
  //      if (sItemsDB == null) throw new IllegalStateException("ItemsDB must be initialized");
  //      return sItemsDB;
  //  }

    public void addItem(String what, String place){
        itemsDB.add(new Item(what, place));
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

    public void removeItem(String what) {
        for (Item existingItem : itemsDB) {
            if (existingItem.equals(what)) {
                itemsDB.remove(existingItem);
                break;
            }
        }
    }
}