package mobile.com.mobilephonebuyers.mobile_list.view;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IMobileListFragmentView {
    void updateViewMobileList(List<MobileObject> body);

    void showAlertDialogCanNotLoadService();

    void showProgressDialog();

    void hideProgressDialog();

    interface MobileListFragmentListener {
        void onUpdateFavoriteMobileListListener();
    }
}
