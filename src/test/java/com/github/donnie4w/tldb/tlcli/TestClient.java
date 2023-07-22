/*
 * Copyright 2023 tldb Author. All Rights Reserved.
 * email: donnie4w@gmail.com
 * https://githuc.com/donnie4w/tldb
 * https://githuc.com/donnie4w/tlcli-j
 */

package com.github.donnie4w.tldb.tlcli;

import org.apache.thrift.TException;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class TestClient {
    public static Logger logger = Logger.getLogger("TestClient");
    Client client;

    public TestClient() {
        client = new Client();
        client.newConnect(true, "192.168.2.108", 7001, "mycli=123");
    }

    @Test
    public void TestCreateTable() throws TException {
        Ack ack = client.createTable("school", new String[]{"classroom", "teacher", "student"}, new String[]{"classroom", "teacher"});
        logger.info(ack.toString());
    }


    @Test
    public void TestUpdate() throws TException {
        Map<String, byte[]> colmap = new HashMap<>();
        colmap.put("classroom", ("class java").getBytes(StandardCharsets.UTF_8));
        colmap.put("teacher", ("teacher java").getBytes(StandardCharsets.UTF_8));
        colmap.put("student", ("student java").getBytes(StandardCharsets.UTF_8));
        colmap.put("level", ("level java").getBytes(StandardCharsets.UTF_8));
        AckBean ack = client.update("school", 5, colmap);
        logger.info("ack >> " + ack.toString());
    }

    @Test
    public void TestDelete() throws TException {
        AckBean ack = client.delete("school", 5);
        logger.info("ack >> " + ack.toString());
    }

    @Test
    public void TestAlter() throws TException {
        Ack ack = client.alterTable("school", new String[]{"classroom", "teacher", "student", "level"}, new String[]{"classroom", "teacher"});
        logger.info(ack.toString());
    }

    @Test
    public void TestSelectById() throws TException {
        DataBean db = client.selectById("school", 1);
        System.out.println("id >> " + db.getId());
        for (String k : db.getTBean().keySet()) {
            System.out.println(k + " >> " + new String(db.getTBean().get(k).array(), StandardCharsets.UTF_8));
        }
    }


    @Test
    public void TestSelectByIdx() throws TException {
        DataBean db = client.selectByIdx("school", "teacher", "teacher0".getBytes(StandardCharsets.UTF_8));
        System.out.println("id >> " + db.getId());
        for (String k : db.getTBean().keySet()) {
            System.out.println(k + " >> " + new String(db.getTBean().get(k).array(), StandardCharsets.UTF_8));
        }
    }


    @Test
    public void TestSelectsByIdLimit() throws TException {
        List<DataBean> dblist = client.SelectsByIdLimit("school", 0, 20);
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
    public void TestSelectByIdxLimit() throws TException {
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
    public void TestSelectAllByIdx() throws TException {
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
    public void TestShowAllTables() throws TException {
        List<TableBean> list = client.showAllTables();
        for (TableBean tb : list) {
            System.out.println(tb.name + " ");
            System.out.println(tb.getColumns().toString());
            System.out.println(tb.getIdx().toString());
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.newConnect(true, "192.168.2.108", 7100, "mycli=123");
        client.createTable("school", new String[]{"classroom", "teacher", "student"}, new String[]{"classroom", "teacher"});
        for (int i = 0; i < 10; i++) {
            Map<String, byte[]> m = new HashMap<>();
            m.put("classroom", ("class" + i).getBytes(StandardCharsets.UTF_8));
            m.put("teacher", ("teacher" + i).getBytes(StandardCharsets.UTF_8));
            m.put("student", ("student" + i).getBytes(StandardCharsets.UTF_8));
            client.insert("school", m);
        }
        System.out.println("selectId>>"+client.selectId("school"));
        DataBean db = client.selectById("school", 1);
        System.out.println("id >> " + db.getId());
        for (String k : db.getTBean().keySet()) {
            System.out.println(k + " >> " + new String(db.getTBean().get(k).array(), StandardCharsets.UTF_8));
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