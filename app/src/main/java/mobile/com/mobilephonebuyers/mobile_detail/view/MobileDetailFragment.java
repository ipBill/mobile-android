package mobile.com.mobilephonebuyers.mobile_detail.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.viewpagerindicator.CirclePageIndicator;

import org.parceler.Parcels;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.mobile_detail.adapter.SlidingImageAdapter;
import mobile.com.mobilephonebuyers.mobile_detail.dao.MobileDetailObject;
import mobile.com.mobilephonebuyers.mobile_detail.interactor.MobileDetailFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_detail.presenter.MobileDetailFragmentPresenter;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

public class MobileDetailFragment extends Fragment implements IMobileDetailFragmentView {

    MobileDetailFragmentPresenter detailFragmentPresenter;
    MobileObject mobileDetail;
    private int currentPage = 0;
    private int NUM_PAGES = 0;

    ProgressDialog progressDialog;

    @BindView(R.id.appBarMobileDetail)
    AppBarLayout appBarMobileDetail;

    @BindView(R.id.toolbarMobileDetail)
    Toolbar toolbarMobileDetail;

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    @BindView(R.id.tvTitleDesc)
    TextView tvTitleDesc;

    @BindView(R.id.tvDesc)
    TextView tvDesc;

    @BindView(R.id.indicatorMobileImage)
    CirclePageIndicator indicatorMobileImage;

    @BindView(R.id.viewPagerMobileImage)
    ViewPager viewPagerMobileImage;

    public MobileDetailFragment() {
        super();
    }

    public static MobileDetailFragment newInstance(MobileObject mobileDetail) {
        MobileDetailFragment fragment = new MobileDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("mobileDetail", Parcels.wrap(mobileDetail));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mobile_detail, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @SuppressWarnings("UnusedParameters")
    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        Bundle bundle = getArguments();
        if (bundle != null) {
            mobileDetail = Parcels.unwrap(bundle.getParcelable("mobileDetail"));
        }
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState
        ButterKnife.bind(this, rootView);
        toolbarMobileDetail.setNavigationOnClickListener(navigationOnClickListener);
        detailFragmentPresenter = new MobileDetailFragmentPresenter(this
                , new MobileDetailFragmentInteractor());
        detailFragmentPresenter.initMobileDetailFromLocal(mobileDetail);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        detailFragmentPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
        detailFragmentPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void showAlertDialogMessageCanNotLoad() {
        showAlertDialog(getString(R.string.dialog_fail_server));
    }

    @SuppressLint("CheckResult")
    @Override
    public void updateMobileDetailImage(List<MobileDetailObject> body) {

        NUM_PAGES = body.size();

        SlidingImageAdapter adapter = new SlidingImageAdapter(body, mobileDetail, getContext());
        viewPagerMobileImage.setAdapter(adapter);
        indicatorMobileImage.setViewPager(viewPagerMobileImage);

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                viewPagerMobileImage.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 5000, 5000);

        indicatorMobileImage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }

    @Override
    public void updateMobileDetailDesc(MobileObject mobileObject) {
        tvTitleDesc.setText(String.valueOf(mobileObject.getName() + " " + mobileObject.getBrand()));
        tvDesc.setText(mobileObject.getDescription());
    }


    @Override
    public void showProgressDialog() {
        if (isAdded()) {
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setMessage(getString(R.string.dialog_just_moment_please));
            progressDialog.show();
        }
    }

    @Override
    public void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlertDialog(String message) {
        Context context = getContext();
        if (context != null) {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(context);
            builder.setTitle(getString(R.string.app_name))
                    .setMessage(message)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    View.OnClickListener navigationOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                activity.finish();
            }
        }
    };
}
