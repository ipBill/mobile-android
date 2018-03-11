package mobile.com.mobilephonebuyers.menu.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.favorite.view.FavoriteListFragment;
import mobile.com.mobilephonebuyers.menu.adapter.MainMenuViewPagerAdapter;
import mobile.com.mobilephonebuyers.menu.presenter.MainMenuPresenter;
import mobile.com.mobilephonebuyers.mobile_list.view.MobileListFragment;

public class MainMenuActivity extends AppCompatActivity implements IMainMenuView {

    MainMenuPresenter mainMenuPresenter;

    @BindView(R.id.containerMainMenu)
    CoordinatorLayout containerMainMenu;

    @BindView(R.id.appbarMainMenu)
    AppBarLayout appbarMainMenu;

    @BindView(R.id.toolbarMainMenu)
    Toolbar toolbarMainMenu;

    @BindView(R.id.tabsMainMenu)
    TabLayout tabsMainMenu;

    @BindView(R.id.viewpagerMainMenu)
    ViewPager viewpagerMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        init(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        mainMenuPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mainMenuPresenter.onRestoreInstanceState(savedInstanceState);
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void updateViewMainMenu() {
        setupViewPager(viewpagerMainMenu, tabsMainMenu);
    }

    private void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mainMenuPresenter = new MainMenuPresenter(this);
        mainMenuPresenter.initMainMenu();
    }

    private void setupViewPager(ViewPager viewPager, TabLayout tabsMainMenu) {
        MainMenuViewPagerAdapter adapter = new MainMenuViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MobileListFragment.newInstance(), getString(R.string.menu_mobile_list));
        adapter.addFragment(FavoriteListFragment.newInstance(), getString(R.string.menu_favorite_list));
        viewPager.setAdapter(adapter);
        tabsMainMenu.setupWithViewPager(viewPager);
    }

}
