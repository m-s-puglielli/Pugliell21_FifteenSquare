package com.e.pugliell21_fifteensquare;

import android.view.View;
import android.widget.ImageButton;

public class FifteenSquareHandler implements View.OnClickListener
{
	private BoardModel model;
	private ImageButton image_button;

	public FifteenSquareHandler(ImageButton image_button)
	{
		this.model = new BoardModel();
		this.image_button = image_button;
		image_button.setBackgroundResource(model.card);
	}

	public void onClick(View v)
	{
		model.toggle = -model.toggle;

		if (model.toggle > 0)
			model.card = R.drawable.king_of_spades;
		else
			model.card = R.drawable.seven_of_diamonds;

		image_button.setBackgroundResource(model.card);
		image_button.invalidate();
	}
}
