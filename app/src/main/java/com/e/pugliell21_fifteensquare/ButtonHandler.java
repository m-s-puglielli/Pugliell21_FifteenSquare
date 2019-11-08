/**
 * @formatter:off
 * @author Maximilian Puglielli
 * @version Last edited on 11/08/2019
 */
package com.e.pugliell21_fifteensquare;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static com.e.pugliell21_fifteensquare.BoardModel.BOARD_HEIGHT;
import static com.e.pugliell21_fifteensquare.BoardModel.BOARD_WIDTH;

/**
 * This class is the handler for all button-pressed-events.
 * More specifically, this class includes the onClick() method, which handles all button-pressed-events
 * interpreted by the button-pressed-event listening threads, known as on-click-listeners.
 */
public class ButtonHandler implements View.OnClickListener
{
	private BoardModel model;
	private ImageButton[][] buttons;


	/**
	 * This constructor is the default for this class.
	 * @param buttons
	 * @param app_context
	 */
	public ButtonHandler(ImageButton[][] buttons, Context app_context)
	{
		this.model = new BoardModel(buttons, app_context);
		this.buttons = buttons;
	}


	/**
	 * This method implements all button presses' functionality.
	 * More specifically, this method checks if a game board's image button or one of either the "Shuffle"
	 * or "Auto-Win" buttons were pressed, then depending on which, it calls the appropriate method from
	 * the BoardModel.java class. This method is the implementation of the View.OnClickListener interface,
	 * and is automatically called whenever one of the button-pressed-event listening threads, known
	 * as on-click-listeners, interprets a button pressed event.
	 * @param v a reference to the View object, from which a button-presses-event was interpreted by
	 *          by one of the the button-pressed-event listening threads, known as on-click-listeners
	 */
	public void onClick(View v)
	{
		// if an ImageButton was pressed,
		if (v instanceof ImageButton)
		{
			int[] row_col = find_button((ImageButton) v);

			// and that button was found in buttons: ImageButton[][], then call model.move_square()
			if (row_col != null)
				this.model.move_square(row_col[0], row_col[1]);
		}



		// if a Button was pressed,
		else if (v instanceof Button)
			// and that button is the "Shuffle" button, then call model.shuffle()
			if (v.getId() == R.id.Shuffle)
				this.model.shuffle();
			// and that button is the "Auto-Win" button, then call model.auto_win()
			else if (v.getId() == R.id.AutoWin)
				this.model.auto_win();
	}


	/**
	 * This method finds a particular button's location in the 2D array buttons: ImageButton[][].
	 * When the onClick() method is called, a reference to the button that was click is passed to it,
	 * v: View. This method receives that variable "v" cast as ImageButton, and returns an array of
	 * two integers, such that the first integer is the row that button resides in, and the second is
	 * the column that button resides in.
	 * @param button a reference to an ImageButton, which is the button being searched for in buttons: ImageButton[][]
	 * @return {row, col}: int[], where row is the row # that v: ImageButton resides in, and col is the
	 *              column # that v: ImageButton resides in.
	 */
	private int[] find_button(ImageButton button)
	{
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				if (this.buttons[row][col] == button)
				{
					int[] row_col = {row, col};
					return row_col;
				}

		return null;
	}
}
