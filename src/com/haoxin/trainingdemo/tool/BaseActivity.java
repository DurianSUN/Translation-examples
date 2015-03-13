package com.haoxin.trainingdemo.tool;

import com.haoxin.trainingdemo.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

/**
 * 基类Activity 有去除标题 ，页面跳转等基本方法。 并且继承了Activity
 * 
 * @author haondroid
 * 
 */
public class BaseActivity extends Activity
{

	public static int[] mainlistbg = { R.drawable.color1, R.drawable.color2,
			R.drawable.color3, R.drawable.color4, R.drawable.color5,
			R.drawable.color6, R.drawable.color7, R.drawable.color8, };

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	/**
	 * 封装一个Intnet意图，来进行跳转等操作。
	 */

	private Intent mintnet;

	/**
	 * 
	 * @param context
	 *            显示的文字
	 * @param time
	 *            显示的时间,如果是1，则短时间显示，如果是2，则长时间显示。
	 */
	public void makeToast(String context, int time)
	{
		int showTime = 0;

		if (time == 1)
		{
			showTime = Toast.LENGTH_SHORT;

		} else
		{

			showTime = Toast.LENGTH_SHORT;
		}
		Toast.makeText(this, context, showTime).show();

	}

	/**
	 * 跳转到另外一个页面
	 * 
	 * @param context
	 *            跳转的当前上下文
	 * @param otherActivity
	 *            要进入的下一个页面
	 */

	public void trunIntent(Context context, Class otherActivity)
	{
		mintnet = new Intent(context, otherActivity);
		startActivity(mintnet);

	}

	/**
	 * 跳转到另外一个页面，并且获得另一个页面的Intent返回值。
	 * 
	 * @param context
	 * @param otherActivity
	 * @param requestCode
	 *            返回码
	 */
	public void trunIntentForResult(Context context, Class otherActivity,
			int requestCode)
	{
		mintnet = new Intent(context, otherActivity);
		startActivityForResult(mintnet, requestCode);

	}
}
