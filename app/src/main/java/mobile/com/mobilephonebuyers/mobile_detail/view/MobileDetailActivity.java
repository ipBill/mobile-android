package mobile.com.mobilephonebuyers.mobile_detail.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mobile.com.mobilephonebuyers.R;

public class MobileDetailActivity extends AppCompatActivity {

    private static final String TAG_Fragment = "MobileDetailFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_detail);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.containerMobileDetail, MobileDetailFragment.newInstance(), TAG_Fragment)
                    .commit();
        }
    }
}
