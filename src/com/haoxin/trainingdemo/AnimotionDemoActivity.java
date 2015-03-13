package com.haoxin.trainingdemo;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.haoxin.trainingdemo.tool.BaseActivity;

public class AnimotionDemoActivity extends BaseActivity
{
	private ImageView imageView;
	private ImageView imageView_space;
	private Animation myAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animotionshow);
		imageView = (ImageView) findViewById(R.id.showImage);
		imageView_space = (ImageView) findViewById(R.id.showspaceImage);

		// 逐帧动画
		imageView.setBackgroundResource(R.drawable.animotion_change);
		AnimationDrawable animationDrawable = (AnimationDrawable) imageView
				.getBackground();
		animationDrawable.start();

		// 补间动画

//		myAnimation = new Animation(AnimotionDemoActivity.this, R.anim.popin);
//		myAnimation.setRepeatMode(Animation.RESTART);
//		myAnimation.setRepeatCount(Animation.INFINITE);
//		imageView_space.startAnimation(myAnimation);

	}
}
