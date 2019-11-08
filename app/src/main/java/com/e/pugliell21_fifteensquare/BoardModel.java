/**
 * @formatter:off
 * @author Maximilian Puglielli
 * @version Last edited on 11/08/2019
 */
package com.e.pugliell21_fifteensquare;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class models the game board, and includes all methods which relate to the game board.
 * More specifically, this class is responsible for creating a model of the game board, shuffling the
 * game board, knowing if it is in a solvable condition after each shuffle, moving squares on the game
 * board, and knowing when the game board is in a winning condition.
 */
public class BoardModel
{
	/**
	 * The game board's number of rows, which is four.
	 */
	public static final int BOARD_HEIGHT = 4;
	/**
	 * The game board's number of columns, which is four.
	 */
	public static final int BOARD_WIDTH  = 4;
	/**
	 * The game board's number of squares, which is its height times its width, which is sixteen.
	 */
	public static final int NUM_SQUARES  = BOARD_HEIGHT * BOARD_WIDTH;



	/**
	 * A 2D array of references to the images used as the background resources for the image buttons
	 * - e.g. R.drawable.square1. This array is static and final, and is in the winning configuration.
	 * It is used to comparison for images: int[][], so the check_if_complete() method can determine
	 * if the game board is in a winning configuration.
	 */
	private static final int[][] completed = {{R.drawable.square1,  R.drawable.square2,  R.drawable.square3,  R.drawable.square4},
											  {R.drawable.square5,  R.drawable.square6,  R.drawable.square7,  R.drawable.square8},
											  {R.drawable.square9,  R.drawable.square10, R.drawable.square11, R.drawable.square12},
											  {R.drawable.square13, R.drawable.square14, R.drawable.square15, R.drawable.blank}};
	private ImageButton[][] buttons;
	private Context app_context;
	private int[][] images;





	/**
	 * This constructor is the default for this class.
	 * @param buttons
	 * @param app_context
	 */
	public BoardModel(ImageButton[][] buttons, Context app_context)
	{
		// initialize variables passed to this object via this constructor
		this.buttons = buttons;
		this.app_context = app_context;



		// set the initial background for all the game board's ImageButtons, using completed: int[][],
		// because it this allows the method of two nested for-loops rather than a long list of initialization
		// statements, and images: int[][] will be shuffled anyway
		this.images = new int[BOARD_HEIGHT][BOARD_WIDTH];
		this.win();



		// shuffle images: int[][]
		this.shuffle();
	}





	/**
	 * This method shuffles the game board to a solvable configuration.
	 * This method shuffles the elements of images: int[][] until they're in a solvable configuration,
	 * and then sets the background resources of all the elements in buttons: ImageButtons[][] equal
	 * to the images referenced to by the image ids in images: int[][].
	 */
	public void shuffle()
	{
		// shuffle the elements of images: int[][], until they're in a solvable configuration
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
		while (! is_solvable());



		// sets the background resources of all the elements in buttons: ImageButtons[][] equal to the
		// images referenced to by the image ids in images: int[][]
		this.set_board();
	}





	/**
	 * This method moves a square at position (row, col) to an adjacent position - up, down, left, or
	 * right - which is holding the blank square.
	 * More specifically, this method switches the selected square with the blank square, if the selected
	 * square is above, below, to the left, or to the right of the blank square.
	 * @param row an integer, representing the current square's row number
	 * @param col an integer, representing the current square's column number
	 */
	public void move_square(int row, int col)
	{
		// if the blank square pressed
		if (this.get_image(row, col) == R.drawable.blank)
			return;



		// if the square above the pressed square is blank
		if (this.get_image(row - 1, col) == R.drawable.blank)
		{
			// switch the buttons' background resource
			this.set_button(row, col, R.drawable.blank);
			this.set_button(row - 1, col, this.get_image(row, col));

			// switch the image ids in the board model
			int temp = this.get_image(row, col);
			this.set_image(row, col, this.get_image(row - 1, col));
			this.set_image(row - 1, col, temp);
		}



		// if the square to the left of the clicked square is blank
		else if (this.get_image(row, col - 1) == R.drawable.blank)
		{
			// switch the buttons' background resource
			this.set_button(row, col, R.drawable.blank);
			this.set_button(row, col - 1, this.get_image(row, col));

			// switch the image ids in the board model
			int temp = this.get_image(row, col);
			this.set_image(row, col, this.get_image(row, col - 1));
			this.set_image(row, col - 1, temp);
		}



		// if the square to the right of the clicked square is blank
		else if (this.get_image(row, col + 1) == R.drawable.blank)
		{
			// switch the buttons' background resource
			this.set_button(row, col, R.drawable.blank);
			this.set_button(row, col + 1, this.get_image(row, col));

			// switch the image ids in the board model
			int temp = this.get_image(row, col);
			this.set_image(row, col, this.get_image(row, col + 1));
			this.set_image(row, col + 1, temp);
		}



		// if the square below the clicked square is blank
		else if (this.get_image(row + 1, col) == R.drawable.blank)
		{
			// switch the buttons' background resource
			this.set_button(row, col, R.drawable.blank);
			this.set_button(row + 1, col, this.get_image(row, col));

			// switch the image ids in the board model
			int temp = this.get_image(row, col);
			this.set_image(row, col, this.get_image(row + 1, col));
			this.set_image(row + 1, col, temp);
		}



		// check if images: int[][] is in a winning configuration, i.e.
		// check if this move was the winning move
		this.check_if_complete();
	}





