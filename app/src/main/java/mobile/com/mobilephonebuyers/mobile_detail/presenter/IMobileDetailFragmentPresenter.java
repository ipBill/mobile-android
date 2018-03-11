package mobile.com.mobilephonebuyers.mobile_detail.presenter;

import mobile.com.mobilephonebuyers.base.BasePresenter;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 12/3/2018 AD.
 */

public interface IMobileDetailFragmentPresenter extends BasePresenter {
    void initMobileDetailFromLocal(MobileObject mobileObject);
}
