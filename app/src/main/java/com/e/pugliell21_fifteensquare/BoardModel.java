package com.e.pugliell21_fifteensquare;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

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

		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				this.images[row][col] = completed[row][col];



		// SHUFFLE THE BOARD
		this.shuffle();
	}





	public void shuffle()
	{
		do
		{
			ArrayList<Integer> temp = new ArrayList<>();

			for (int row = 0; row < BOARD_HEIGHT; row++)
				for (int col = 0; col < BOARD_WIDTH; col++)
					temp.add(this.images[row][col]);

			Collections.shuffle(temp);

			for (int row = 0; row < BOARD_HEIGHT; row++)
				for (int col = 0; col < BOARD_WIDTH; col++)
					this.images[row][col] = temp.get((row * 4) + col);
		}
		while (!is_solvable());



		this.set_board();
	}





	private void set_board()
	{
		// SET THE ELEMENTS OF 'buttons[][]' BACKGROUND RESOURCES TO BE EQUAL TO 'images[][]'
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				this.buttons[row][col].setBackgroundResource(this.images[row][col]);
	}


	private boolean is_solvable()
	{
		int[][] values = new int[BOARD_HEIGHT][BOARD_WIDTH];

		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
			{
				switch (this.images[row][col])
				{
					case R.drawable.blank:
						values[row][col] = 0;
						break;

					case R.drawable.square1:
						values[row][col] = 1;
						break;

					case R.drawable.square2:
						values[row][col] = 2;
						break;

					case R.drawable.square3:
						values[row][col] = 3;
						break;

					case R.drawable.square4:
						values[row][col] = 4;
						break;

					case R.drawable.square5:
						values[row][col] = 5;
						break;

					case R.drawable.square6:
						values[row][col] = 6;
						break;

					case R.drawable.square7:
						values[row][col] = 7;
						break;

					case R.drawable.square8:
						values[row][col] = 8;
						break;

					case R.drawable.square9:
						values[row][col] = 9;
						break;

					case R.drawable.square10:
						values[row][col] = 10;
						break;

					case R.drawable.square11:
						values[row][col] = 11;
						break;

					case R.drawable.square12:
						values[row][col] = 12;
						break;

					case R.drawable.square13:
						values[row][col] = 13;
						break;

					case R.drawable.square14:
						values[row][col] = 14;
						break;

					case R.drawable.square15:
						values[row][col] = 15;
						break;

					default:
						Toast.makeText(this.app_context, "ERROR -- BoardModel.java:is_solvable()", Toast.LENGTH_SHORT).show();
				}
			}


		if (values[BOARD_HEIGHT - 1][BOARD_WIDTH - 1] != 0)
		{
			for (int row = 0; row < BOARD_HEIGHT - 1; row++)
				for (int col = 0; col < BOARD_WIDTH; col++)
					if (values[row][col] == 0)
						switch_values_down(row, col, row + 1, col, values);

			for (int col = 0; col < BOARD_WIDTH - 1; col++)
				if (values[BOARD_HEIGHT - 1][col] == 0)
					switch_values_right(BOARD_HEIGHT - 1, col, BOARD_HEIGHT - 1, col + 1, values);
		}


		int[] values_linear = new int[BOARD_HEIGHT * BOARD_WIDTH];

		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				values_linear[(row * 4) + col] = values[row][col];


		for (int i = 0; i < (BOARD_HEIGHT * BOARD_WIDTH) - 1; i++)
		{
			int cnt = 0;

			for (int j = i + 1; j < (BOARD_HEIGHT * BOARD_WIDTH) - 1; j++)
				if (values_linear[i] > values_linear[j])
					cnt++;

			values_linear[i] = cnt;
		}


		int sum = 0;
		for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; i++)
			sum += values_linear[i];

		return (sum % 2) == 0;
	}


	private void switch_values_down(int row_1, int col_1, int row_2, int col_2, int[][] values)
	{
		if (row_1 < 0 || BOARD_HEIGHT <= row_1 || row_2 < 0 || BOARD_HEIGHT <= row_2 || col_1 < 0 || BOARD_WIDTH <= col_1 || col_2 < 0 || BOARD_WIDTH <= col_2)
			return;

		int temp = values[row_1][col_1];
		values[row_1][col_1] = values[row_2][col_2];
		values[row_2][col_2] = temp;

		switch_values_down(row_1 + 1, col_1, row_2 + 1, col_2, values);
	}


	private void switch_values_right(int row_1, int col_1, int row_2, int col_2, int[][] values)
	{
		if (row_1 < 0 || BOARD_HEIGHT <= row_1 || row_2 < 0 || BOARD_HEIGHT <= row_2 || col_1 < 0 || BOARD_WIDTH <= col_1 || col_2 < 0 || BOARD_WIDTH <= col_2)
			return;

		int temp = values[row_1][col_1];
		values[row_1][col_1] = values[row_2][col_2];
		values[row_2][col_2] = temp;

		switch_values_right(row_1, col_1 + 1, row_2, col_2 + 1, values);
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
		if (row < 0 || BOARD_HEIGHT <= row || col < 0 || BOARD_WIDTH <= col)
			return -1;

		return this.images[row][col];
	}





	private void set_image(int row, int col, int image_ID)
	{
		if (row < 0 || BOARD_HEIGHT <= row || col < 0 || BOARD_WIDTH <= col || image_ID == -1)
		{
			Toast.makeText(this.app_context, "ERROR -- BoardModel.java:set_image()", Toast.LENGTH_SHORT).show();
			return;
		}

		this.images[row][col] = image_ID;
	}





	private void set_button(int row, int col, int image_ID)
	{
		if (row < 0 || BOARD_HEIGHT <= row || col < 0 || BOARD_WIDTH <= col || image_ID == -1)
		{
			Toast.makeText(this.app_context, "ERROR -- BoardModel.java:set_button()", Toast.LENGTH_SHORT).show();
			return;
		}

		this.buttons[row][col].setBackgroundResource(image_ID);
	}


	private void check_if_complete()
	{
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				if (this.images[row][col] != completed[row][col])
					return;

		Toast.makeText(this.app_context, "YOU WIN", Toast.LENGTH_LONG).show();
	}
}
