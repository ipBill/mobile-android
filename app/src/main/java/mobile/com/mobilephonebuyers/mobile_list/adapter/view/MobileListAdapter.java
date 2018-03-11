package mobile.com.mobilephonebuyers.mobile_list.adapter.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MobileListAdapter extends RecyclerView.Adapter<MobileListAdapter.MobileListViewHolder>
        implements IMobileListAdapterView {

    private List<MobileObject> mobileList;
    private Context context;
    public IMobileListAdapterView.MobileListAdapterListener listener;

    public MobileListAdapter() {

    }

    @NonNull
    @Override
    public MobileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mobile_list, parent, false);
        return new MobileListViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MobileListViewHolder holder, int position) {
        MobileObject object = mobileList.get(position);
        setViewHolderMobileList(holder, object);
    }

    @SuppressLint("CheckResult")
    private void setViewHolderMobileList(MobileListViewHolder holder, final MobileObject object) {
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

        if (object.isFavorite()) {
            Glide.with(context)
                    .load(R.drawable.ic_favorite_pressed)
                    .into(holder.ivFavorite);
        } else {
            Glide.with(context)
                    .load(R.drawable.ic_favorite_default)
                    .into(holder.ivFavorite);
        }

        View.OnClickListener onClickFavoriteListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (object.isFavorite()) {
                        listener.onClickFavoritePressed(object, false);
                    } else {
                        listener.onClickFavoritePressed(object, true);
                    }
                }
            }
        };
        holder.ivFavorite.setOnClickListener(onClickFavoriteListener);
    }

    @Override
    public int getItemCount() {
        if (mobileList == null) {
            return 0;
        }
        return mobileList.size();
    }

    @Override
    public void updateViewMobileList(List<MobileObject> body) {
        this.mobileList = body;
        notifyDataSetChanged();
    }

    @Override
    public void updateViewForFavorite(List<MobileObject> mobileObjects) {
        this.mobileList = mobileObjects;
        notifyDataSetChanged();
    }

    public void setOnClickFavoriteListener(IMobileListAdapterView.MobileListAdapterListener listener) {
        this.listener = listener;
    }

    public static class MobileListViewHolder extends RecyclerView.ViewHolder {

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

        @BindView(R.id.ivFavorite)
        ImageView ivFavorite;

        public MobileListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
