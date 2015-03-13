package com.haoxin.trainingdemo.selfView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.haoxin.trainingdemo.R;

public class ClearEditText extends LinearLayout
{
	/**
	 * 复合组件，却没有实现子组件的功能
	 */

	private EditText editText;
	private Button button;

	public ClearEditText(Context context)
	{
		super(context);

		String infService = Context.LAYOUT_INFLATER_SERVICE;
		LayoutInflater li;
		li = (LayoutInflater) getContext().getSystemService(infService);
		li.inflate(R.layout.clear_edit_view, this, true);

		editText = (EditText) findViewById(R.id.clear_editText);
		editText.setText("复合组件");
		button = (Button) findViewById(R.id.clear_button);

		clearupButton();

	}

	private void clearupButton()
	{

		button.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				// TODO Auto-generated method stub

			}
		});
	}

	public ClearEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

}
