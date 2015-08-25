package com.fifteen.activities;


import com.fifteen.R;
import com.fifteen.R.drawable;
import com.fifteen.R.string;
import com.fifteen.common.GlobalSettings;
import com.fifteen.controllers.ClassicGameController;
import com.fifteen.controllers.CurrentGameController;
import com.fifteen.controllers.ImageGameController;
import com.fifteen.views.GameFieldView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import static com.fifteen.common.Constants.*;

public class GameFieldActivity extends Activity {
	// ------------------------------------------------------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Context context = this;
		GameFieldView gameFieldView = new GameFieldView(context);
		setContentView(gameFieldView);
		findCurrentController();
		CurrentGameController.getInstance().registerObserver(gameFieldView);
		CurrentGameController.getInstance().startNewGame();
	}
	// ------------------------------------------------------------------------------------------------------------------------
	@Override
	public void onResume(){
		super.onResume();
		findCurrentController();
	}
	// ------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, MENU_ITEM_ID_NEW_GAME, Menu.NONE, getResources().getString(R.string.new_game)).setIcon(R.drawable.ic_menu_start_game);
		menu.add(Menu.NONE, MENU_ITEM_ID_BACK_TO_MENU, Menu.NONE, getResources().getString(R.string.back_to_menu)).setIcon(R.drawable.ic_menu_back);
		return (super.onCreateOptionsMenu(menu));
	}

	// ------------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_ITEM_ID_NEW_GAME:
			ClassicGameController.getInstance().startNewGame();
			break;
		case MENU_ITEM_ID_BACK_TO_MENU:
			finish();
			break;
		default:
			return false;
		}
		return true;
	}
	// ------------------------------------------------------------------------------------------------------------------------
	private void findCurrentController(){
		int gameType = (int) GlobalSettings.getInstance().getInteger(GAME_TYPE);
		switch (gameType) {
		case GAME_TYPE_CLASSIC_GAME:
			CurrentGameController.setInstance(ClassicGameController.getInstance());
			break;
		case GAME_TYPE_IMAGE_GAME:
			CurrentGameController.setInstance(ImageGameController.getInstance());
			break;
		}
		
	}
}
