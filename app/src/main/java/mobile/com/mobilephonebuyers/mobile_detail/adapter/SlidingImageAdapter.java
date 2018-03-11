package mobile.com.mobilephonebuyers.mobile_detail.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import mobile.com.mobilephonebuyers.R;
import mobile.com.mobilephonebuyers.mobile_detail.dao.MobileDetailObject;
import mobile.com.mobilephonebuyers.mobile_list.dao.MobileObject;

/**
 * Created by bill on 12/03/2018
 */

public class SlidingImageAdapter extends PagerAdapter {

    private List<MobileDetailObject> mobileDetailList;
    private MobileObject mobileObject;
    private LayoutInflater inflater;
    private Context context;

    public SlidingImageAdapter(List<MobileDetailObject> mobileDetailList, MobileObject mobileObject, Context context) {
        this.context = context;
        this.mobileDetailList = mobileDetailList;
        this.mobileObject = mobileObject;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (mobileDetailList == null) {
            return 0;
        }
        return mobileDetailList.size();
    }

    @SuppressLint("CheckResult")
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View imageLayout = inflater.inflate(R.layout.item_mobile_image, container, false);

        assert imageLayout != null;
        final ImageView imageView = imageLayout.findViewById(R.id.ivMobile);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.error(R.drawable.ic_error);
        requestOptions.placeholder(R.drawable.ic_loading);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE);

        Glide.with(context)
                .load(mobileDetailList.get(position).getUrl())
                .apply(requestOptions)
                .into(imageView);

        container.addView(imageLayout, 0);

        TextView tvTitle = imageLayout.findViewById(R.id.tvTitle);
        tvTitle.setText(String.valueOf(context.getString(R.string.text_price) + " " + mobileObject.getPrice()
                + " " + context.getString(R.string.text_rating) + " " + mobileObject.getRating()));

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}