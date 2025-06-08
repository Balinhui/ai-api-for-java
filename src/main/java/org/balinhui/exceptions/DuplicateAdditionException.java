package org.balinhui.exceptions;

public class DuplicateAdditionException extends RuntimeException {
    public DuplicateAdditionException() {
      super();
    }
    public DuplicateAdditionException(String message) {
        super(message);
    }
}
