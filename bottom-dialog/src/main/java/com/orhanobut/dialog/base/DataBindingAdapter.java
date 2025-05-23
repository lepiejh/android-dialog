package com.orhanobut.dialog.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class DataBindingAdapter<T,B extends ViewDataBinding> extends BaseAdapter
{

	protected List<T> mList = null;
	protected Context mContext = null;
	protected LayoutInflater mInflater;
	protected B binding;
	protected abstract int getLayoutId();
	protected abstract int variableId();
	protected abstract void onBindView(int position);

	public DataBindingAdapter(Context mContext)
	{
		this.mContext = mContext;
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	
	/**
	 * 构造方法描述:判断适配器内部的集合是否为空方法
	 * @return 
	 * 返 回 类 型:boolean
	 */
	public boolean isNull()
	{
		return mList == null || mList.size() == 0;
	}

	/**
	 * 获取适配器集合的item数量
	 */
	@Override
	public int getCount()
	{
		return mList == null ? 0 : mList.size();
	}

	/**
	 * 获取当前item对象
	 */
	@Override
	public T getItem(int position)
	{
		return mList.get(position);
	}

	/**
	 * 获取当前item对象的下标id
	 */
	@Override
	public long getItemId(int position)
	{
		return position;
	}
	
	/**
	 * 构造方法描述:添加一项数据
	 * @param t 
	 * 返 回 类 型:void
	 */
	public void addItem(T t)
	{
		if (mList == null)
		{
			mList = new ArrayList<T>();
		}
		mList.add(t);
		notifyDataSetChanged();
	}
	
	/**
	 * 构造方法描述:添加一项数据到集合的首位
	 * @param t 
	 * 返 回 类 型:void
	 */
	public void addItemToIndex(T t)
	{
		if (mList == null)
		{
			mList = new ArrayList<T>();
		}
		mList.add(0, t);// 数据添加到数据的首位
		notifyDataSetChanged();
	}

	/**
	 * 构造方法描述:删除某一项
	 * @param position 
	 * 返 回 类 型:void
	 */
	public void removeItem(int position)
	{
		if (mList == null || mList.size() == 0)
		{
			return;
		}
		mList.remove(position);
		notifyDataSetChanged();
	}
	
	/**
	 * 构造方法描述:设置修改某一项
	 * @param t
	 * @param position 
	 * 返 回 类 型:void
	 */
	public void setItem(T t, int position)
	{
		if (mList == null || mList.size() == 0)
		{
			return;
		}
		mList.set(position, t);
		notifyDataSetChanged();
	}

	/**
	 * 构造方法描述:获取适配器所有数据
	 * @return 
	 * 返 回 类 型:List<T>
	 */
	public List<T> getList()
	{
		return mList;
	}

	/**
	 * 构造方法描述:为适配器设置数据
	 * @param list 
	 * 返 回 类 型:void
	 */
	public void setList(List<T> list)
	{
		this.mList = list;
		notifyDataSetChanged();//刷新数据
	}

	/**
	 * 构造方法描述:在原集合基础上追加一个集合数据
	 * @param list 
	 * 返 回 类 型:void
	 */
	public void addList(List<T> list)
	{
		if (list == null)
		{
			return;// 如果传进来的是空集合，就返回出去
		}
		if (mList == null)
		{
			mList = new ArrayList<T>();
		}
		// 此方法是将传进来的List<T>全部数据条目添加到myList<T>中，把传进来的一个集合添加到mList集合中的最后一个元素位置排列
		mList.addAll(list);
		notifyDataSetChanged();
	}
	
	/**
	 * 构造方法描述:清空所有数据 
	 * 返 回 类 型:void
	 */
	public void clear()
	{
		if (mList!=null)
		{
			mList.clear();
			notifyDataSetChanged();
		}
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {

			binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), getLayoutId(), parent, false);
			convertView = binding.getRoot();
			convertView.setTag(binding);
		} else {
			binding = (B) convertView.getTag();
		}

		binding.setVariable(variableId(), mList.get(position));
		onBindView(position);
		return convertView;
	}



}
