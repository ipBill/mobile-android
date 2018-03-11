package mobile.com.mobilephonebuyers.mobile_detail.view;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.mobile_detail.dao.MobileDetailObject;
import mobile.com.mobilephonebuyers.mobile_detail.interactor.MobileDetailFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_detail.presenter.MobileDetailFragmentPresenter;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

public class MobileDetailFragment extends Fragment implements IMobileDetailFragmentView {

    MobileDetailFragmentPresenter detailFragmentPresenter;
    MobileObject mobileDetail;

    ProgressDialog progressDialog;

    @BindView(R.id.appBarMobileDetail)
    AppBarLayout appBarMobileDetail;

    @BindView(R.id.toolbarMobileDetail)
    Toolbar toolbarMobileDetail;

    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;

    @BindView(R.id.ivMobile)
    ImageView ivMobile;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvTitleDesc)
    TextView tvTitleDesc;

    @BindView(R.id.tvDesc)
    TextView tvDesc;

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

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.error(R.drawable.ic_error);
        requestOptions.placeholder(R.drawable.ic_loading);

        Glide.with(this)
                .load(body.get(0).getUrl())
                .apply(requestOptions)
                .into(ivMobile);

    }

    @Override
    public void updateMobileDetailDesc(MobileObject mobileObject) {
        tvTitle.setText(String.valueOf(getString(R.string.text_price) + " " + mobileObject.getPrice()
                + " " + getString(R.string.text_rating) + " " + mobileObject.getRating()));
        tvTitleDesc.setText(String.valueOf(mobileObject.getName() + " " + mobileObject.getBrand()));
        tvDesc.setText(mobileObject.getDescription());
    }


    @Override
    public void showProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.dialog_just_moment_please));
        progressDialog.show();
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
