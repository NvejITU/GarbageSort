package dk.itu.local_garbage;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import java.util.Map;

public class ItemsViewModel extends ViewModel {
    private static MutableLiveData<ItemsDB> items;

    public ItemsViewModel() {
        items= new MutableLiveData<>();
        items.setValue(new ItemsDB());
    }

    public MutableLiveData<ItemsDB> getValue() { return items; }

    public void addItem(String what, String place){
        ItemsDB temp= items.getValue();
        temp.addItem(what, place);
        items.setValue(temp);
    }

    public void removeItem(String what){
        ItemsDB temp= items.getValue();
        temp.removeItem(what);
        items.setValue(temp);
    }
    public List<Item> getList() {  return items.getValue().getList();  }

    public int size() { return items.getValue().size(); }

    public String searchForPlace(String item){
        String itemPlace = "not found";

        for (Item existingItem : items.getValue().getList()) {
            if (existingItem.equals(item)){
                itemPlace = existingItem.getPlace();
            }
        }
        return itemPlace;
    }
}