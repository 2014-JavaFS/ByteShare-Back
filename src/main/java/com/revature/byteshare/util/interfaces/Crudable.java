package com.revature.byteshare.util.interfaces;

public interface Crudable<O> {
    boolean update(O updatedObject);
    boolean delete(O removedObject);
}
