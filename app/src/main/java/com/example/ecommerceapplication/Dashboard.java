package com.example.ecommerceapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {
    TabLayout tabLayout;
    private ViewPager mViewPager;
    ProgressDialog dialog;
    FloatingActionButton fablog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        mViewPager = (ViewPager) findViewById(R.id.container);
        setUpViewPager(mViewPager);
        tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        fablog = findViewById(R.id.fab);

        fablog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Dashboard.this);
                builder.setTitle("Logout Confirmation!!!").
                        setMessage("You sure, you want to logout?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(Dashboard.this,Login.class);
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });
    }

    private void setUpViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter=new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Tshirts(),"T-Shirts");
        adapter.addFragment(new Toys(),"Toys");
        adapter.addFragment(new Cups(),"Cups");
        adapter.addFragment(new Cart(),"Cart");
        viewPager.setAdapter(adapter);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList=new ArrayList<>();
        private final List<String> stringList=new ArrayList<>();
        public void addFragment(Fragment fragment,String title){
            fragmentList.add(fragment);
            stringList.add(title);
        }
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "T-Shirts";
                case 1:
                    return "Toys";
                case 2:
                    return "Cups";
                case 3:
                    return "Cart";
                default:
                    return null;
            }
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position){
                case 0:
                    Tshirts tshirts=new Tshirts();
                    return  tshirts;
                case 1:
                    Toys toys=new Toys();
                    return toys;
                case 2:
                    Cups cups=new Cups();
                    return  cups;
                case 3:
                    Cart cart=new Cart();
                    return  cart;
                default:
                    return  null;
            }
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
