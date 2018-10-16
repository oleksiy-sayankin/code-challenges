package com.fifteen.views;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


import static com.fifteen.common.ResourceIds.*;

public class CellView {

  private boolean isPressed = false;
  private final int number;
  private float x;
  private float y;
  private int width;
  private int height;
  private final Resources resources;
  private final Bitmap imagePressed;
  private final Bitmap imageReleased;

  //------------------------------------------------------------------------------------------------------------------------
  public CellView(Resources aResources, int aNumber) {
    number = aNumber;
    resources = aResources;
    imagePressed = BitmapFactory.decodeResource(resources, findResourceIdByCellNumberAndState(number, true));
    imageReleased = BitmapFactory.decodeResource(resources, findResourceIdByCellNumberAndState(number, false));
    width = imageReleased.getWidth();
    height = imageReleased.getHeight();
  }

  //------------------------------------------------------------------------------------------------------------------------
  public boolean isTouched(float ax, float ay) {
    return (ax >= x) && (ax <= (x + width)) && (ay >= y) && (ay <= (y + height));
  }

  //------------------------------------------------------------------------------------------------------------------------
  public void draw(Canvas canvas) {
    Bitmap image = isPressed ? imagePressed : imageReleased;
    canvas.drawBitmap(image, x, y, null);
  }

  //------------------------------------------------------------------------------------------------------------------------
  public int getNumber() {
    return number;
  }

  //------------------------------------------------------------------------------------------------------------------------
  public int getWidth() {
    return width;
  }

  //------------------------------------------------------------------------------------------------------------------------
  public int getHeight() {
    return height;
  }

  //------------------------------------------------------------------------------------------------------------------------
  public void setXY(float ax, float ay) {
    x = ax;
    y = ay;
  }

  //------------------------------------------------------------------------------------------------------------------------
  public float getX() {
    return x;
  }

  //------------------------------------------------------------------------------------------------------------------------
  public float getY() {
    return y;
  }

  //------------------------------------------------------------------------------------------------------------------------
  public void setIsPressed(boolean aIsPressed) {
    isPressed = aIsPressed;
  }

}
