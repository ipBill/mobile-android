package mobile.com.mobilephonebuyers.mobile_detail.interactor;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_detail.dao.MobileDetailObject;
import mobile.com.mobilephonebuyers.mobile_detail.presenter.MobileDetailFragmentPresenter;

/**
 * Created by bill on 12/3/2018 AD.
 */

public interface IMobileDetailFragmentInteractor {
    void loadMobileDetailImageFromService(int id
            , IMobileDetailFragmentInteractor.MobileDetailFragmentInteractorListener listener);

    interface MobileDetailFragmentInteractorListener {
        void loadMobileDetailImageFromServiceFinished(boolean isSuccess, List<MobileDetailObject> body);
    }
}
