package mobile.com.mobilephonebuyers.favorite.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.parceler.Parcels;

import java.util.List;

import mobile.com.mobilephonebuyers.favorite.view.IFavoriteListFragmentView;
import mobile.com.mobilephonebuyers.manager.RealmManager;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class FavoriteListFragmentPresenter implements IFavoriteListFragmentPresenter {

    private List<MobileObject> mobileList;
    private IFavoriteListFragmentView favoriteListFragmentView;

    public FavoriteListFragmentPresenter(IFavoriteListFragmentView favoriteListFragmentView) {
        this.favoriteListFragmentView = favoriteListFragmentView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("mobileList", Parcels.wrap(mobileList));
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        mobileList = Parcels.unwrap(savedInstanceState.getParcelable("mobileList"));
        favoriteListFragmentView.updateViewMobileFavorite(mobileList);
    }

    @Override
    public void loadMobileFavorite() {
        List<MobileObject> mobileList = RealmManager.getInstance().loadMobileListFavoriteFromLocal(true);
        if (mobileList != null && !mobileList.isEmpty()) {
            this.mobileList = mobileList;
            favoriteListFragmentView.updateViewMobileFavorite(mobileList);
        } else {
            favoriteListFragmentView.updateViewMobileFavoriteEmpty(mobileList);
        }
    }

    @Override
    public void removeFavorite(int position) {
        boolean isSuccess = RealmManager.getInstance().updateFavoriteToLocal(mobileList.get(position), false);
        if(isSuccess) {
            loadMobileFavorite();
            favoriteListFragmentView.updateViewRemoveFavoriteSuccessful(position);
        }else {
            favoriteListFragmentView.updateViewRemoveFavoriteNotSuccessful(position);
        }
    }
}
