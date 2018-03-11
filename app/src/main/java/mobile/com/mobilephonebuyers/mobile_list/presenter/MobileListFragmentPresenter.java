package mobile.com.mobilephonebuyers.mobile_list.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import mobile.com.mobilephonebuyers.mobile_list.interactor.IMobileListFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_list.view.IMobileListFragmentView;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MobileListFragmentPresenter implements IMobileListFragmentPresenter {

    IMobileListFragmentView mobileListFragmentView;
    IMobileListFragmentInteractor mobileListFragmentInteractor;

    public MobileListFragmentPresenter(IMobileListFragmentView mobileListFragmentView
            , IMobileListFragmentInteractor mobileListFragmentInteractor) {
        this.mobileListFragmentView = mobileListFragmentView;
        this.mobileListFragmentInteractor = mobileListFragmentInteractor;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onDestroyView() {

    }
}
