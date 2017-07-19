package cn.almsound.www.baselibrary;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by alm on 17-6-6.
 * 万能适配器
 */

public abstract class AllPurposeAdapter<T> extends BaseAdapter {

    /**
     * 上下文弱引用
     */
    private WeakReference<Context> contextWeakReference;

    /**
     * 适配器数据源
     */
    private ArrayList<T> mDataList;

    /**
     * 适配器item的布局id
     */
    @LayoutRes
    private int mItemLayoutId;

    /**
     * 自定义适配器总数(适配器会根据这个数与数据源的比例来显示)
     */
    private int mCountSum = -1;

    /**
     * 构造器
     *
     * @param context      上下文
     * @param dataList     适配器数据源
     * @param itemLayoutId 适配器item的布局id
     */
    public AllPurposeAdapter(Context context, ArrayList<T> dataList, @LayoutRes int itemLayoutId) {
        contextWeakReference = new WeakReference<>(context);
        mDataList = dataList;
        mItemLayoutId = itemLayoutId;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        if (mCountSum == -1) {
            return mDataList.size();
        } else {
            return mCountSum;
        }
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public T getItem(int position) {
        if (mCountSum == -1) {
            return mDataList.get(position);
        } else {
            return mDataList.get(mCountSum % mDataList.size());
        }
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, position, getItem(position));
        return viewHolder.getConvertView();
    }


    /**
     * 更新数据源并刷新适配器进行显示
     *
     * @param dataList 新的数据源
     */
    public void refresh(ArrayList<T> dataList) {
        mDataList = dataList;
        notifyDataSetChanged();
    }

    /**
     * 删除数据源中指定位置的数据
     *
     * @param position 指定删除位置
     */
    public void deleteListByPosition(int position) {
        if (position >= mDataList.size()) {
            return;
        }

        mDataList.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 设置自定义数据总量
     *
     * @param countSum 自定义数据总量
     * @return AllPurposeAdapter本类对象
     */
    public AllPurposeAdapter<T> setCount(int countSum) {
        mCountSum = countSum;
        return this;
    }

    /**
     * 获取ViewHolder
     *
     * @param position    当前位置
     * @param convertView 复用的View
     * @param parent      父布局
     * @return ViewHolder
     */
    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(contextWeakReference.get(), convertView, parent, mItemLayoutId, position);
    }


    protected abstract void convert(ViewHolder viewHolder, int position, T item);
}
