package mobile.com.mobilephonebuyers.mobile_list.interactor;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IMobileListFragmentInteractor {

    void loadMobileListFromService(int sortId, LoadMobileListListener listener);

    interface LoadMobileListListener {

        void loadMobileListFromServiceFinished(boolean isSuccess, int sortId, List<MobileObject> body);
    }
}
