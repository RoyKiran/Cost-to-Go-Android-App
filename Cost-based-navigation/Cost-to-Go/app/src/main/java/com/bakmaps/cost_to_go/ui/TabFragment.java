package com.bakmaps.cost_to_go.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bakmaps.cost_to_go.ui.Google_Map_Cost;
import com.bakmaps.cost_to_go.ui.ListRoutes;
import com.bakmaps.cost_to_go.ui.M_Indicator;
import com.bakmaps.cost_to_go.R;

public class TabFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int main_pg_items = 2;
    public static int upslide_pg_items = 3;
    public int type;
    int tabIcons[] = {R.drawable.driving2, R.drawable.publictransport, R.drawable.walk1};

    public TabFragment(){}

    public TabFragment(int type) {
        this.type = type;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.tab_activity, null);
        tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.viewpager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new TabsAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
                if(type==1){
                    // setupTabIcons;
                    tabLayout.getTabAt(0).setIcon(tabIcons[0]);
                    tabLayout.getTabAt(1).setIcon(tabIcons[1]);
                    tabLayout.getTabAt(2).setIcon(tabIcons[2]);
                }
            }
        });


        return x;
    }

    class TabsAdapter extends FragmentPagerAdapter {

        public TabsAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */
        @Override
        public Fragment getItem(int position) {
            if (type == 0) {
                switch (position) {
                    case 0:
                        return new M_Indicator();
                    case 1:
                        return new Google_Map_Cost();
                }
            }
            else if(type == 1){
                /*switch (position) {
                    case 0:
                        return new ListRoutes(0);
                    case 1:
                        return new ListRoutes(1);
                    case 2:
                        return new ListRoutes();
                }*/
                return new ListRoutes(position);
            }
            return null;
        }

        @Override
        public int getCount() {
            if (type==0)
                return main_pg_items;
            else
                return upslide_pg_items;
        }

        /**
         * This method returns the title of the tab according to the position.
         */
        @Override
        public CharSequence getPageTitle(int position) {
            if(type == 0){
            switch (position) {
                case 0:
                    return "TRANSIT";
                case 1:
                    return "NAVIGATION";
            }}
            else if(type == 1){
                /*switch (position) {
                    case 0:
                        //return "Driving";
                        return "";
                    case 1:
                       // return "public transient" ;
                        return "";
                    case 2:
                       // return "walking";
                        return "";
                }*/
                return "";
            }

            return null;
        }
    }

}