	/**
	 * This method sets the game board to a winning state.
	 * More specifically, this method sets the elements of images: int[][] equal to the elements of
	 * completed: int[][], by calling win(), and sets the elements of buttons: ImageButton[][] background
	 * resources equal to the images referenced to in images: int[][], by calling set_board(), thereby
	 * setting the game board to a winning state. Then it calls check_if_complete() on the auto completed
	 * game board, so the end-game condition will be triggered, and the user will be informed as such,
	 * via a toast message.
	 */
	public void auto_win()
	{
		// set the elements of images: int[][] equal to the elements of completed: int[][]
		this.win();

		// set the game board's ImageButton background equal to the images, referenced to by the image
		// ids, in images: int[][]
		this.set_board();

		// check if the board is completed
		this.check_if_complete();
	}





	/**
	 * This method sets the elements of buttons: ImageButton[][] background resources equal to the
	 * images referenced to in images: int[][].
	 */
	private void set_board()
	{
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				this.buttons[row][col].setBackgroundResource(this.images[row][col]);
	}





	/**
	 * This method interprets all the elements in images: int[][], and determines if the game board
	 * is solvable or not.
	 * More specifically, this method translates all the bitmap references in images: int[][] into their
	 * corresponding integer values (e.g. R.drawable.square1 => 1), and populates a new 2D integer array,
	 * called values: int[][], with the translated integers. Next, it moves the zero, wherever it is
	 * in values: int[][], to the bottom-right corner, using moves that correspond to legal moves in
	 * the Fifteen Square game. Then, it creates a 1D array, called values_linear: int[], and populates
	 * it will all the elements in values: int[][] as if it were reading the 2D array - i.e. from left
	 * to right, and from top to bottom. Penultimately, it replaces each element in values_linear: int[]
	 * with the number of succeeding elements, which are less than the it, excluding the zero - e.g.
	 * if values_linear was {15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0}, then the 15 would
	 * be replaced with 14, because that is the number of elements succeeding the 15, which are less
	 * than itself, excluding the zero. And lastly, this method sums all the new elements in values_linear: int[],
	 * and if that sum is even, the board is solvable, otherwise it's not solvable.
	 * @return a boolean, representing if the game board is solvable or not
	 */
	private boolean is_solvable()
	{
		/* EXTERNAL CITATION
		 *      Date:		6th November 2019
		 * 	    Problem:	I, like most, did not know how to check if a Fifteen Square game board was
		 * 	                    solvable.
		 * 	    Resource:	https://www.youtube.com/watch?v=FS8eZ3qQa6E   &   http://mathworld.wolfram.com/15Puzzle.html
		 * 	    Solution:	I read the wolfram web-page, and didn't quite understand everything, so I watched
		 *                      the youtube video. Watching the steps the guy went through in the video
		 *                      solidified my understanding of the process.
		 */

		// interpret the images, referenced to by the image ids, in images: int[][], into their appropriate
		// integer values, and place those integer values into values: int[][]
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
						// this default case should never be triggered
						Toast.makeText(this.app_context, "ERROR -- BoardModel.java:is_solvable()", Toast.LENGTH_SHORT).show();
				}
			}



		// reconfigure values: int[][] such that the zero is in the bottom right corner
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



		// retrieve all the integers in values: int[][], and place them in a 1D int[] called values_linear: int[]
		int[] values_linear = new int[NUM_SQUARES];

		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				values_linear[(row * 4) + col] = values[row][col];



		// reformat values_linear: int[], such that values_linear: int[] at index i is equal to the
		// number of elements succeeding it which are less than it, not including the zero
		for (int i = 0; i < NUM_SQUARES - 1; i++)
		{
			int cnt = 0;

			for (int j = i + 1; j < NUM_SQUARES - 1; j++)
				if (values_linear[i] > values_linear[j])
					cnt++;

			values_linear[i] = cnt;
		}



		// sum all the values calculated in the code block immediately above
		int sum = 0;
		for (int i = 0; i < NUM_SQUARES; i++)
			sum += values_linear[i];

		// if the sum is even => the board is solvable
		// if the sum is odd  => the board is not solvable
		return (sum % 2) == 0;
	}





	/**
	 * This method moves the integer at values[row][col] to the lowest position within the same column.
	 * More specifically, this method moves the integer at values[row][col] down one position, and keeps
	 * recursing until the integer is in the lowest position within the same column.
	 * NOTE: this is a recursive method.
	 * @param row_1 an integer, representing the first position's row number
	 * @param col_1 an integer, representing the first position's column number
	 * @param row_2 an integer, representing the second position's row number
	 * @param col_2 an integer, representing the second position's column number
	 * @param values a 2D integer array, which is a reference to the 2D integer array from is_solvable()
	 */
	private void switch_values_down(int row_1, int col_1, int row_2, int col_2, int[][] values)
	{
		// NOTE: recursive loop exit, and out-of-bounds protection
		if (row_1 < 0 || BOARD_HEIGHT <= row_1 ||
			row_2 < 0 || BOARD_HEIGHT <= row_2 ||
			col_1 < 0 || BOARD_WIDTH  <= col_1 ||
			col_2 < 0 || BOARD_WIDTH  <= col_2)
			return;

		// NOTE: pre-order method work
		int temp = values[row_1][col_1];
		values[row_1][col_1] = values[row_2][col_2];
		values[row_2][col_2] = temp;

		// NOTE: recurse on the two squares directly below the current two squares
		switch_values_down(row_1 + 1, col_1, row_2 + 1, col_2, values);
	}





	/**
	 * This method moves the integer at values[row][col] to the right-most position within the same row.
	 * More specifically, this method moves the integer at values[row][col] right one position, and keeps
	 * recursing until the integer is in the right-most position within the same column.
	 * NOTE: this is a recursive method.
	 * @param row_1 an integer, representing the first position's row number
	 * @param col_1 an integer, representing the first position's column number
	 * @param row_2 an integer, representing the second position's row number
	 * @param col_2 an integer, representing the second position's column number
	 * @param values a 2D integer array, which is a reference to the 2D integer array from is_solvable()
	 */
	private void switch_values_right(int row_1, int col_1, int row_2, int col_2, int[][] values)
	{
		// NOTE: recursive loop exit, and out-of-bounds protection
		if (row_1 < 0 || BOARD_HEIGHT <= row_1 ||
			row_2 < 0 || BOARD_HEIGHT <= row_2 ||
			col_1 < 0 || BOARD_WIDTH  <= col_1 ||
			col_2 < 0 || BOARD_WIDTH  <= col_2)
			return;

		// NOTE: pre-order method work
		int temp = values[row_1][col_1];
		values[row_1][col_1] = values[row_2][col_2];
		values[row_2][col_2] = temp;

		// NOTE: recurse on the two squares directly to the right of the current two squares
		switch_values_right(row_1, col_1 + 1, row_2, col_2 + 1, values);
	}





	/**
	 * This method sets the elements of images: int[][] equal to the elements of completed: int[][].
	 */
	private void win()
	{
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				this.images[row][col] = completed[row][col];
	}





	/**
	 * This method retrieves the int at images[row][col], and includes out-of-bounds protection.
	 * @param row an integer, representing the row number of the desired image's position
	 * @param col an integer, representing the column number of the desired image's position
	 * @return this.images[row][col]: an integer, which is a reference to the desired image
	 */
	private int get_image(int row, int col)
	{
		// out-of-bounds protection
		if (row < 0 || BOARD_HEIGHT <= row ||
			col < 0 || BOARD_WIDTH  <= col)
			return -1;

		return this.images[row][col];
	}





	/**
	 * This method overwrites the image id in images[row][col], and includes protection from an out-of-bounds
	 * row/col or a corrupted image_ID.
	 * @param row an integer, representing the row number of the position of the image to be overwritten
	 * @param col an integer, representing the column number of the position of the image to be overwritten
	 * @param image_ID an integer, which is a reference to the new image
	 */
	private void set_image(int row, int col, int image_ID)
	{
		// out-of-bounds protection
		if (row < 0 || BOARD_HEIGHT <= row ||
			col < 0 || BOARD_WIDTH  <= col ||
			image_ID == -1)
		{
			// this if statement should never be triggered
			Toast.makeText(this.app_context, "ERROR -- BoardModel.java:set_image()", Toast.LENGTH_SHORT).show();
			return;
		}

		this.images[row][col] = image_ID;
	}





	/**
	 * This method sets the background resource of the button in buttons[row][col] to image_ID, and includes
	 * protection from an out-of-bounds row/col or a corrupted image_ID.
	 * @param row an integer, representing the row number of the position of the image to be overwritten
	 * @param col an integer, representing the column number of the position of the image to be overwritten
	 * @param image_ID an integer, which is a reference to the new image
	 */
	private void set_button(int row, int col, int image_ID)
	{
		// out-of-bounds protection
		if (row < 0 || BOARD_HEIGHT <= row ||
			col < 0 || BOARD_WIDTH  <= col ||
			image_ID == -1)
		{
			// this if statement should never be triggered
			Toast.makeText(this.app_context, "ERROR -- BoardModel.java:set_button()", Toast.LENGTH_SHORT).show();
			return;
		}

		this.buttons[row][col].setBackgroundResource(image_ID);
	}





	/**
	 * This method compares images: int[][] to completed: int[][] to determine if the game board has
	 * been completed, and informs the user as such, via a toast message.
	 */
	private void check_if_complete()
	{
		// check if all the elements of images: int[][] are equal to completed: int[][]
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				if (this.images[row][col] != completed[row][col])
					return;

		// inform the user that the game board is in a winning configuration
		Toast.makeText(this.app_context, "YOU WIN", Toast.LENGTH_LONG).show();
	}
}
