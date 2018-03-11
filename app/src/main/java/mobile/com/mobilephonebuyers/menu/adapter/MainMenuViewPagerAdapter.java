package mobile.com.mobilephonebuyers.menu.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MainMenuViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragmentMenuList = new ArrayList<>();
    private final List<String> menuTitleList = new ArrayList<>();

    public MainMenuViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentMenuList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentMenuList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return menuTitleList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentMenuList.add(fragment);
        menuTitleList.add(title);
    }
}
