package dk.itu.local_garbage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class UIFragment extends Fragment {
    // Model: Database of items
   // private static ItemsDB itemsDB;
    Button addItem, listItems, deleteItem;
    private TextView newWhat, newPlace;
    ItemsViewModel itemsDB;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        final View v = inflater.inflate(R.layout.fragment_ui, container, false);

        //Text Fields
        TextView input = v.findViewById(R.id.what_text);
        TextView place = v.findViewById(R.id.place_text);

        itemsDB = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        addItem = v.findViewById(R.id.add_item_button);
        deleteItem = v.findViewById(R.id.delete_item_button);
        Button findPlace = v.findViewById(R.id.where_button);
        findPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String what = input.getText().toString().trim();
                String place = itemsDB.searchForPlace(what);
                input.setBackgroundColor(Color.parseColor("#FFFFFF"));
                input.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text

                input.setText(what + " should be placed in: " + place);
            }
        });

        // adding a new thing
        addItem.setOnClickListener(view ->  {
                String whatS = input.getText().toString().trim();
                String placeS = place.getText().toString().trim();
                if ((whatS.length() > 0) && (placeS.length() > 0)) {
                    itemsDB.addItem(whatS, placeS);
                    input.setText("");
                    place.setText("");
                }
        });

        deleteItem.setOnClickListener(view ->  {
                String what = input.getText().toString().trim();
                input.setBackgroundColor(Color.parseColor("#FFFFFF"));
                input.onEditorAction(EditorInfo.IME_ACTION_DONE); //to close the keyboard when done with the text
                if(what.length() > 0) {
                itemsDB.removeItem(what);
                newWhat.setText("");
            }
        });

        return v;
    }
}