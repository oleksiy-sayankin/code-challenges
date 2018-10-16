package com.fifteen.controllers;

import com.fifteen.gamefields.ImageGameField;


public class ImageGameController extends AbstractGameController {
  static {
    instance = new ImageGameController();
  }

  //----------------------------------------------------------------------------------------------------------------
  public static AbstractGameController getInstance() {
    return instance;
  }

  //----------------------------------------------------------------------------------------------------------------
  private ImageGameController() {
    gameField = ImageGameField.getInstance();
  }
}
