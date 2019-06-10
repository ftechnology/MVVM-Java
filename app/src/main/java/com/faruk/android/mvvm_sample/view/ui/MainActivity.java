package com.faruk.android.mvvm_sample.view.ui;

import android.os.Bundle;
import android.widget.Toast;

import com.faruk.android.mvvm_sample.R;
import com.faruk.android.mvvm_sample.service.model.Data;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            DataListFragment fragment = new DataListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, DataListFragment.TAG).commit();
        }
    }

    /** Shows the item title */
    public void show(Data data) {
        Toast.makeText(this, data.title, Toast.LENGTH_LONG).show();
    }

    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}
