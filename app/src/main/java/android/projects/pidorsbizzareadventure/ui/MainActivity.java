package android.projects.pidorsbizzareadventure.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.projects.pidorsbizzareadventure.R;
import android.projects.pidorsbizzareadventure.ui.welcome.WelcomePageFragment;
import android.projects.pidorsbizzareadventure.ui.сhallengesList.ListPagefragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    WelcomePageFragment fragmentHome;
    ListPagefragment listFragment;

    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent fromAuth = getIntent();
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.pager);

        fragmentHome = new WelcomePageFragment();
        listFragment = new ListPagefragment();

        PageAdapter adapter = new PageAdapter(getSupportFragmentManager(), getLifecycle());

        adapter.addFragment(fragmentHome);
        adapter.addFragment(listFragment);

        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(adapter);

    }

    public static class PageAdapter extends FragmentStateAdapter {

        private ArrayList<Fragment> arrayList = new ArrayList<>();

        public PageAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }


        public void addFragment(Fragment fragment) {
            arrayList.add(fragment);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // return your fragment that corresponds to this 'position'
            return arrayList.get(position);
        }
    }

}
