package mobile.com.mobilephonebuyers.favorite.presenter;

import mobile.com.mobilephonebuyers.base.BasePresenter;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IFavoriteListFragmentPresenter extends BasePresenter {
    void loadMobileFavorite(int sortId);

    void removeFavorite(int position);
}
