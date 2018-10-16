package com.fifteen.common;

import com.fifteen.gamefields.GameFieldState;


public interface Observer {
  void update(GameFieldState aGameFieldState);
}
