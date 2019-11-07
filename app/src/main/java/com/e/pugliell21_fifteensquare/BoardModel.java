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



		this.set_board();
	}




	private void set_board()
	{
		// SET THE ELEMENTS OF 'buttons[][]' BACKGROUND RESOURCES TO BE EQUAL TO 'images[][]'
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				this.buttons[row][col].setBackgroundResource(this.images[row][col]);
	}


	public void win()
	{
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				this.images[row][col] = completed[row][col];


		this.set_board();


		this.check_if_complete();
	}





	public void move_square(int row, int col)
	{
		// IF THE BLANK SQUARE IS CLICKED
		if (this.get_image(row, col) == R.drawable.blank)
			return;



		// IF THE SQUARE ABOVE THE CLICKED SQUARE IS BLANK
		if (this.get_image(row - 1, col) == R.drawable.blank)
		{
			// SWITCH THE BUTTONS' BACKGROUND RESOURCE
			this.set_button(row, col, R.drawable.blank);
			this.set_button(row - 1, col, this.get_image(row, col));

			// SWITCH THE IMAGE IDS IN THE BOARD MODEL
			int temp = this.get_image(row, col);
			this.set_image(row, col, this.get_image(row - 1, col));
			this.set_image(row - 1, col, temp);
		}



		// IF THE SQUARE TO THE LEFT OF THE CLICKED SQUARE IS BLANK
		else if (this.get_image(row, col - 1) == R.drawable.blank)
		{
			// SWITCH THE BUTTONS' BACKGROUND RESOURCE
			this.set_button(row, col, R.drawable.blank);
			this.set_button(row, col - 1, this.get_image(row, col));

			// SWITCH THE IMAGE IDS IN THE BOARD MODEL
			int temp = this.get_image(row, col);
			this.set_image(row, col, this.get_image(row, col - 1));
			this.set_image(row, col - 1, temp);
		}



		// IF THE SQUARE TO THE RIGHT OF THE CLICKED SQUARE IS BLANK
		else if (this.get_image(row, col + 1) == R.drawable.blank)
		{
			// SWITCH THE BUTTONS' BACKGROUND RESOURCE
			this.set_button(row, col, R.drawable.blank);
			this.set_button(row, col + 1, this.get_image(row, col));

			// SWITCH THE IMAGE IDS IN THE BOARD MODEL
			int temp = this.get_image(row, col);
			this.set_image(row, col, this.get_image(row, col + 1));
			this.set_image(row, col + 1, temp);
		}



		// IF THE SQUARE BELOW THE CLICKED SQUARE IS BLANK
		else if (this.get_image(row + 1, col) == R.drawable.blank)
		{
			// SWITCH THE BUTTONS' BACKGROUND RESOURCE
			this.set_button(row, col, R.drawable.blank);
			this.set_button(row + 1, col, this.get_image(row, col));

			// SWITCH THE IMAGE IDS IN THE BOARD MODEL
			int temp = this.get_image(row, col);
			this.set_image(row, col, this.get_image(row + 1, col));
			this.set_image(row + 1, col, temp);
		}


		this.check_if_complete();
	}





	private int get_image(int row, int col)
	{
		if (row < 0 || 3 < row ||
			col < 0 || 3 < col)
			return -1;

		return this.images[row][col];
	}





	private void set_image(int row, int col, int image_ID)
	{
		if (row < 0 || 3 < row ||
			col < 0 || 3 < col)
			return;
		if (image_ID == -1)
			return;

		this.images[row][col] = image_ID;
	}





	private void set_button(int row, int col, int image_ID)
	{
		if (row < 0 || 3 < row ||
			col < 0 || 3 < col)
			return;
		if (image_ID == -1)
			return;

		this.buttons[row][col].setBackgroundResource(image_ID);
	}


	private void check_if_complete()
	{
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				if (this.images[row][col] != completed[row][col])
					return;

		Toast.makeText(this.app_context, "CONGRATS, YOU WIN", Toast.LENGTH_SHORT).show();
	}
}
