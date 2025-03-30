package com.example.fragment_tablelayout_viewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new NeworderFragment();
            case 1:
                return new NeworderFragment();
            case 2:
                return new NeworderFragment();
            case 3:
                return new NeworderFragment();
            case 4:
                return new NeworderFragment();
            default:
                return new NeworderFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 1;
    }
}
