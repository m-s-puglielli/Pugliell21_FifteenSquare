package com.e.pugliell21_fifteensquare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.InputStream;

import static com.e.pugliell21_fifteensquare.BoardModel.BOARD_HEIGHT;
import static com.e.pugliell21_fifteensquare.BoardModel.BOARD_WIDTH;

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);










		// GET THE APPLICATION'S CONTEXT FOR SENDING TOAST MESSAGES
		Context app_context = getApplicationContext();



		// CREATE THE SHUFFLE BUTTON
		Button shuffle = (Button) findViewById(R.id.Shuffle);



		// CREATE THE IMAGE BUTTONS
		ImageButton[][] buttons = new ImageButton[BOARD_HEIGHT][BOARD_WIDTH];

		buttons[0][0] = (ImageButton) findViewById(R.id.Square00);
		buttons[0][1] = (ImageButton) findViewById(R.id.Square01);
		buttons[0][2] = (ImageButton) findViewById(R.id.Square02);
		buttons[0][3] = (ImageButton) findViewById(R.id.Square03);

		buttons[1][0] = (ImageButton) findViewById(R.id.Square10);
		buttons[1][1] = (ImageButton) findViewById(R.id.Square11);
		buttons[1][2] = (ImageButton) findViewById(R.id.Square12);
		buttons[1][3] = (ImageButton) findViewById(R.id.Square13);

		buttons[2][0] = (ImageButton) findViewById(R.id.Square20);
		buttons[2][1] = (ImageButton) findViewById(R.id.Square21);
		buttons[2][2] = (ImageButton) findViewById(R.id.Square22);
		buttons[2][3] = (ImageButton) findViewById(R.id.Square23);

		buttons[3][0] = (ImageButton) findViewById(R.id.Square30);
		buttons[3][1] = (ImageButton) findViewById(R.id.Square31);
		buttons[3][2] = (ImageButton) findViewById(R.id.Square32);
		buttons[3][3] = (ImageButton) findViewById(R.id.Square33);



		// CREATE THE HANDLER
		FifteenSquareHandler handler = new FifteenSquareHandler(buttons, app_context);



		// SET THE ON-CLICK-LISTENER FOR THE SHUFFLE BUTTON
		shuffle.setOnClickListener(handler);



		// SET THE ON-CLICK-LISTENER FOR ALL THE IMAGE BUTTONS
		for (int row = 0; row < BOARD_HEIGHT; row++)
			for (int col = 0; col < BOARD_WIDTH; col++)
				buttons[row][col].setOnClickListener(handler);
	}
}
