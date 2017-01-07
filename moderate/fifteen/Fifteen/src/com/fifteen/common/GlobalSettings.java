package com.fifteen.common;

import java.util.HashMap;

public class GlobalSettings {
  private static HashMap<String, Object> values;
  private static GlobalSettings insatnce;

  static {
    insatnce = new GlobalSettings();
  }

  //--------------------------------------------------------------------------------------------------------------------------------------------
  private GlobalSettings() {
    values = new HashMap<String, Object>();
  }

  //--------------------------------------------------------------------------------------------------------------------------------------------
  public static GlobalSettings getInstance() {
    return insatnce;
  }

  //--------------------------------------------------------------------------------------------------------------------------------------------
  public Integer getInteger(String aKey) {
    return (Integer) values.get(aKey);
  }

  //--------------------------------------------------------------------------------------------------------------------------------------------
  public void putInteger(String aKey, Object aValue) {
    values.put(aKey, aValue);
  }

  //--------------------------------------------------------------------------------------------------------------------------------------------
  public boolean containsKey(String aKey) {
    return values.containsKey(aKey);
  }
}
