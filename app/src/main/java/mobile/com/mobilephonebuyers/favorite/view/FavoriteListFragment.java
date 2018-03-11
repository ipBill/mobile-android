package mobile.com.mobilephonebuyers.favorite.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.favorite.adapter.presenter.FavoriteListAdapterPresenter;
import mobile.com.mobilephonebuyers.favorite.adapter.view.FavoriteListAdapter;
import mobile.com.mobilephonebuyers.favorite.presenter.FavoriteListFragmentPresenter;
import mobile.com.mobilephonebuyers.manager.SharedPreferenceManager;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

public class FavoriteListFragment extends Fragment implements IFavoriteListFragmentView {

    FavoriteListFragmentPresenter favoriteListFragmentPresenter;
    FavoriteListAdapterPresenter favoriteListAdapterPresenter;
    FavoriteListAdapter adapter;

    @BindView(R.id.swipeLayoutFavorite)
    SwipeRefreshLayout swipeLayoutFavorite;

    @BindView(R.id.recyclerViewFavorite)
    RecyclerView recyclerViewFavorite;

    public FavoriteListFragment() {
        super();
    }

    public static FavoriteListFragment newInstance() {
        FavoriteListFragment fragment = new FavoriteListFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_favorite_list, container, false);
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

        adapter = new FavoriteListAdapter();
        favoriteListFragmentPresenter = new FavoriteListFragmentPresenter(this);
        favoriteListAdapterPresenter = new FavoriteListAdapterPresenter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerViewFavorite.setLayoutManager(manager);
        recyclerViewFavorite.setAdapter(adapter);
        initSwipeRecyclerView();

        if (savedInstanceState == null) {
            favoriteListFragmentPresenter.loadMobileFavorite(SharedPreferenceManager.getInstance().getSortId());
        }
        swipeLayoutFavorite.setOnRefreshListener(onRefreshListener);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        favoriteListFragmentPresenter.onSaveInstanceState(outState);
        favoriteListAdapterPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
        // Save Instance (Fragment level's variables) State here
    }

    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance (Fragment level's variables) State here
        favoriteListFragmentPresenter.onRestoreInstanceState(savedInstanceState);
        favoriteListAdapterPresenter.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void updateViewMobileFavorite(List<MobileObject> mobileList) {
        hideSwipeLayout();
        favoriteListAdapterPresenter.updateViewMobileFavorite(mobileList);
    }

    private void initSwipeRecyclerView() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                final int position = viewHolder.getAdapterPosition(); //get position which is swipe
                if (swipeDir == ItemTouchHelper.LEFT) {    //if swipe left
                    showDialogConfirmDelete(position);
                }
            }

            private void showDialogConfirmDelete(final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(R.string.dialog_confirm_delete);
                builder.setPositiveButton(R.string.btn_remove, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        favoriteListFragmentPresenter.removeFavorite(position);
                    }
                }).setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        favoriteListAdapterPresenter.updateViewRemoveFavoriteNotSuccessful(position);
                    }
                }).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewFavorite);
    }

    @Override
    public void updateViewRemoveFavoriteSuccessful(int position) {
        favoriteListAdapterPresenter.updateViewRemoveFavoriteSuccessful(position);
        updateViewRemoveFavoriteMobileList();
    }

    @Override
    public void updateViewRemoveFavoriteNotSuccessful(int position) {
        favoriteListAdapterPresenter.updateViewRemoveFavoriteNotSuccessful(position);
    }

    @Override
    public void updateViewMobileFavoriteEmpty(List<MobileObject> mobileList) {
        favoriteListAdapterPresenter.updateViewMobileFavorite(mobileList);
    }

    private void hideSwipeLayout() {
        if (swipeLayoutFavorite != null && swipeLayoutFavorite.isRefreshing()) {
            swipeLayoutFavorite.setRefreshing(false);
        }
    }

    private void updateViewRemoveFavoriteMobileList() {
        FavoriteListFragmentListener listener = (FavoriteListFragmentListener) getActivity();
        if (listener != null) {
            listener.onUpdateMobileListListener();
        }
    }

    SwipeRefreshLayout.OnRefreshListener onRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            favoriteListFragmentPresenter.loadMobileFavorite(SharedPreferenceManager.getInstance().getSortId());
        }
    };

    public void updateViewFavoriteFragment(int sortId) {
        favoriteListFragmentPresenter.loadMobileFavorite(SharedPreferenceManager.getInstance().getSortId());
    }
}
