package org.balinhui.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ResponseList<E> implements Iterable<E>, OnAddAction<E> {
    private int objectLength = 10;
    private Object[] objects = new Object[objectLength];
    private int pointer = -1;
    private OnAddAction<E> onAddAction;

    public ResponseList() {}

    public ResponseList(@Nullable OnAddAction<E> addAction) {
        this.onAddAction = addAction;
    }

    public final void add(E e) {
        if (pointer >= objects.length - 1)
            expansion();
        objects[pointer + 1] = e;
        pointer++;
        onAdd(e);
    }

    @SuppressWarnings("unchecked")
    public final E get(int index) {
        Objects.checkIndex(index, size());
        return (E) objects[index];
    }

    public final E getFirst() {
        return get(0);
    }

    public final E getLast() {
        return get(pointer);
    }

    public final int size() {
        return pointer + 1;
    }

    public final boolean isEmpty() {
        return pointer == -1;
    }

    @Override
    public void onAdd(E e) {
        if (onAddAction != null) {
            onAddAction.onAdd(e);
        }
    }

    private void expansion() {
        objectLength += 10;
        objects = copyObject();
    }

    private Object[] copyObject() {
        Object[] newObject = new Object[objectLength];
        System.arraycopy(objects, 0, newObject, 0, objects.length);
        return newObject;
    }

    @Override
    public final @NotNull Iterator<E> iterator() {
        return new Iterator<>() {
            int cursor;
            int lastRet = -1;

            public boolean hasNext() {
                return cursor <= pointer;
            }

            @SuppressWarnings("unchecked")
            public E next() {
                int i = cursor;
                if (i >= size())
                    throw new NoSuchElementException();
                Object[] objects = ResponseList.this.objects;
                if (i >= objects.length)
                    throw new ConcurrentModificationException();
                cursor = i + 1;
                return (E) objects[lastRet = i];
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object object : objects) {
            if (object != null)
                sb.append(object).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }
}
