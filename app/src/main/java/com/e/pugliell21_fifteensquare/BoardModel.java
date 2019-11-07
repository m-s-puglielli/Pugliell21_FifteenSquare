package com.e.pugliell21_fifteensquare;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.Toast;

public class BoardModel
{
	public static final int BOARD_HEIGHT = 4;
	public static final int BOARD_WIDTH = 4;





	private static final int[][] completed = {{R.drawable.square1,  R.drawable.square2,  R.drawable.square3,  R.drawable.square4},
											  {R.drawable.square5,  R.drawable.square6,  R.drawable.square7,  R.drawable.square8},
											  {R.drawable.square9,  R.drawable.square10, R.drawable.square11, R.drawable.square12},
											  {R.drawable.square13, R.drawable.square14, R.drawable.square15, R.drawable.blank}};
	private ImageButton[][] buttons;
	private Context app_context;
	private int[][] images;





	public BoardModel(ImageButton[][] buttons, Context app_context)
	{
		// INITIALIZE PASSED VARIABLES
		this.buttons = buttons;
		this.app_context = app_context;



		// SET THE INITIAL BACKGROUND FOR ALL THE BUTTONS
		this.images = new int[BOARD_HEIGHT][BOARD_WIDTH];
		this.images[0][0] = R.drawable.blank;
		this.images[0][1] = R.drawable.square1;
		this.images[0][2] = R.drawable.square2;
		this.images[0][3] = R.drawable.square3;
		this.images[1][0] = R.drawable.square4;
		this.images[1][1] = R.drawable.square5;
		this.images[1][2] = R.drawable.square6;
		this.images[1][3] = R.drawable.square7;
		this.images[2][0] = R.drawable.square8;
		this.images[2][1] = R.drawable.square9;
		this.images[2][2] = R.drawable.square10;
		this.images[2][3] = R.drawable.square11;
		this.images[3][0] = R.drawable.square12;
		this.images[3][1] = R.drawable.square13;
		this.images[3][2] = R.drawable.square14;
		this.images[3][3] = R.drawable.square15;



		// SHUFFLE THE BOARD
		this.shuffle();
	}





	public void shuffle()
	{
		// SHUFFLE 'images[][]'
		// TODO: implement "shuffle the board" functionality



		// SET THE ELEMENTS OF 'buttons[][]' BACKGROUND RESOURCES TO BE EQUAL TO 'images[][]'
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				this.buttons[row][col].setBackgroundResource(this.images[row][col]);
	}





	public void move_square(int row, int col)
	{
		// IF THE BLANK SQUARE IS CLICKED
		if (this.images[row][col] == R.drawable.blank)
			return;



		// IF THE SQUARE ABOVE THE CLICKED SQUARE IS BLANK
		if (this.images[row - 1][col] == R.drawable.blank)
		{
			// SWITCH THE BUTTONS' BACKGROUND RESOURCE
			buttons[row][col].setBackgroundResource(R.drawable.blank);
			buttons[row - 1][col].setBackgroundResource(this.images[row][col]);

			// SWITCH THE IMAGE IDS IN THE BOARD MODEL
			int temp = this.images[row][col];
			this.images[row][col] = this.images[row - 1][col];
			this.images[row - 1][col] = temp;
		}



		// IF THE SQUARE TO THE LEFT OF THE CLICKED SQUARE IS BLANK
		else if (this.images[row][col - 1] == R.drawable.blank)
		{
			// SWITCH THE BUTTONS' BACKGROUND RESOURCE
			buttons[row][col].setBackgroundResource(R.drawable.blank);
			buttons[row][col - 1].setBackgroundResource(this.images[row][col]);

			// SWITCH THE IMAGE IDS IN THE BOARD MODEL
			int temp = this.images[row][col];
			this.images[row][col] = this.images[row][col - 1];
			this.images[row][col - 1] = temp;
		}



		// IF THE SQUARE TO THE RIGHT OF THE CLICKED SQUARE IS BLANK
		else if (this.images[row][col + 1] == R.drawable.blank)
		{
			// SWITCH THE BUTTONS' BACKGROUND RESOURCE
			buttons[row][col].setBackgroundResource(R.drawable.blank);
			buttons[row][col + 1].setBackgroundResource(this.images[row][col]);

			// SWITCH THE IMAGE IDS IN THE BOARD MODEL
			int temp = this.images[row][col];
			this.images[row][col] = this.images[row][col + 1];
			this.images[row][col + 1] = temp;
		}



		// IF THE SQUARE BELOW THE CLICKED SQUARE IS BLANK
		else if (this.images[row + 1][col] == R.drawable.blank)
		{
			// SWITCH THE BUTTONS' BACKGROUND RESOURCE
			buttons[row][col].setBackgroundResource(R.drawable.blank);
			buttons[row + 1][col].setBackgroundResource(this.images[row][col]);

			// SWITCH THE IMAGE IDS IN THE BOARD MODEL
			int temp = this.images[row][col];
			this.images[row][col] = this.images[row + 1][col];
			this.images[row + 1][col] = temp;
		}



		if (is_complete())
		{
			Toast.makeText(this.app_context, "CONGRATS, YOU WIN", Toast.LENGTH_SHORT).show();
		}
	}





	private boolean is_complete()
	{
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				if (this.images[row][col] != completed[row][col])
					return false;
		return true;
	}
}
