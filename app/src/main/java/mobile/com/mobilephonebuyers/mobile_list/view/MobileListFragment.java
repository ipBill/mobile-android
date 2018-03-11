package mobile.com.mobilephonebuyers.mobile_list.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.mobile_list.adapter.presenter.MobileListAdapterPresenter;
import mobile.com.mobilephonebuyers.mobile_list.adapter.view.IMobileListAdapterView;
import mobile.com.mobilephonebuyers.mobile_list.adapter.view.MobileListAdapter;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;
import mobile.com.mobilephonebuyers.mobile_list.interactor.MobileListFragmentInteractor;
import mobile.com.mobilephonebuyers.mobile_list.presenter.MobileListFragmentPresenter;

public class MobileListFragment extends Fragment implements IMobileListFragmentView {

    @BindView(R.id.swipeLayoutMobileList)
    SwipeRefreshLayout swipeLayoutMobileList;

    @BindView(R.id.recyclerViewMobileList)
    RecyclerView recyclerViewMobileList;

    ProgressDialog progressDialog;

    MobileListFragmentPresenter mobileListPresenter;
    MobileListAdapterPresenter mobileListAdapterPresenter;
    MobileListAdapter adapter;

    public MobileListFragment() {
        super();
    }

    public static MobileListFragment newInstance() {
        MobileListFragment fragment = new MobileListFragment();
        Bundle args = new Bundle();
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
        View rootView = inflater.inflate(R.layout.fragment_mobile_list, container, false);
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
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // Note: State of variable initialized here could not be saved
        //       in onSavedInstanceState

        ButterKnife.bind(this, rootView);
        mobileListPresenter = new MobileListFragmentPresenter(this, new MobileListFragmentInteractor());
        adapter = new MobileListAdapter();
        mobileListAdapterPresenter = new MobileListAdapterPresenter(adapter);
        adapter.setOnClickFavoriteListener(onClickFavoriteListener);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerViewMobileList.setLayoutManager(manager);
        recyclerViewMobileList.setAdapter(adapter);
        if (savedInstanceState == null) {
            mobileListPresenter.loadMobileList();
        }
        swipeLayoutMobileList.setOnRefreshListener(onRefreshListener);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        mobileListPresenter.onSaveInstanceState(outState);
        mobileListAdapterPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
        mobileListPresenter.onRestoreInstanceState(savedInstanceState);
        mobileListAdapterPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void updateViewMobileList(List<MobileObject> body) {
        if (swipeLayoutMobileList != null && swipeLayoutMobileList.isRefreshing()) {
            swipeLayoutMobileList.setRefreshing(false);
        }
        mobileListAdapterPresenter.updateViewMobileList(body);
    }

    @Override
    public void showAlertDialogCanNotLoadService() {
        showAlertDialog(getString(R.string.dialog_fail_server));
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

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mobileListPresenter.loadMobileList();
        }
    };

    IMobileListAdapterView.MobileListAdapterListener onClickFavoriteListener = new IMobileListAdapterView.MobileListAdapterListener() {
        @Override
        public void onClickFavoritePressed(MobileObject mobileObject, boolean isFavorite) {
            mobileListAdapterPresenter.saveFavoriteToLocal(mobileObject, isFavorite);
        }
    };
}
