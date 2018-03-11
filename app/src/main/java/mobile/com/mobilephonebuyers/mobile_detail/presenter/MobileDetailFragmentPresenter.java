package mobile.com.mobilephonebuyers.mobile_detail.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import mobile.com.mobilephonebuyers.mobile_detail.interactor.IMobileDetailFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_detail.interactor.MobileDetailFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_detail.view.IMobileDetailFragmentView;
import mobile.com.mobilephonebuyers.mobile_detail.view.MobileDetailFragment;

/**
 * Created by bill on 12/3/2018 AD.
 */

public class MobileDetailFragmentPresenter implements IMobileDetailFragmentPresenter {

    private IMobileDetailFragmentView mobileDetailFragmentPresenter;
    private IMobileDetailFragmentInteractor mobileDetailFragmentInteractor;

    public MobileDetailFragmentPresenter(IMobileDetailFragmentView mobileDetailFragmentPresenter
            , IMobileDetailFragmentInteractor mobileDetailFragmentInteractor) {
        this.mobileDetailFragmentPresenter = mobileDetailFragmentPresenter;
        this.mobileDetailFragmentInteractor = mobileDetailFragmentInteractor;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

    }


}
