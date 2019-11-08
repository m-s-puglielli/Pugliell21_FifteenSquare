/**
 * @formatter:off
 * @author Maximilian Puglielli
 * @version 11/07/2019
 */

/* PROGRAM DESCRIPTION
 * To truly understand this program, there are a couple of things we need to go over:
 *      There's only one instance of the ButtonHandler.java class, and it exists in the MainActivity.java
 *          class's onCreate() method. Similarly, there's only one instance of the BoardModel.java class,
 *          and it exists in the ButtonHandler.java class. Therefore, in terms of class hierarchy, BoardModel.java
 *          is an aggregate of ButtonHandler.java, and ButtonHandler.java is an aggregate of MainActivity.java's
 *          onCreate() method.
 *      There are two important 2D arrays which model the game board. The first is called "buttons",
 *          and its a 2D array of ImageButton references, which is first initialized in MainActivity.java's
 *          onCreate() method. The second is called "images", and its a 2D array of integers - which are
 *          actually references to the bitmaps being drawn on the corresponding image buttons, e.g.
 *          "R.drawable.square1" references the bitmap depicting a one inside of a square - which is
 *          first initialized in BoardModel.java's BoardModel() default constructor.
 *          The ImageButton array is used for changing the background resource of any of the ImageButtons
 *          on the game board. The integer array is used for checking if the game board is in the
 *          winning configuration, and switching board squares. The following code snippet is a good
 *          example, where a board square and the square directly below it switch positions:
 *              buttons[row][col].setBackgroundResource(images[row + 1][col]);
 *              buttons[row + 1][col].setBackgroundResource(images[row][col]);
 * This program has three important parts. The main activity, which is this class, the button handler,
 * and the board model. The main activity initializes the program
 */

package com.e.pugliell21_fifteensquare;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import static com.e.pugliell21_fifteensquare.BoardModel.BOARD_HEIGHT;
import static com.e.pugliell21_fifteensquare.BoardModel.BOARD_WIDTH;

/**
 * This class is the FifteenSquare game's main thread.
 * Program execution begins here. More specifically, this class includes the onCreate() method, which
 * initializes the activity's layout, all the buttons therein, and activates the on-click-listener
 * for all the buttons.
 */
public class MainActivity extends AppCompatActivity
{
	/**
	 * This method initializes the FifteenSquare game.
	 * More specifically, this method initializes the activity's layout, creates references to all the
	 * image buttons therein, and creates new threads which all listen for a button click event, via
	 * activating those buttons' on-click-listening functionality.
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// call AppCompatActivity's onCreate() method
		super.onCreate(savedInstanceState);

		// set the screen's layout to activity_main.xml
		setContentView(R.layout.activity_main);



		// get the application's context for sending toast messages in BoardModel.java
		Context app_context = getApplicationContext();



		// create references to each of the "Shuffle" & "Auto-Win" buttons
		Button shuffle = findViewById(R.id.Shuffle);
		Button auto_win = findViewById(R.id.AutoWin);



		// create references to each of the game board's ImageButtons
		ImageButton[][] buttons = new ImageButton[BOARD_HEIGHT][BOARD_WIDTH];

		buttons[0][0] = findViewById(R.id.Square00);
		buttons[0][1] = findViewById(R.id.Square01);
		buttons[0][2] = findViewById(R.id.Square02);
		buttons[0][3] = findViewById(R.id.Square03);

		buttons[1][0] = findViewById(R.id.Square10);
		buttons[1][1] = findViewById(R.id.Square11);
		buttons[1][2] = findViewById(R.id.Square12);
		buttons[1][3] = findViewById(R.id.Square13);

		buttons[2][0] = findViewById(R.id.Square20);
		buttons[2][1] = findViewById(R.id.Square21);
		buttons[2][2] = findViewById(R.id.Square22);
		buttons[2][3] = findViewById(R.id.Square23);

		buttons[3][0] = findViewById(R.id.Square30);
		buttons[3][1] = findViewById(R.id.Square31);
		buttons[3][2] = findViewById(R.id.Square32);
		buttons[3][3] = findViewById(R.id.Square33);



		// create an instance of the ButtonHandler.java class, used for listening for button press events
		ButtonHandler handler = new ButtonHandler(buttons, app_context);



		// set the on-click-listener for the "Shuffle" & "Auto-Win" buttons, thereby creating 2 new
		// threads which listen for button-pressed-events
		shuffle.setOnClickListener(handler);
		auto_win.setOnClickListener(handler);



		// set the on-click-listener for all the game board's ImageButtons, thereby creating 16 new
		// threads which listen for button-pressed-events
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				buttons[row][col].setOnClickListener(handler);
	}
}
