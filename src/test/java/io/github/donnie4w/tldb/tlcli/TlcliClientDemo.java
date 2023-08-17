/*
 * Copyright 2023 tldb Author. All Rights Reserved.
 * email: donnie4w@gmail.com
 * https://githuc.com/donnie4w/tldb
 * https://githuc.com/donnie4w/tlcli-j
 */

package io.github.donnie4w.tldb.tlcli;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class TlcliClientDemo {
    public static Logger logger = Logger.getLogger("TestClient");
    Client client;

    public TlcliClientDemo() {
        client = new Client();
        client.newConnect(true, "127.0.0.1", 7100, "mycli=123");
    }

    @Test
    public void TestCreateTable() throws TlException {
        Map<String, ColumnType> cmap = new HashMap<>();
        cmap.put("classroom", ColumnType.STRING);
        cmap.put("teacher", ColumnType.BINARY);
        cmap.put("level", ColumnType.INT16);
        cmap.put("number", ColumnType.INT64);
        client.createTable("school", cmap, new String[]{"classroom", "level"});
        Ack ack = client.createTable("school", cmap, new String[]{"classroom", "teacher"});
        logger.info(ack.toString());
    }

    @Test
    public void TestUpdate() throws TlException {
        Map<String, byte[]> m = new HashMap<>();
        m.put("classroom", ("class111").getBytes(StandardCharsets.UTF_8));
        m.put("teacher", ("teacher111").getBytes(StandardCharsets.UTF_8));
        m.put("level", ByteBuffer.allocate(Short.BYTES).order(ByteOrder.BIG_ENDIAN).putShort((short) (1 << 5)).array());
        AckBean ack = client.update("school", 2, m);
        logger.info("ack >> " + ack.toString());
    }

    @Test
    public void TestDelete() throws TlException {
        AckBean ack = client.delete("school", 5);
        logger.info("ack >> " + ack.toString());
    }

    @Test
    public void TestAlter() throws TlException {
        Map<String, ColumnType> cmap = new HashMap<>();
        cmap.put("classroom", ColumnType.STRING);
        cmap.put("teacher", ColumnType.BINARY);
        cmap.put("student", ColumnType.BINARY);
        cmap.put("level", ColumnType.INT16);
        cmap.put("number", ColumnType.INT64);
        Ack ack = client.alterTable("school", cmap, new String[]{"classroom", "teacher"});
        logger.info(ack.toString());
    }

    @Test
    public void TestSelectById() throws TlException {
        DataBean db = client.selectById("school", 1);
        System.out.println("id >> " + db.getId());
        for (String k : db.getTBean().keySet()) {
            System.out.println(k + " >> " + new String(db.getTBean().get(k).array(), StandardCharsets.UTF_8));
        }
    }


    @Test
    public void TestSelectByIdx() throws TlException {
        DataBean db = client.selectByIdx("school", "teacher", "teacher0".getBytes(StandardCharsets.UTF_8));
        System.out.println("id >> " + db.getId());
        for (String k : db.getTBean().keySet()) {
            System.out.println(k + " >> " + new String(db.getTBean().get(k).array(), StandardCharsets.UTF_8));
        }
    }


    @Test
    public void TestSelectsByIdLimit() throws TlException {
        List<DataBean> dblist = client.selectsByIdLimit("school", 0, 20);
        if (dblist != null) {
            for (DataBean _db : dblist) {
                StringBuffer sb = new StringBuffer();
                sb.append("id:").append(_db.getId()).append(",");
                for (String key : _db.getTBean().keySet()) {
                    sb.append(key).append(":").append(new String(_db.getTBean().get(key).array(), StandardCharsets.UTF_8)).append(" ");
                }
                logger.info(sb.toString());
            }
        }
    }

    @Test
    public void TestSelectByIdxLimit() throws TlException {
        List<byte[]> list = new ArrayList<>();
        list.add("teacher0".getBytes(StandardCharsets.UTF_8));
        list.add("teacher1".getBytes(StandardCharsets.UTF_8));
        list.add("teacher2".getBytes(StandardCharsets.UTF_8));
        List<DataBean> dblist = client.selectByIdxLimit("school", "teacher", list, 0, 20);
        if (dblist != null) {
            for (DataBean _db : dblist) {
                StringBuffer sb = new StringBuffer();
                sb.append("id:").append(_db.getId()).append(",");
                for (String key : _db.getTBean().keySet()) {
                    sb.append(key).append(":").append(new String(_db.getTBean().get(key).array(), StandardCharsets.UTF_8)).append(" ");
                }
                logger.info(sb.toString());
            }
        }
    }

    @Test
    public void TestSelectAllByIdx() throws TlException {
        List<DataBean> dblist = client.selectAllByIdx("school", "teacher", "teacher0".getBytes(StandardCharsets.UTF_8));
        if (dblist != null) {
            for (DataBean _db : dblist) {
                StringBuffer sb = new StringBuffer();
                sb.append("id:").append(_db.getId()).append(",");
                for (String key : _db.getTBean().keySet()) {
                    sb.append(key).append(":").append(new String(_db.getTBean().get(key).array(), StandardCharsets.UTF_8)).append(" ");
                }
                logger.info(sb.toString());
            }
        }
    }

    @Test
    public void TestShowAllTables() throws TlException {
        List<TableBean> list = client.showAllTables();
        for (TableBean tb : list) {
            System.out.println(tb.name + " ");
            System.out.println(tb.getColumns().toString());
            System.out.println(tb.getIdx().toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.newConnect(true, "127.0.0.1", 7100, "mycli=123");
        Map<String, ColumnType> cmap = new HashMap<>();
        cmap.put("classroom", ColumnType.STRING);
        cmap.put("teacher", ColumnType.BINARY);
        cmap.put("level", ColumnType.INT16);
        cmap.put("number", ColumnType.INT64);
        client.createTable("school", cmap, new String[]{"classroom", "level"});
        for (int i = 0; i < 5; i++) {
            Map<String, byte[]> m = new HashMap<>();
            m.put("classroom", ("class" + i).getBytes(StandardCharsets.UTF_8));
            m.put("teacher", ("teacher" + i).getBytes(StandardCharsets.UTF_8));
            m.put("level", ByteBuffer.allocate(Short.BYTES).order(ByteOrder.BIG_ENDIAN).putShort((short) (1 << 10)).array());
            m.put("number", ByteBuffer.allocate(Long.BYTES).order(ByteOrder.BIG_ENDIAN).putLong(1L << 33).array());
            client.insert("school", m);
        }

        Map<String, byte[]> m = new HashMap<>();
        m.put("classroom", ("class111").getBytes(StandardCharsets.UTF_8));
        m.put("teacher", ("teacher111").getBytes(StandardCharsets.UTF_8));
        m.put("level", ByteBuffer.allocate(Short.BYTES).order(ByteOrder.BIG_ENDIAN).putShort((short) (1 << 5)).array());
        client.update("school", 1, m);

        System.out.println("selectId>>" + client.selectId("school"));
        DataBean db = client.selectById("school", 1);
        System.out.println("id >> " + db.getId());
        for (Map.Entry<String, ByteBuffer> me : db.getTBean().entrySet()) {
            System.out.println(me.getKey() + " >> " + new String(me.getValue().array(), StandardCharsets.UTF_8));
        }
        List<byte[]> list = new ArrayList<>();
        list.add("teacher0".getBytes(StandardCharsets.UTF_8));
        list.add("teacher1".getBytes(StandardCharsets.UTF_8));
        list.add("teacher2".getBytes(StandardCharsets.UTF_8));
        List<DataBean> dblist = client.selectByIdxLimit("school", "teacher", list, 0, 20);
        if (dblist != null) {
            for (DataBean _db : dblist) {
                StringBuffer sb = new StringBuffer();
                sb.append("id:").append(_db.getId()).append(",");
                for (String key : _db.getTBean().keySet()) {
                    sb.append(key).append(":").append(new String(_db.getTBean().get(key).array(), StandardCharsets.UTF_8)).append(" ");
                }
                logger.info(sb.toString());
            }
        }
        Thread.sleep(60 * 1000);
    }
}