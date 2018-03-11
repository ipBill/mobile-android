package mobile.com.mobilephonebuyers.mobile_detail.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.parceler.Parcels;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_detail.dao.MobileDetailObject;
import mobile.com.mobilephonebuyers.mobile_detail.interactor.IMobileDetailFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_detail.interactor.MobileDetailFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_detail.view.IMobileDetailFragmentView;
import mobile.com.mobilephonebuyers.mobile_detail.view.MobileDetailFragment;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 12/3/2018 AD.
 */

public class MobileDetailFragmentPresenter implements IMobileDetailFragmentPresenter, IMobileDetailFragmentInteractor.MobileDetailFragmentInteractorListener {

    private IMobileDetailFragmentView mobileDetailFragmentView;
    private IMobileDetailFragmentInteractor mobileDetailFragmentInteractor;
    private MobileObject mobileObject;

    public MobileDetailFragmentPresenter(IMobileDetailFragmentView mobileDetailFragmentView
            , IMobileDetailFragmentInteractor mobileDetailFragmentInteractor) {
        this.mobileDetailFragmentView = mobileDetailFragmentView;
        this.mobileDetailFragmentInteractor = mobileDetailFragmentInteractor;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("mobileObject", Parcels.wrap(mobileObject));
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        mobileObject = Parcels.unwrap(savedInstanceState.getParcelable("mobileObject"));
        mobileDetailFragmentView.updateMobileDetailDesc(mobileObject);
    }

    @Override
    public void initMobileDetailFromLocal(MobileObject mobileObject) {
        this.mobileObject = mobileObject;
        mobileDetailFragmentView.showProgressDialog();
        mobileDetailFragmentInteractor.loadMobileDetailImageFromService(mobileObject.getId(), this);
    }

    @Override
    public void loadMobileDetailImageFromServiceFinished(boolean isSuccess, List<MobileDetailObject> body) {
        if (isSuccess) {
            mobileDetailFragmentView.updateMobileDetailImage(body);
            mobileDetailFragmentView.updateMobileDetailDesc(mobileObject);
        } else {
            mobileDetailFragmentView.showAlertDialogMessageCanNotLoad();
            mobileDetailFragmentView.updateMobileDetailDesc(mobileObject);
        }
        mobileDetailFragmentView.hideProgressDialog();
    }
}
