package com.thetonrifles.recyclergrid;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.thetonrifles.recyclergrid.enums.FragmentEnums;
import com.thetonrifles.recyclergrid.managers.SimpleFragmentManager;

public class TestActivity extends AppCompatActivity implements FragmentA.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        if (savedInstanceState == null)
        {
            SimpleFragmentManager.init(this, R.id.container);
            //getFragmentManager().addOnBackStackChangedListener(this);

            SimpleFragmentManager.changeFragment(FragmentEnums.FRAGMENT_A);
        }
    }

    @Override
    public void onButtonClick()
    {
        SimpleFragmentManager.changeFragment(FragmentEnums.FRAGMENT_C);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
