package mobile.com.mobilephonebuyers.favorite.adapter.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

import mobile.com.mobilephonebuyers.favorite.adapter.view.IFavoriteListAdapterView;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class FavoriteListAdapterPresenter implements IFavoriteListAdapterPresenter {

    private IFavoriteListAdapterView favoriteListAdapterView;

    public FavoriteListAdapterPresenter(IFavoriteListAdapterView favoriteListAdapterView) {
        this.favoriteListAdapterView = favoriteListAdapterView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void updateViewMobileFavorite(List<MobileObject> mobileList) {
        favoriteListAdapterView.updateViewMobileFavorite(mobileList);
    }

    @Override
    public void updateViewRemoveFavoriteNotSuccessful(int position) {
        favoriteListAdapterView.updateViewRemoveFavoriteNotSuccessful(position);
    }

    @Override
    public void updateViewRemoveFavoriteSuccessful(int position) {
        favoriteListAdapterView.updateViewRemoveFavoriteSuccessful(position);
    }
}
