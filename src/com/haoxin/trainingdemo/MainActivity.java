package com.haoxin.trainingdemo;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.haoxin.trainingdemo.tool.BaseActivity;

/**
 * 主页展示 实现了OnSharedPreferenceChangeListener类，在onSharedPreferenceChanged()方法中
 */
public class MainActivity extends BaseActivity implements
		OnSharedPreferenceChangeListener
{
	private TextView main_list_title;
	private TextView showResult_tex;
	private Button getResult_Btn;
	private ListView listView;
	ArrayAdapter<String> myArrayAdapter = null;
	private SharedPreferences prefs;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showlist);
		listView = (ListView) findViewById(R.id.showlist);
		main_list_title = (TextView) findViewById(R.id.mianlist);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		prefs.registerOnSharedPreferenceChangeListener(this);
		// 初始化颜色
		initcolorbg();

		myArrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_expandable_list_item_1, getData());
		listView.setAdapter(myArrayAdapter);
		listView.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3)
			{
				switch (position + 1)
				{
				case 1:// 进入自定义Adapter实例页面
					makeToast("跳转进入联系人页面", 1);
					trunIntent(MainActivity.this, PhoneListShowActivity.class);
					break;
				case 2:
					// 进入startActivityForResult实例页面
					makeToast("进入风格填充界面", 1);
					trunIntentForResult(MainActivity.this,
							SettingActivity.class, 20);
					break;
				case 4:
					// 进入startActivityForResult实例页面
					makeToast("进入动画展示界面", 1);
					trunIntent(MainActivity.this, AnimotionDemoActivity.class);
					break;
				case 5:
					// 进入Shared Perference实例页面
					makeToast("进入Shared Perference,应用程序首选项界面", 1);
					trunIntent(MainActivity.this, MyPreferenceActivity.class);
					break;
				case 6:
					// 进入Shared Perference实例页面
					makeToast("进入备忘录", 1);
					trunIntent(MainActivity.this, FragmentTestActivity.class);
					break;
				case 7:
					// 进入Shared Perference实例页面
					makeToast("Intent", 1);
					// Uri uri = Uri.parse("content：//contacts/people");
					// Intent intent = new Intent(Intent.ACTION_PICK, uri);
					// startActivityForResult(intent, 2);

					break;
				default:
					break;
				}

			}
		});

	}

	private void initcolorbg()
	{
		String kk = prefs.getString("bgtitcolor", "");
		if (!kk.equals(""))
		{

			main_list_title.setBackgroundResource(mainlistbg[Integer
					.parseInt(kk)]);
		} else
		{

			main_list_title.setBackgroundResource(mainlistbg[1]);
		}
		makeToast("sp初始化标题背景颜色+" + kk, 1);

	}

	private ArrayList<String> getData()
	{
		ArrayList<String> list = new ArrayList();

		list.add("1、获取联系人(自定义Adapter显示)");
		list.add("2、跳转页面，获得Intnet返回值");
		list.add("3、各种样式的进度条(自定义控件)");
		list.add("4、动画实例(属性,补间,逐帧)");
		list.add("5、用户首选项(PreferenceActivity)");
		list.add("6、备忘录");
		list.add("7、系统联系人");

		return list;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 20)
		{
			String context = data.getExtras().getString("colornumber");
			makeToast(this.getClass().getName() + "获得的第二个页面的返回值是：" + context, 2);
			main_list_title.setBackgroundResource(mainlistbg[Integer
					.parseInt(context)]);

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences prefs, String key)
	{
		prefs = this.prefs;
		String kk = prefs.getString("bgtitcolor", key);
		makeToast(kk, 1);

		main_list_title
				.setBackgroundResource(mainlistbg[Integer.parseInt(kk) - 1]);
	}
}
