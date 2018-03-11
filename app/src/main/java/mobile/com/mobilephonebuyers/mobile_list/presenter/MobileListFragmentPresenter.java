package mobile.com.mobilephonebuyers.mobile_list.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import org.parceler.Parcels;

import java.util.List;

import mobile.com.mobilephonebuyers.manager.RealmManager;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;
import mobile.com.mobilephonebuyers.mobile_list.interactor.IMobileListFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_list.view.IMobileListFragmentView;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class MobileListFragmentPresenter implements IMobileListFragmentPresenter, IMobileListFragmentInteractor.LoadMobileListListener {

    private IMobileListFragmentView mobileListFragmentView;
    private IMobileListFragmentInteractor mobileListFragmentInteractor;
    private List<MobileObject> dao;

    public MobileListFragmentPresenter(IMobileListFragmentView mobileListFragmentView
            , IMobileListFragmentInteractor mobileListFragmentInteractor) {
        this.mobileListFragmentView = mobileListFragmentView;
        this.mobileListFragmentInteractor = mobileListFragmentInteractor;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelable("dao", Parcels.wrap(dao));
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        dao = Parcels.unwrap(savedInstanceState.getParcelable("dao"));
        mobileListFragmentView.updateViewMobileList(dao);
    }

    @Override
    public void loadMobileList() {
        mobileListFragmentView.showProgressDialog();
        mobileListFragmentInteractor.loadMobileListFromService(this);
    }

    @Override
    public void loadMobileListFromServiceFinished(boolean isSuccess, List<MobileObject> dao) {
        if (isSuccess) {
            RealmManager.getInstance().insertOrUpdateMobileList(dao);
            loadMobileListFromLocal();
        } else {
            loadMobileListFromLocal();
        }
        mobileListFragmentView.hideProgressDialog();
    }

    private void loadMobileListFromLocal() {
        List<MobileObject> dao = RealmManager.getInstance().loadMobileListFromLocal();
        if (dao != null) {
            this.dao = dao;
            mobileListFragmentView.updateViewMobileList(dao);
        } else {
            mobileListFragmentView.showAlertDialogCanNotLoadService();
        }
    }

}
