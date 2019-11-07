package com.e.pugliell21_fifteensquare;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static com.e.pugliell21_fifteensquare.BoardModel.BOARD_HEIGHT;
import static com.e.pugliell21_fifteensquare.BoardModel.BOARD_WIDTH;

public class ButtonHandler implements View.OnClickListener
{
	private BoardModel model;
	private ImageButton[][] buttons;





	public ButtonHandler(ImageButton[][] buttons, Context app_context)
	{
		this.model = new BoardModel(buttons, app_context);
		this.buttons = buttons;
	}





	public void onClick(View v)
	{
		// IF AN IMAGE BUTTON WAS PRESSED
		if (v instanceof ImageButton)
		{
			int[] row_col = find_button(v);
			if (row_col != null)
				this.model.move_square(row_col[0], row_col[1]);
		}



		// IF THE SHUFFLE BUTTON WAS PRESSED
		else if (v instanceof Button)
			if (v.getId() == R.id.Shuffle)
				this.model.shuffle();
			else if (v.getId() == R.id.AutoWin)
				this.model.win();
	}





	private int[] find_button(View v)
	{
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				if (this.buttons[row][col] == v)
				{
					int[] row_col = {row, col};
					return row_col;
				}

		return null;
	}
}
