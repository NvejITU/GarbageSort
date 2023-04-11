package dk.itu.local_garbage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;

public class GarbageSorting extends AppCompatActivity {

    private FragmentManager fm;
    Fragment fragmentUI, fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garbage_sorting);
        ItemsDB.initialize(this);
        fm = getSupportFragmentManager();
        setUpFragments();
    }

    private void setUpFragments() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            fragmentUI= fm.findFragmentById(R.id.container_ui_landscape);
            fragmentList= fm.findFragmentById(R.id.container_list);
            if ((fragmentUI == null) && (fragmentList == null)) {
                Fragment fragmentUI= new UIFragment();
                Fragment fragmentList= new ListFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui_landscape, fragmentUI)
                        .add(R.id.container_list, fragmentList)
                        .commit();
            }
        } else {
            //Orientation portrait
            if (fragmentUI== null && fragmentList == null) {
                fragmentUI= new UIFragment();
                fragmentList = new ListFragment();
                fm.beginTransaction()
                        .add(R.id.container_ui, fragmentUI)
                        .add(R.id.container_list,fragmentList)
                        .commit();
            }
        }

    }
}
