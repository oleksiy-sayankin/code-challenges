package com.javasensei.portfolio.labyrinth;

import java.util.*;

/**
 * @author oleksiy sayankin
 */
public class RenewalPriorityQueue<E> extends PriorityQueue<E>{
    public void renew(){
        List<E> list = new ArrayList<E>();
        while (!isEmpty()){
            list.add(this.poll());
        }
        clear();
        addAll(list);
    }
}
