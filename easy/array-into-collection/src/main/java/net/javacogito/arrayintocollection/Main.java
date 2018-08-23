package net.javacogito.arrayintocollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Main {
  public static void main(String[] args) {
    Object[] objects = { new Object(), new Object(), new Object() };
    Collection<Object> collection = new ArrayList<>();
    collection.add(new Object());
    System.out.println("Array size is " + objects.length);
    System.out.println("Collection size before adding elements from an array is " + collection.size());
    addArrayIntoCollection(objects, collection);
    System.out.println("Collection size after adding elements from an array is " + collection.size());
  }

  public static void addArrayIntoCollection(Object[] objects, Collection<Object> collection) {
    collection.addAll(new ArrayList<>(Arrays.asList(objects)));
  }
}


