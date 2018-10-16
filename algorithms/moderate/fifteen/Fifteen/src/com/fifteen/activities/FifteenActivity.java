package com.fifteen.activities;


import com.fifteen.R;
import com.fifteen.R.drawable;
import com.fifteen.R.id;
import com.fifteen.R.layout;
import com.fifteen.R.string;
import com.fifteen.common.GlobalSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import static com.fifteen.common.Constants.*;

public class FifteenActivity extends ListActivity {
  private final String TAG = "FifteenActivity";
  private String[] mainMenuItemsArray;

  /**
   * Called when the activity is first created.
   */
  //-----------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initMainMenuItems();
    setContentView(R.layout.main);
    Context mainContext = this;
    int textViewResourceId = android.R.layout.simple_expandable_list_item_1;
    ArrayAdapter<String> menuItemsAdapter = new ArrayAdapter<String>(mainContext, textViewResourceId, mainMenuItemsArray);
    setListAdapter(menuItemsAdapter);
    GlobalSettings.getInstance().putInteger(GAME_TYPE, GAME_TYPE_CLASSIC_GAME);

  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public void onListItemClick(ListView listView, View view, int position, long id) {
    switch (position) {
      case MENU_ITEM_POSITION_START_GAME:
        startGame();
        break;
      case MENU_ITEM_POSITION_SETTINGS:
        showSettings();
        break;
      case MENU_ITEM_POSITION_ABOUT:
        showAboutInfo();
        break;
      case MENU_ITEM_POSITION_EXIT:
        closeApp();
        break;
    }
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {
      case DIALOG_ID_ABOUT:
        return createAboutDialog();
      default:
        return null;
    }
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  private Dialog createAboutDialog() {
    LayoutInflater inflater = getLayoutInflater();
    View layout = inflater.inflate(R.layout.about, (ViewGroup) findViewById(R.id.about));

    TextView author = (TextView) layout.findViewById(R.id.textViewAuthor);
    author.setText(R.string.author);
    TextView authorValue = (TextView) layout.findViewById(R.id.textViewAuthorValue);
    authorValue.setText(R.string.author_value);
    TextView version = (TextView) layout.findViewById(R.id.textViewVersion);
    version.setText(R.string.version);
    TextView versionValue = (TextView) layout.findViewById(R.id.textViewVersionValue);
    versionValue.setText(R.string.version_value);
    ImageView imageInfo = (ImageView) layout.findViewById(R.id.image_info);
    imageInfo.setImageResource(R.drawable.ic_menu_info);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(layout);
    builder.setMessage(R.string.about);
    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    });
    return builder.create();
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  private void initMainMenuItems() {
    mainMenuItemsArray = new String[]{
      getResources().getString(R.string.start_game),
      getResources().getString(R.string.about),
      getResources().getString(R.string.settings),
      getResources().getString(R.string.exit)
    };
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  private void closeApp() {
    Log.i(TAG, "closeApp() started");
    finish();
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  private void showAboutInfo() {
    Log.i(TAG, "showAboutInfo() started");
    showDialog(DIALOG_ID_ABOUT);
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  private void startGame() {
    Log.i(TAG, "startGame() started");
    Intent intent = new Intent(FifteenActivity.this, GameFieldActivity.class);
    startActivity(intent);
  }

  //-----------------------------------------------------------------------------------------------------------------------------------------
  private void showSettings() {
    Log.i(TAG, "showSettings() started");
    Intent intent = new Intent(FifteenActivity.this, SettingsActivity.class);
    startActivity(intent);

  }
}