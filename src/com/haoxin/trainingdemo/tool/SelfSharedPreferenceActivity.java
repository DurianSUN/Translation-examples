package com.haoxin.trainingdemo.tool;

import com.haoxin.trainingdemo.R;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;

public class SelfSharedPreferenceActivity extends BaseActivity
{

	private CheckBox autoUpdate;
	Spinner upadteFreqSpinner;
	Spinner magintudeSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		upadteFreqSpinner = (Spinner) findViewById(R.id.spinner_updat_freq);
		magintudeSpinner = (Spinner) findViewById(R.id.spinner_quake_mag);

		populateSpinners();

	}

	private void populateSpinners()
	{
		// 填充更新频率微调框
		ArrayAdapter<CharSequence> fAdapter;
		fAdapter = ArrayAdapter.createFromResource(this,
				R.array.update_fre_options,
				android.R.layout.simple_spinner_item);

		upadteFreqSpinner.setAdapter(fAdapter);
		// 填充最小震级微调框

		ArrayAdapter<CharSequence> mAdapter;
		mAdapter = ArrayAdapter.createFromResource(this, R.array.magnitude,
				android.R.layout.simple_spinner_item);
		
		mAdapter.setDropDownViewResource(0);
		

	}

}
