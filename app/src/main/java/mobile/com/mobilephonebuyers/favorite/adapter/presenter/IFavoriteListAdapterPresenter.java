package mobile.com.mobilephonebuyers.favorite.adapter.presenter;

import java.util.List;

import mobile.com.mobilephonebuyers.base.BasePresenter;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IFavoriteListAdapterPresenter extends BasePresenter {
    void updateViewMobileFavorite(List<MobileObject> mobileList);

    void updateViewRemoveFavoriteNotSuccessful(int position);

    void updateViewRemoveFavoriteSuccessful(int position);
}
