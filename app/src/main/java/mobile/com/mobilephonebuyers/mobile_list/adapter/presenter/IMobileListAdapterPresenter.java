package mobile.com.mobilephonebuyers.mobile_list.adapter.presenter;

import java.util.List;

import mobile.com.mobilephonebuyers.base.BasePresenter;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public interface IMobileListAdapterPresenter extends BasePresenter {
    void updateViewMobileList(List<MobileObject> body);
}
