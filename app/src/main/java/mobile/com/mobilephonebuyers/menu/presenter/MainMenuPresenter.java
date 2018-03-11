package mobile.com.mobilephonebuyers.menu.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import mobile.com.mobilephonebuyers.menu.view.IMainMenuView;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MainMenuPresenter implements IMainMenuPresenter {

    private IMainMenuView mainMenuView;

    public MainMenuPresenter(IMainMenuView mainMenuView) {
        this.mainMenuView = mainMenuView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void initMainMenu() {
        mainMenuView.updateViewMainMenu();
    }

    @Override
    public void initSortMenu() {
        mainMenuView.updateViewSortMenu();
    }

    @Override
    public void initShowSortMenu() {
        mainMenuView.updateViewShowSortMenu();
    }

    @Override
    public void initViewSort(int sortId) {
        mainMenuView.updateViewSort(sortId);
    }
}
