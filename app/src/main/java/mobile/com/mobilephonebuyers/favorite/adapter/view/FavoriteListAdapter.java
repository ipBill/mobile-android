package mobile.com.mobilephonebuyers.favorite.adapter.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 11/3/2018 AD.
 */

public class FavoriteListAdapter extends RecyclerView.Adapter<FavoriteListAdapter.FavoriteViewHolder>
        implements IFavoriteListAdapterView {

    private List<MobileObject> mobileList;
    private Context context;
    private IFavoriteListAdapterView.FavoriteListAdapterViewListener listener;

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mobile_favorite, parent, false);
        return new FavoriteViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        MobileObject object = mobileList.get(position);
        setViewHolderMobileList(holder, object);
    }

    @Override
    public int getItemCount() {
        if (mobileList == null) {
            return 0;
        }
        return mobileList.size();
    }

    @Override
    public void updateViewMobileFavorite(List<MobileObject> mobileList) {
        this.mobileList = mobileList;
        notifyDataSetChanged();
    }

    @Override
    public void updateViewRemoveFavoriteSuccessful(int position) {
        notifyItemRemoved(position);
    }

    @Override
    public void updateViewRemoveFavoriteNotSuccessful(int position) {
        notifyItemRemoved(position + 1);
        notifyItemRangeChanged(position, getItemCount());
    }

    @SuppressLint("CheckResult")
    private void setViewHolderMobileList(FavoriteListAdapter.FavoriteViewHolder holder, final MobileObject object) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.error(R.drawable.ic_error);
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.centerCrop();

        holder.tvTitle.setText(object.getName());
        holder.tvDesc.setText(object.getDescription());
        Glide.with(context)
                .load(object.getThumbImageURL())
                .apply(requestOptions)
                .into(holder.ivMobile);

        holder.tvPrice.setText(String.valueOf(context.getString(R.string.text_price) + object.getPrice()));
        holder.tvRating.setText(String.valueOf(context.getString(R.string.text_rating) + object.getRating()));

        View.OnClickListener onClickMobileDetailListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickMobileDetailListener(object);
                }
            }
        };

        holder.cvMobile.setOnClickListener(onClickMobileDetailListener);
    }

    public void setOnClickMobileDetailListener(IFavoriteListAdapterView.FavoriteListAdapterViewListener listener) {
        this.listener = listener;
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cvMobile)
        CardView cvMobile;

        @BindView(R.id.ivMobile)
        ImageView ivMobile;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvDesc)
        TextView tvDesc;

        @BindView(R.id.tvPrice)
        TextView tvPrice;

        @BindView(R.id.tvRating)
        TextView tvRating;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
