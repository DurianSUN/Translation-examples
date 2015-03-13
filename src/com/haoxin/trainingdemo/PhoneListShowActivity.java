package com.haoxin.trainingdemo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.LogRecord;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts.Photo;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 获得电话联系人，自定义Adapter显示。
 * 
 * @author haondroid
 * 
 */
public class PhoneListShowActivity extends ListActivity
{

	Context mContext = null;
	/** 获取库Phon表字段 **/
	private static final String[] PHONES_PROJECTION = new String[] {
			Phone.DISPLAY_NAME, Phone.NUMBER, Photo.PHOTO_ID, Phone.CONTACT_ID };
	private ListView showList;
	private ArrayList<String> nameData = new ArrayList<String>();// 用来装姓名的动态数组。
	private ArrayList<String> numberData = new ArrayList<String>();// 用来装号码的动态数组。
	private ArrayList<Bitmap> phoneData = new ArrayList<Bitmap>();// 用来装号码头像的动态数组。

	/**
	 * 联系人显示姓名
	 */
	private static final int PHONE_DESIPLAY_NAME_IDNEX = 0;
	/**
	 * 电话号码
	 */
	private static final int PHONE_NUMBER_INDEX = 1;
	/**
	 * 头像ID
	 */
	private static final int PHONE_PHOTO_INDEX = 2;
	/**
	 * 联系人ID
	 */
	private static final int PHONE_CONTACT_ID_INDEX = 3;

	private MyListAdapter mydapter = null;

	Cursor phoneCursor;

	Boolean finishState = false;

	HanderForGetData handerForGetData;

	/**
	 * 想实现后台加载100条就刷新一次 adapter.notifyDataSetChanged();
	 * 
	 * @param savedInstanceState
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		mContext = this;
		showList = this.getListView();

		Thread mThread = new Thread(new Runnable()
		{

			@Override
			public void run()
			{

				getPhoneData();

			}
		});
		mThread.start();

		handerForGetData = new HanderForGetData();

		// 获得联系人数据。
		mydapter = new MyListAdapter(this);

		setListAdapter(mydapter);

		// 为列表绑定监听
		showList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3)
			{
				// Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri
				// .parse("tel:" + numberData.get(arg2)));
				// startActivity(dialIntent);//拨打电话。
				Toast.makeText(
						PhoneListShowActivity.this,
						nameData.get(position) + "的电话是"
								+ numberData.get(position), Toast.LENGTH_LONG);
				System.out.println(nameData.get(position) + "的电话是"
						+ numberData.get(position));
			}
		});

		super.onCreate(savedInstanceState);
	}

	class HanderForGetData extends Handler
	{

		@Override
		public void handleMessage(Message msg)
		{
			super.handleMessage(msg);
			if (msg.what == 0x123)
			{

				// 刷新一次线程
				mydapter.notifyDataSetChanged();

			}

		}

	}

	/**
	 * 获得联系人数据
	 */
	private Boolean getPhoneData()
	{
		ContentResolver resolver = getContentResolver();
		phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION,
				null, null, null);
		if (phoneCursor != null)
		{

			while (phoneCursor.moveToNext())
			{
				// 获得联系人号码
				String phoneNumber = phoneCursor.getString(PHONE_NUMBER_INDEX);
				if (TextUtils.isEmpty(phoneNumber))
					continue;
				// 获得联系人ID
				Long contactid = phoneCursor.getLong(PHONE_CONTACT_ID_INDEX);
				// 获得联系人头像ID
				Long photoid = phoneCursor.getLong(PHONE_PHOTO_INDEX);
				// 获得联系人姓名
				String phoneName = phoneCursor
						.getString(PHONE_DESIPLAY_NAME_IDNEX);
				// 获得联系人头像Bitmap
				Bitmap contactPhoto = null;
				if (photoid > 0)
				{

					Uri uri = ContentUris.withAppendedId(
							ContactsContract.Contacts.CONTENT_URI, photoid);
					InputStream input = ContactsContract.Contacts
							.openContactPhotoInputStream(resolver, uri);

					contactPhoto = BitmapFactory.decodeStream(input);

				} else
				{

					contactPhoto = BitmapFactory.decodeResource(getResources(),
							R.drawable.logo);
				}

				nameData.add(phoneName);
				numberData.add(phoneNumber);
				phoneData.add(contactPhoto);
				// TODO 此处有问题
				if (nameData.size() % 280 == 0)
				{
					Message message = new Message();
					message.what = 0x123;
					handerForGetData.sendMessage(message);
					System.out.println();
				}

			}
			phoneCursor.close();

		}
		return true;

	}

	/**
	 * 制作自己的适配器。
	 * 
	 * @author haondroid
	 * 
	 */
	class MyListAdapter extends BaseAdapter
	{

		private ViewHolder holder;

		class ViewHolder
		{
			ImageView mImageView;
			TextView mTextViewName;
			TextView mTexViewConetent;

		}

		public MyListAdapter(Context context)
		{
			mContext = context;
		}

		@Override
		public boolean areAllItemsEnabled()
		{
			return false;
		}

		@Override
		public int getCount()
		{
			// 设置绘制的数量
			return nameData.size();
		}

		@Override
		public Object getItem(int arg0)
		{
			return arg0;
		}

		@Override
		public long getItemId(int arg0)
		{
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{

			holder = new ViewHolder();
			// ImageView iamge = null;
			// TextView title = null;
			// TextView text = null;
			if (convertView == null || position < numberData.size())
			{
				convertView = LayoutInflater.from(mContext).inflate(
						R.layout.liststyle, null);
				holder.mImageView = (ImageView) convertView
						.findViewById(R.id.peopleiamge);
				holder.mTextViewName = (TextView) convertView
						.findViewById(R.id.title);
				holder.mTexViewConetent = (TextView) convertView
						.findViewById(R.id.content);
				convertView.setTag(holder);
			} else
			{
				holder = (ViewHolder) convertView.getTag();
			}
			// 绘制联系人名称
			holder.mTextViewName.setText(nameData.get(position));
			// 绘制联系人号码
			holder.mTexViewConetent.setText(numberData.get(position));
			// 绘制联系人头像
			holder.mImageView.setImageBitmap(phoneData.get(position));
			System.out.println(nameData.get(position) + "的电话是"
					+ numberData.get(position));
			return convertView;
		}

	}

}
