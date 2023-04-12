package dk.itu.local_garbage;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import java.util.Map;

public class ItemsViewModel extends ViewModel {
    private static MutableLiveData<ItemsDB> items;

    public ItemsViewModel() {
        items = new MutableLiveData<>();
        items.setValue(new ItemsDB());
    }

    public void initialize(Context context){
        items.getValue().initialize(context);
    }
    public MutableLiveData<ItemsDB> getValue() {
        return items;
    }

    public void addItem(String what, String place) {
        ItemsDB temp = items.getValue();
        temp.addItem(what, place);
        items.setValue(temp);
    }

    public List<Item> getList() {
        return items.getValue().getValues();
    }

    public void removeItem(String what) {
        ItemsDB temp = items.getValue();
        temp.removeItem(what);
        items.setValue(temp);
    }

    public int size() {
        return items.getValue().size();
    }

    public String findPlace(String item){
        String itemPlace = "not found";

        for (Item existingItem : items.getValue().getValues()) {
            if (existingItem.getWhat().equals(item)){
                itemPlace = existingItem.getPlace();
            }
        }
        return itemPlace;
    }

/*    public String searchForPlace(String item){
        String itemPlace = "not found";
        for (int i = 0; i <= items.getValue().size(); i++) {
            String existingItem = items.getValue().getList().get(i).getWhat();
            if (existingItem.equals(item)){
                itemPlace = items.getValue().getList().get(i).getPlace();
            }
        }

        return itemPlace;
    }

    public String findPlace(String item) {
        ItemsDB temp = items.getValue();
        String place = temp.searchForPlace(item);
        items.setValue(temp);
        return place;
    }
*/
}