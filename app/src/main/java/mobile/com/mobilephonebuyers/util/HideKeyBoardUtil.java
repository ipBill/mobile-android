package mobile.com.mobilephonebuyers.util;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import mobile.com.mobilephonebuyers.manager.Contextor;

public class HideKeyBoardUtil {

    private static HideKeyBoardUtil instance;

    public static HideKeyBoardUtil getInstance() {
        if (instance == null)
            instance = new HideKeyBoardUtil();
        return instance;
    }

    private Context mContext;

    private HideKeyBoardUtil() {
        mContext = Contextor.getInstance().getContext();
    }

    public void hideKeyBoard(View view) {
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public void showKeyBoard(){
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

}
