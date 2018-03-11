package mobile.com.mobilephonebuyers.menu.view;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.favorite.view.FavoriteListFragment;
import mobile.com.mobilephonebuyers.favorite.view.IFavoriteListFragmentView;
import mobile.com.mobilephonebuyers.manager.SharedPreferenceManager;
import mobile.com.mobilephonebuyers.menu.adapter.MainMenuViewPagerAdapter;
import mobile.com.mobilephonebuyers.menu.presenter.MainMenuPresenter;
import mobile.com.mobilephonebuyers.mobile_list.view.IMobileListFragmentView;
import mobile.com.mobilephonebuyers.mobile_list.view.MobileListFragment;

public class MainMenuActivity extends AppCompatActivity implements IMainMenuView, IFavoriteListFragmentView.FavoriteListFragmentListener, IMobileListFragmentView.MobileListFragmentListener {

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

    @Override
    public void onUpdateMobileListListener() {
        //Update Mobile List
        MainMenuViewPagerAdapter adapter = ((MainMenuViewPagerAdapter) viewpagerMainMenu.getAdapter());
        if (adapter != null) {
            MobileListFragment fragment = (MobileListFragment) adapter.getItem(0);
            if (fragment != null) {
                fragment.updateViewMobileList(SharedPreferenceManager.getInstance().getSortId());
            }
        }
    }

    @Override
    public void onUpdateFavoriteMobileListListener() {
        //Update Favorite List
        MainMenuViewPagerAdapter adapter = ((MainMenuViewPagerAdapter) viewpagerMainMenu.getAdapter());
        if (adapter != null) {
            FavoriteListFragment fragment = (FavoriteListFragment) adapter.getItem(1);
            if (fragment != null) {
                fragment.updateViewFavoriteFragment(SharedPreferenceManager.getInstance().getSortId());
            }
        }
    }

    @Override
    public void updateViewSortMenu() {
        toolbarMainMenu.inflateMenu(R.menu.sort);
        toolbarMainMenu.setOnMenuItemClickListener(onMenuSortItemClickListener);
    }

    @Override
    public void updateViewShowSortMenu() {
        showDialogSort();
    }

    @Override
    public void updateViewSort(int sortId) {
        onUpdateMobileListListener();
        onUpdateFavoriteMobileListListener();
    }

    private void showDialogSort() {
        LayoutInflater inflater = getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.dialog_sort, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.full_screen_dialog));
        builder.setView(dialogLayout);
        final AlertDialog ad = builder.show();

        RadioGroup radioGroupFavorite = dialogLayout.findViewById(R.id.radioGroupFavorite);

        if (SharedPreferenceManager.getInstance().getSortId() == 0) {
            RadioButton radioLowToHigh = dialogLayout.findViewById(R.id.radioLowToHigh);
            radioLowToHigh.toggle();
        } else if (SharedPreferenceManager.getInstance().getSortId() == 1) {
            RadioButton radioHighToLow = dialogLayout.findViewById(R.id.radioHighToLow);
            radioHighToLow.toggle();
        } else if (SharedPreferenceManager.getInstance().getSortId() == 2) {
            RadioButton radioRating = dialogLayout.findViewById(R.id.radioRating);
            radioRating.toggle();
        } else {
            RadioButton radioLowToHigh = dialogLayout.findViewById(R.id.radioLowToHigh);
            radioLowToHigh.toggle();
        }

        radioGroupFavorite.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioLowToHigh:
                        SharedPreferenceManager.getInstance().setSortId(0);
                        mainMenuPresenter.initViewSort(0);
                        break;
                    case R.id.radioHighToLow:
                        SharedPreferenceManager.getInstance().setSortId(1);
                        mainMenuPresenter.initViewSort(1);
                        break;
                    case R.id.radioRating:
                        SharedPreferenceManager.getInstance().setSortId(2);
                        mainMenuPresenter.initViewSort(2);
                        break;
                }
                ad.dismiss();
            }
        });

    }

    private void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        mainMenuPresenter = new MainMenuPresenter(this);
        mainMenuPresenter.initMainMenu();
        mainMenuPresenter.initSortMenu();
    }

    private void setupViewPager(ViewPager viewPager, TabLayout tabsMainMenu) {
        MainMenuViewPagerAdapter adapter = new MainMenuViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MobileListFragment.newInstance(), getString(R.string.menu_mobile_list));
        adapter.addFragment(FavoriteListFragment.newInstance(), getString(R.string.menu_favorite_list));
        viewPager.setAdapter(adapter);
        tabsMainMenu.setupWithViewPager(viewPager);
    }

    Toolbar.OnMenuItemClickListener onMenuSortItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (item.getItemId() == R.id.btnSort) {
                mainMenuPresenter.initShowSortMenu();
                return true;
            }
            return false;
        }
    };

}
