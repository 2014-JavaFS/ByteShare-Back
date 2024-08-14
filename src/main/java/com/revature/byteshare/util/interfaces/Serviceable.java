package com.revature.byteshare.util.interfaces;

import java.util.List;

public interface Serviceable<O> extends Crudable<O> {
    List<O> findAll();
    O create(O newObject);
    O findById(int number);
}
