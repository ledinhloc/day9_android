package com.example.fragment_tablelayout_viewpager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.fragment_tablelayout_viewpager2.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewPager2Adapter viewPager2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setSupportActionBar(binding.toolBar);

        FloatingActionButton fab = binding.fabAction;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Xac nhan"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Lay hang"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Dang giao"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Danh gia"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Huy"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager2Adapter = new ViewPager2Adapter(fragmentManager, getLifecycle());
        binding.viewPager2.setAdapter(viewPager2Adapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager2.setCurrentItem(tab.getPosition());
                changeFabIcon(tab.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position));
            }
        });
    }

    private void changeFabIcon(final int index) {
        binding.fabAction.hide();
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void run() {
                switch (index){
                    case 0:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_chat_24));
                        break;
                    case 1:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_chat_24));
                        break;
                    case 2:
                        binding.fabAction.setImageDrawable(getDrawable(R.drawable.ic_baseline_chat_24));
                        break;
                }
                binding.fabAction.show();
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuSearch) {
            Toast.makeText(this, "Bạn đang chọn search", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuNewGroup) {
            Toast.makeText(this, "Bạn đang chọn more", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuBroadcast) {
            Toast.makeText(this, "Bạn đang chọn broadcast", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuWeb) {
            Toast.makeText(this, "Bạn đang chọn web", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuMessage) {
            Toast.makeText(this, "Bạn đang chọn message", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.menuSetting) {
            Toast.makeText(this, "Bạn đang chọn setting", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }
}