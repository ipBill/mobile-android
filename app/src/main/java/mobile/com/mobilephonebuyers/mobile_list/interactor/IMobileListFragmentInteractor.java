package mobile.com.mobilephonebuyers.mobile_list.interactor;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;
import mobile.com.mobilephonebuyers.mobile_list.presenter.MobileListFragmentPresenter;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IMobileListFragmentInteractor {

    void loadMobileListFromService(LoadMobileListListener listener);

    interface LoadMobileListListener {

        void loadMobileListFromServiceFinished(boolean isSuccess, List<MobileObject> body);
    }
}
