/**
 * Copyright 2023 tldb Author. All Rights Reserved.
 * email: donnie4w@gmail.com
 * https://githuc.com/donnie4w/tldb
 * https://githuc.com/donnie4w/tlcli-j
 */
package io.github.donnie4w.tldb.tlcli;

public enum ColumnType {
    STRING("0"),INT64("1"),INT32("2"),INT16("3"),INT8("4"),
    FLOAT64("5"),FLOAT32("6"), BINARY("7"),BYTE("8");

    //UINT64("9"),UINT32("10"),UINT16("11"),UINT8("12")

    private final String value;
    private ColumnType(String value){
        this.value = value;
    }
    protected String getValue(){
        return  this.value;
    }
}
