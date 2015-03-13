package com.haoxin.trainingdemo;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Window;
/**
 * 继承PreferenceActivity类，然后配置自己上自己定制的布局文件。
 * @author haondroid
 *
 */
public class MyPreferenceActivity extends PreferenceActivity
{
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.userpreferences);

	}

}
