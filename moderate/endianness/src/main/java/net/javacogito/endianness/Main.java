package net.javacogito.endianness;

import java.nio.ByteOrder;

public class Main {

  public static void main(String[] args){
    System.out.println(endianness());
  }

  private static String endianness(){
    if (ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN)) {
      return "BigEndian";
    } else {
      return "LittleEndian";
    }
  }
}