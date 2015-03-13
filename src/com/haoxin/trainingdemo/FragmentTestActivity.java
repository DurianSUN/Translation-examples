package com.haoxin.trainingdemo;

import java.util.ArrayList;

import com.haoxin.trainingdemo.tool.BaseActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentTestActivity extends BaseActivity
{

	private TextView title;
	private ListView showList;
	private EditText editText;
	private ArrayList<String> to_do_Item = null;
	private ArrayAdapter<String> aas = null;
	private SharedPreferences sp ;

	@Override
	protected void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragmentlayout);
		showList = (ListView) findViewById(R.id.fragmentshowlist);
		editText = (EditText) findViewById(R.id.editext);
		title=(TextView)findViewById(R.id.todo_title);
		
		//初始化标题背景颜色
//		init_title_bg_color();

		aas = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, getData());
		showList.setAdapter(aas);
		editText.setOnKeyListener(new View.OnKeyListener()
		{

			public boolean onKey(View v, int KeyCode, KeyEvent event)
			{
				if (event.getAction() == KeyEvent.ACTION_DOWN)
					if ((KeyCode == KeyEvent.KEYCODE_DPAD_CENTER)
							|| (KeyCode == KeyEvent.KEYCODE_ENTER))
					{

						to_do_Item.add(to_do_Item.size() + 1 + "、"
								+ editText.getText().toString());
						aas.notifyDataSetChanged();
						editText.setText("");
						return true;

					}

				return false;
			}
		});

	}

	private void init_title_bg_color()
	{
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		String color = sp.getString("bgtitcolor", "");
		title.setBackgroundResource(mainlistbg[Integer.parseInt(color)+1]);
		
		
	}

	private ArrayList<String> getData()
	{
		to_do_Item = new ArrayList();

		to_do_Item.add("1、获取联系人(自定义Adapter显示)");
		to_do_Item.add("2、跳转页面，获得Intnet返回值");
		to_do_Item.add("3、各种样式的进度条(自定义控件)");
		to_do_Item.add("4、动画实例(属性,补间,逐帧)");
		to_do_Item.add("5、用户首选项(PreferenceActivity)");

		return to_do_Item;
	}

}
