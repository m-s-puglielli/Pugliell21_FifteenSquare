package com.e.pugliell21_fifteensquare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);



		ImageButton image_button = (ImageButton) findViewById(R.id.Card);

		FifteenSquareHandler handler = new FifteenSquareHandler(image_button);

		image_button.setOnClickListener(handler);
	}
}
