package com.fifteen.common;

import static com.fifteen.common.Constants.*;

import com.fifteen.R;
import com.fifteen.R.drawable;

public class ResourceIds {
	//------------------------------------------------------------------------------------------------------------------------
	public static int findResourceIdByCellNumberAndState(int aCellNumber, boolean aIsPressed){
		int gameType = (int)GlobalSettings.getInstance().getInteger(Constants.GAME_TYPE);
		int resourceId = 0;
		switch(gameType){
		case GAME_TYPE_CLASSIC_GAME:
		if(aIsPressed){
				   switch(aCellNumber){
				   case  1: {resourceId = R.drawable.img01_pressed; break;}
				   case  2: {resourceId = R.drawable.img02_pressed; break;}
				   case  3: {resourceId = R.drawable.img03_pressed; break;}
				   case  4: {resourceId = R.drawable.img04_pressed; break;}
				   case  5: {resourceId = R.drawable.img05_pressed; break;}
				   case  6: {resourceId = R.drawable.img06_pressed; break;}
				   case  7: {resourceId = R.drawable.img07_pressed; break;}
				   case  8: {resourceId = R.drawable.img08_pressed; break;}
				   case  9: {resourceId = R.drawable.img09_pressed; break;}
				   case 10: {resourceId = R.drawable.img10_pressed; break;}
				   case 11: {resourceId = R.drawable.img11_pressed; break;}
				   case 12: {resourceId = R.drawable.img12_pressed; break;}
				   case 13: {resourceId = R.drawable.img13_pressed; break;}
				   case 14: {resourceId = R.drawable.img14_pressed; break;}
				   case 15: {resourceId = R.drawable.img15_pressed; break;}
				   case 16: {resourceId = R.drawable.img16_pressed; break;}
				   }			
			}
			else{
			   switch(aCellNumber){
			   case  1: {resourceId = R.drawable.img01; break;}
			   case  2: {resourceId = R.drawable.img02; break;}
			   case  3: {resourceId = R.drawable.img03; break;}
			   case  4: {resourceId = R.drawable.img04; break;}
			   case  5: {resourceId = R.drawable.img05; break;}
			   case  6: {resourceId = R.drawable.img06; break;}
			   case  7: {resourceId = R.drawable.img07; break;}
			   case  8: {resourceId = R.drawable.img08; break;}
			   case  9: {resourceId = R.drawable.img09; break;}
			   case 10: {resourceId = R.drawable.img10; break;}
			   case 11: {resourceId = R.drawable.img11; break;}
			   case 12: {resourceId = R.drawable.img12; break;}
			   case 13: {resourceId = R.drawable.img13; break;}
			   case 14: {resourceId = R.drawable.img14; break;}
			   case 15: {resourceId = R.drawable.img15; break;}
			   case 16: {resourceId = R.drawable.img16; break;}
			   }
			}
		break;
		case GAME_TYPE_IMAGE_GAME:
			if(aIsPressed){
				   switch(aCellNumber){
				   case  1: {resourceId = R.drawable.ein01_pressed; break;}
				   case  2: {resourceId = R.drawable.ein02_pressed; break;}
				   case  3: {resourceId = R.drawable.ein03_pressed; break;}
				   case  4: {resourceId = R.drawable.ein04_pressed; break;}
				   case  5: {resourceId = R.drawable.ein05_pressed; break;}
				   case  6: {resourceId = R.drawable.ein06_pressed; break;}
				   case  7: {resourceId = R.drawable.ein07_pressed; break;}
				   case  8: {resourceId = R.drawable.ein08_pressed; break;}
				   case  9: {resourceId = R.drawable.ein09_pressed; break;}
				   case 10: {resourceId = R.drawable.ein10_pressed; break;}
				   case 11: {resourceId = R.drawable.ein11_pressed; break;}
				   case 12: {resourceId = R.drawable.ein12_pressed; break;}
				   case 13: {resourceId = R.drawable.ein13_pressed; break;}
				   case 14: {resourceId = R.drawable.ein14_pressed; break;}
				   case 15: {resourceId = R.drawable.ein15_pressed; break;}
				   case 16: {resourceId = R.drawable.ein16_pressed; break;}
				   }			
			}
			else{
			   switch(aCellNumber){
			   case  1: {resourceId = R.drawable.ein01; break;}
			   case  2: {resourceId = R.drawable.ein02; break;}
			   case  3: {resourceId = R.drawable.ein03; break;}
			   case  4: {resourceId = R.drawable.ein04; break;}
			   case  5: {resourceId = R.drawable.ein05; break;}
			   case  6: {resourceId = R.drawable.ein06; break;}
			   case  7: {resourceId = R.drawable.ein07; break;}
			   case  8: {resourceId = R.drawable.ein08; break;}
			   case  9: {resourceId = R.drawable.ein09; break;}
			   case 10: {resourceId = R.drawable.ein10; break;}
			   case 11: {resourceId = R.drawable.ein11; break;}
			   case 12: {resourceId = R.drawable.ein12; break;}
			   case 13: {resourceId = R.drawable.ein13; break;}
			   case 14: {resourceId = R.drawable.ein14; break;}
			   case 15: {resourceId = R.drawable.ein15; break;}
			   case 16: {resourceId = R.drawable.ein16; break;}
			   }
			}

			break;
		}
		return resourceId; 

	}
	 
	 
	 
}
