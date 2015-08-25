package com.fifteen.activities;


import static com.fifteen.common.Constants.*;

import com.fifteen.R;
import com.fifteen.R.layout;
import com.fifteen.R.string;
import com.fifteen.common.GlobalSettings;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SettingsActivity extends ListActivity{
	private final String TAG = "FifteenActivity";
	private String[] settingsMenuItemsArray; 
	private String[] gameTypeMenuItemsArray; 
	// ------------------------------------------------------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSettingsMenuItems();
		initGameTypeMenuItems();
        setContentView(R.layout.settings);
        Context mainContext = this;
        int textViewResourceId = android.R.layout.simple_expandable_list_item_1;
        ArrayAdapter<String> menuItemsAdapter = new ArrayAdapter<String>(mainContext, textViewResourceId, settingsMenuItemsArray); 
        setListAdapter(menuItemsAdapter);  		
	}
	//-----------------------------------------------------------------------------------------------------------------------------------------
    private void initSettingsMenuItems(){
		settingsMenuItemsArray = new String[]{	
				getResources().getString(R.string.game_type)
				};		    	
    }	
	//-----------------------------------------------------------------------------------------------------------------------------------------
    private void initGameTypeMenuItems(){
		gameTypeMenuItemsArray = new String[]{	
				getResources().getString(R.string.game_type_classic),
				getResources().getString(R.string.game_type_image)
				};		    	
    }	
	//-----------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void onListItemClick(ListView listView, View view, int position, long id){ 
    	switch(position){
    	case MENU_ITEM_POSITION_GAME_TYPE:
    		showGameTypeDialog();
    		break;
        	}  	
    }  
	//-----------------------------------------------------------------------------------------------------------------------------------------
    @Override
    protected Dialog onCreateDialog(int id){
    	switch(id){
    	case DIALOG_ID_GAME_TYPE:
    		return createGameTypeDialog();
    	default:
    		return null;
    	}
    }    
	//-----------------------------------------------------------------------------------------------------------------------------------------
    private void showGameTypeDialog(){
    	Log.i(TAG, "showAboutInfo() started");   
       	showDialog(DIALOG_ID_GAME_TYPE);
    }
	//-----------------------------------------------------------------------------------------------------------------------------------------
    private Dialog createGameTypeDialog(){
    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setTitle(R.string.choose_game_type_header);
    	int currentMenuItemPosition = findCurrentMenuItemPosition();
    	builder.setSingleChoiceItems(gameTypeMenuItemsArray, currentMenuItemPosition, new DialogInterface.OnClickListener() {				
			public void onClick(DialogInterface dialog, int item) {
				switch(item){
				case MENU_ITEM_ID_CLASSIC_GAME:
					GlobalSettings.getInstance().putInteger(GAME_TYPE, GAME_TYPE_CLASSIC_GAME);					
					break;
				case MENU_ITEM_ID_IMAGE_GAME:
					GlobalSettings.getInstance().putInteger(GAME_TYPE, GAME_TYPE_IMAGE_GAME);					
					break;
				}		
			}
		});  
		builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {				
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();			
			}
		});

    	return builder.create();  	
    }
	//-----------------------------------------------------------------------------------------------------------------------------------------
    private int findCurrentMenuItemPosition(){
    	int currentGameType = GlobalSettings.getInstance().getInteger(GAME_TYPE);
    	int currentMenuItemPosition = 0;
    	switch (currentGameType){
    	case GAME_TYPE_CLASSIC_GAME:
    		currentMenuItemPosition = MENU_ITEM_POSITION_GAME_TYPE_CLASSIC;
    		break;
    	case GAME_TYPE_IMAGE_GAME:
    		currentMenuItemPosition = MENU_ITEM_POSITION_GAME_TYPE_IMAGE;
    		break;
    	}
    	return currentMenuItemPosition;
    }
}
