package dk.itu.local_garbage;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class UIFragment extends Fragment {
    Button addItem, findItems, deleteItem;
    private TextView itemWhat, itemPlace;

    private static ItemsViewModel item;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v= inflater.inflate(R.layout.fragment_ui, container, false);

        //Text Fields
        itemWhat = v.findViewById(R.id.what_text);
        itemPlace = v.findViewById(R.id.place_text);

        findItems = v.findViewById(R.id.where_button);
        addItem= v.findViewById(R.id.add_item_button);
        deleteItem= v.findViewById(R.id.delete_item_button);

        item = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);


        // adding a new thing
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whatS = itemWhat.getText().toString().trim();
                String whereS = itemPlace.getText().toString().trim();
                if ((whatS.length() > 0) && (whereS.length() > 0)) {
                    item.addItem(whatS, whereS);
                    Toast.makeText(getActivity(), "Added "+itemWhat.getText(),
                            Toast.LENGTH_SHORT).show();
                    itemWhat.setText("");
                    itemPlace.setText("");
                    itemWhat.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    itemPlace.onEditorAction(EditorInfo.IME_ACTION_DONE);
                } else
                    Toast.makeText(getActivity(), "Please type something in the what and where fields", Toast.LENGTH_LONG).show();
            }
        });

        deleteItem.setOnClickListener(view -> {
            if (!itemWhat.getText().toString().trim().isEmpty()) {
                item.removeItem(itemWhat.getText().toString());
                Toast.makeText(getActivity(), "Removed "+itemWhat.getText(),
                        Toast.LENGTH_SHORT).show();
            }else Toast.makeText(getActivity(), "Removed", Toast.LENGTH_LONG).show();
        });

        findItems.setOnClickListener(view -> {
            if (!itemWhat.getText().toString().trim().isEmpty()) {
                String place = item.findPlace(itemWhat.getText().toString().trim());
                String item = itemWhat.getText().toString();
                itemWhat.setText(item + " should be placed in: " + place);
            } else Toast.makeText(getActivity(), "Please type in something in the what field", Toast.LENGTH_LONG).show();
        });

        return v;

    }


}