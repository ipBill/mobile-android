package mobile.com.mobilephonebuyers.mobile_list.adapter.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_list.adapter.view.IMobileListAdapterView;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MobileListAdapterPresenter implements IMobileListAdapterPresenter {

    private IMobileListAdapterView mobileListAdapterView;

    public MobileListAdapterPresenter(IMobileListAdapterView mobileListAdapterView) {
        this.mobileListAdapterView = mobileListAdapterView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void updateViewMobileList(List<MobileObject> body) {
        mobileListAdapterView.updateViewMobileList(body);
    }
}
