package com.fifteen.controllers;

import com.fifteen.gamefields.ClassicGameField;

public class ClassicGameController extends AbstractGameController {
  static {
    instance = new ClassicGameController();
  }

  //----------------------------------------------------------------------------------------------------------------
  public static AbstractGameController getInstance() {
    return instance;
  }

  //----------------------------------------------------------------------------------------------------------------
  private ClassicGameController() {
    gameField = ClassicGameField.getInstance();
  }
}
