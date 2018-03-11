package mobile.com.mobilephonebuyers.mobile_detail.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.parceler.Parcels;

import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

public class MobileDetailActivity extends AppCompatActivity {

    private static final String TAG_Fragment = "MobileDetailFragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_detail);
        init(savedInstanceState);
    }

    private void init(Bundle savedInstanceState) {

        Intent intent = getIntent();
        MobileObject mobileObject = Parcels.unwrap(intent.getParcelableExtra("mobileDetail"));

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.containerMobileDetail, MobileDetailFragment.newInstance(mobileObject), TAG_Fragment)
                    .commit();
        }
    }
}
