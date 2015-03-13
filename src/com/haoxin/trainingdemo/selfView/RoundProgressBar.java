package com.haoxin.trainingdemo.selfView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.haoxin.trainingdemo.R;

public class RoundProgressBar extends View
{
	/**
	 * 画笔对象的引用
	 */
	private Paint paint;
	/**
	 * 圆环进度颜色
	 */
	private int roundProgressColor;
	/**
	 * 圆环颜色
	 */
	private int roundColor;
	/**
	 * 字体颜色
	 */
	private int textColor;
	/**
	 * 字大小
	 */
	private float textSize;
	/**
	 * 圆环宽度
	 */
	private float roundWidth;
	/**
	 * 进度最大值
	 */
	private int max;
	/**
	 * 是否显示中间进度
	 */
	private boolean textIsDisplayable;
	/**
	 * 进度的风格，是否实心，或者空心
	 */
	private int style;

	public RoundProgressBar(Context context)
	{
		this(context, null);
	}

	public RoundProgressBar(Context context, AttributeSet attr)
	{
		this(context, attr, 0);
	}

	public RoundProgressBar(Context context, AttributeSet attrs, int defStyle)
	{

		super(context, attrs, defStyle);

		paint = new Paint();

		TypedArray mTypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.RoundProgressBar);

		roundColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_roundColor, Color.RED);
		roundProgressColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_roundProgressColor, Color.GREEN);
		textColor = mTypedArray.getColor(
				R.styleable.RoundProgressBar_textColor, Color.GREEN);
		textSize = mTypedArray.getDimension(
				R.styleable.RoundProgressBar_textSize, 15);
		roundWidth = mTypedArray.getDimension(
				R.styleable.RoundProgressBar_roundWidth, 5);
		max = mTypedArray.getInteger(R.styleable.RoundProgressBar_max, 100);
		textIsDisplayable = mTypedArray.getBoolean(
				R.styleable.RoundProgressBar_textIsDisplayable, true);
		style = mTypedArray.getInt(R.styleable.RoundProgressBar_style, 0);

		mTypedArray.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
	}
	
	
	

}
