package mobile.com.mobilephonebuyers.mobile_detail.view;

import java.util.List;

import mobile.com.mobilephonebuyers.mobile_detail.dao.MobileDetailObject;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 12/3/2018 AD.
 */

public interface IMobileDetailFragmentView {
    void showAlertDialogMessageCanNotLoad();

    void updateMobileDetailImage(List<MobileDetailObject> body);

    void updateMobileDetailDesc(MobileObject mobileObject);

    void showProgressDialog();

    void hideProgressDialog();
}
