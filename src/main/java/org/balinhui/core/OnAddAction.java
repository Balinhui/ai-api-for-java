package org.balinhui.core;

@FunctionalInterface
public interface OnAddAction<E> {
    void onAdd(E e);
}
