package mobile.com.mobilephonebuyers.menu.presenter;

import mobile.com.mobilephonebuyers.base.BasePresenter;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IMainMenuPresenter extends BasePresenter {
    void initMainMenu();

    void initSortMenu();

    void initShowSortMenu();

    void initViewSort(int sortId);
}
