package com.endava.jpa;

import java.io.Serializable;

public interface Identifiable<T extends Serializable> {
    T getId();
}
