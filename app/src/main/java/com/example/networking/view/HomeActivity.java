package com.example.networking.view;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.networking.R;
import com.example.networking.view.feeds.fragments.ExportPromiseFragment;
import com.example.networking.view.feeds.fragments.ImportPromiseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    // map {Tag: Fragment}
    private HashMap<String, Fragment> fragmentMap = null;

    private final String EXPORT_PROMISE_FRAGMENT_TAG = "export_promise_feed";
    private final String IMPORT_PROMISE_FRAGMENT_TAG = "import_promise_feed";
    private final String NEW_PROMISE_FRAGMENT_TAG = "new_promise_fragment";
    private final String PROFILE_FRAGMENT_TAG = "profile_fragment";

    private FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentMap = new HashMap<>();
        if (savedInstanceState == null) {
            fragmentMap.put(EXPORT_PROMISE_FRAGMENT_TAG, null);
            fragmentMap.put(IMPORT_PROMISE_FRAGMENT_TAG, null);
            fragmentMap.put(NEW_PROMISE_FRAGMENT_TAG, null);
            fragmentMap.put(PROFILE_FRAGMENT_TAG, null);
            if (getIntent().getExtras() == null) {
                switchToAnotherFragment(EXPORT_PROMISE_FRAGMENT_TAG);
            } else {
                navigation.setSelectedItemId(R.id.bottom_nav_import_promises);
                // Clear NECESSARILY, otherwise it will problems with screen rotation
                clearAllExtras();
            }
        } else {
            fragmentMap.put(EXPORT_PROMISE_FRAGMENT_TAG, getSupportFragmentManager().getFragment(savedInstanceState, EXPORT_PROMISE_FRAGMENT_TAG));
            fragmentMap.put(IMPORT_PROMISE_FRAGMENT_TAG, getSupportFragmentManager().getFragment(savedInstanceState, IMPORT_PROMISE_FRAGMENT_TAG));
            fragmentMap.put(NEW_PROMISE_FRAGMENT_TAG, getSupportFragmentManager().getFragment(savedInstanceState, NEW_PROMISE_FRAGMENT_TAG));
            fragmentMap.put(PROFILE_FRAGMENT_TAG, getSupportFragmentManager().getFragment(savedInstanceState, PROFILE_FRAGMENT_TAG));
            if (getIntent().getExtras() != null) {
                navigation.setSelectedItemId(R.id.bottom_nav_import_promises);
                // Clear NECESSARILY, otherwise it will problems with screen rotation
                clearAllExtras();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        for (HashMap.Entry<String, Fragment> entry : fragmentMap.entrySet() ) {
            if (entry.getKey() != null && entry.getValue() != null) {
                getSupportFragmentManager().putFragment(outState, entry.getKey(), entry.getValue());
            }
        }
    }

    public void switchToAnotherFragment(String fragmentTag) {
        if (!fragmentMap.containsKey(fragmentTag)) {
            System.out.println("WHY THIS FRAGMENT IS NOT ON MAP???");
            return;
        }
        Fragment newFragment = fragmentMap.get(fragmentTag);
        if (newFragment == null) {
            System.out.println("NULL_FRAGMENT: " + fragmentTag);
            switch (fragmentTag) {
                case EXPORT_PROMISE_FRAGMENT_TAG:
                    newFragment = new ExportPromiseFragment();
                    break;
                case IMPORT_PROMISE_FRAGMENT_TAG:
                    newFragment = new ImportPromiseFragment();
                    break;
                case NEW_PROMISE_FRAGMENT_TAG:
                    newFragment = new PromiseCreaterFragment();
                    break;
                case PROFILE_FRAGMENT_TAG:
                    newFragment = new ProfileFragment();
                    break;
                default:
                    System.out.println("WHY THERE IS NOT FRAGMENT CLASS FOR THIS TAG???");
                    return;
            }
            fragmentMap.put(fragmentTag, newFragment);
        } else {
            System.out.println("FRAGMENT EXISTS");
        }

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (fm.findFragmentByTag(fragmentTag) == null) {
            System.out.println("ADDING FRAGMENT" + fragmentTag);
            fragmentTransaction.add(R.id.content_fragment, newFragment, fragmentTag);
        }

        for (Fragment fragment : fm.getFragments()) {
            if (fragment != null && fragment.isVisible()) {
                fragmentTransaction.hide(fragment);
                System.out.println("HIDING FRAGMENT:" + fragment.getTag());
            }
        }

        fragmentTransaction.show(newFragment).commit();
        System.out.println("REPLACE DONE, NEW FRAGMENT:" + fragmentTag);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.bottom_nav_export_promises:
                    switchToAnotherFragment(EXPORT_PROMISE_FRAGMENT_TAG);
                    return true;

                case R.id.bottom_nav_import_promises:
                    switchToAnotherFragment(IMPORT_PROMISE_FRAGMENT_TAG);
                    return true;

                case R.id.botton_nav_new_promise:
                    switchToAnotherFragment(NEW_PROMISE_FRAGMENT_TAG);
                    return true;

                case R.id.bottom_nav_profile:
                    switchToAnotherFragment(PROFILE_FRAGMENT_TAG);
                    return true;
            }
            return false;
        }
    };

    public void switchToFeed() {
        ((BottomNavigationView) findViewById(R.id.bottom_navigation_menu)).
                setSelectedItemId(R.id.bottom_nav_export_promises);
        switchToAnotherFragment(EXPORT_PROMISE_FRAGMENT_TAG);
    }

    public void clearAllExtras() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                getIntent().removeExtra(key);
            }
        }
    }
}