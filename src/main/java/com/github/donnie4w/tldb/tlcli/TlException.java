/*
 * Copyright 2023 tldb Author. All Rights Reserved.
 * email: donnie4w@gmail.com
 */
package com.github.donnie4w.tldb.tlcli;

public class TlException extends Exception{
    private static final long serialVersionUID = 1L;

    public TlException() {
        super();
    }

    public TlException(String message) {
        super(message);
    }

    public TlException(Throwable cause) {
        super(cause);
    }

    public TlException(String message, Throwable cause) {
        super(message, cause);
    }
}
