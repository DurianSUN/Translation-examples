package com.haoxin.trainingdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.haoxin.trainingdemo.tool.BaseActivity;

/**
 * 自定义了一个选择颜色的列表，注意事项，在扩展的布局文件中找出控件的时。 例如： showtext =
 * (TextView)content.findViewById(R.layout.textView); 注意：一定要加上扩展的布局文件名称
 * 
 * @author haondroid
 * 
 */
public class SettingActivity extends BaseActivity
{
	private Context mContext = null;
	private EditText name_Eex;
	private Button sava_btn;
	private Intent intent;
	private ListView colorList;
	private ColorAdapter colorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		name_Eex = (EditText) findViewById(R.id.nametext);
		sava_btn = (Button) findViewById(R.id.savaBtn);
		colorList = (ListView) findViewById(R.id.colorlist);

		colorAdapter = new ColorAdapter(this);
		colorList.setAdapter(colorAdapter);

		colorList.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int postion, long arg3)
			{

				// makeToast(1+postion+"", 1);
				intent.putExtra("colornumber", postion + "");
				setResult(20, intent);
				finish();

			}
		});

		intent = new Intent();
		sava_btn.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				try
				{
					String name = name_Eex.getText().toString();
					if (name.equals(""))
					{
						makeToast("输入为空，请输入选择的颜色序号。", 1);
					} else
					{
						intent.putExtra("colornumber", name);
						setResult(20, intent);
						finish();

					}
				} catch (Exception e)
				{
					e.printStackTrace();
				}

			}
		});

	}

	class ColorAdapter extends BaseAdapter
	{
		private LayoutInflater mInflater;

		public ColorAdapter(Context context)
		{
			mContext = context;
		}

		@Override
		public int getCount()
		{
			return mainlistbg.length;
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
		public View getView(int position, View contentView, ViewGroup parent)
		{

			ImageView image = null;
			TextView colornumber = null;

			if (contentView == null || position < mainlistbg.length)
			{

				contentView = mInflater.from(mContext).inflate(
						R.layout.colorliststyle, null);
				image = (ImageView) contentView.findViewById(R.id.colorImage);
				colornumber = (TextView) contentView
						.findViewById(R.id.colornumber);

			}

			image.setImageResource(mainlistbg[position]);
			// makeToast(position + "", 1);
			colornumber.setText(position + 1 + "、");

			return contentView;
		}

	}

}
