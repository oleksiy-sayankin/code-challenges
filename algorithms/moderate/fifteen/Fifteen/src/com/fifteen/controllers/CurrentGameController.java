package com.fifteen.controllers;


public class CurrentGameController extends AbstractGameController {
  //--------------------------------------------------------------------------------------------------------------------------------------------
  public static void setInstance(AbstractGameController aInstance) {
    instance = aInstance;
  }

  //--------------------------------------------------------------------------------------------------------------------------------------------
  public static AbstractGameController getInstance() {
    return instance;
  }

}
