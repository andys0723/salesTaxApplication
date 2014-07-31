package model;

import exception.ReadItemLineException;

public interface ItemFactory {
   public Item createItem(String readLine) throws ReadItemLineException;
}
