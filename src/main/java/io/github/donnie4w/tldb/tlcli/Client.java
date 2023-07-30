/*
 * Copyright 2023 tldb Author. All Rights Reserved.
 * email: donnie4w@gmail.com
 * https://githuc.com/donnie4w/tldb
 * https://githuc.com/donnie4w/tlcli-j
 */

package io.github.donnie4w.tldb.tlcli;

import org.apache.thrift.TConfiguration;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import org.apache.thrift.TException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.nio.ByteBuffer;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

public class Client {

    public static Logger logger = Logger.getLogger("tlcli");
    private TTransport transport;
    private Icli.Client conn;
    private String host;
    private int port;
    private boolean tls;
    private String auth;

    private AtomicInteger connId = new AtomicInteger(0);

    private AtomicInteger pingNum = new AtomicInteger(0);

    private int connectTimeout = 10 * 1000;

    private int socketTimeout = 60 * 1000;

    public TConfiguration tc = new TConfiguration();

    public Client() {
    }

    public Client(int connectTimeout, int socketTimeout) {
        this.connectTimeout = connectTimeout;
        this.socketTimeout = socketTimeout;
    }

    private Ack connect(boolean tls, String host, int port, String auth) {
        try {
            this.tls = tls;
            this.host = host;
            this.port = port;
            this.auth = auth;
            if (tls) {
                X509TrustManager xtm = new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };
                SSLContext ctx = SSLContext.getInstance("SSL");
                ctx.init(null, new TrustManager[]{xtm}, null);
                SSLSocket socket = (SSLSocket) ctx.getSocketFactory().createSocket(host, port);
                TSocket ts = new TSocket(socket);
                ts.setConnectTimeout(this.connectTimeout);
                ts.setSocketTimeout(this.socketTimeout);
                transport = ts;
            } else {
                transport = new TSocket(this.tc, host, port, this.socketTimeout, this.connectTimeout);
            }
            if (!transport.isOpen()) {
                transport.open();
            }
            TProtocol protocol = new TCompactProtocol(transport);
            this.conn = new Icli.Client(protocol);
            this.pingNum.set(0);
            return this.auth(this.auth);
        } catch (Exception e) {
            return new Ack(false, 0, e.getMessage());
        }
    }

    public synchronized Ack ping(long i) throws TException {
        return this.conn.Ping(i);
    }

    public synchronized Ack auth(String s) throws TException {
        return this.conn.Auth(s);
    }

    public synchronized Ack createTable(String tableName, String[] columnsName, String[] indexs) throws TlException {
        TableBean tb = new TableBean();
        tb.setName(tableName);
        tb.columns = new HashMap();
        for (String c : columnsName) {
            tb.columns.put(c, ByteBuffer.allocate(0));
        }
        if (indexs != null) {
            tb.Idx = new HashMap();
            for (String i : indexs) {
                tb.Idx.put(i, Byte.valueOf((byte) 0));
            }
        }
        try {
            return this.conn.Create(tb);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized Ack alterTable(String tableName, String[] columnsName, String[] indexs) throws TlException {
        TableBean tb = new TableBean();
        tb.setName(tableName);
        tb.columns = new HashMap<>();
        for (String c : columnsName) {
            tb.columns.put(c, ByteBuffer.allocate(0));
        }
        if (indexs != null) {
            tb.Idx = new HashMap<>();
            for (String i : indexs) {
                tb.Idx.put(i, Byte.valueOf((byte) 0));
            }
        }
        try {
            return this.conn.Alter(tb);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized Ack drop(String name) throws TlException {
        try {
            return this.conn.Drop(name);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized long selectId(String name) throws TlException {
        try {
            return this.conn.SelectId(name);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized DataBean selectById(String name, long id) throws TlException {
        try {
            return this.conn.SelectById(name, id);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized List<DataBean> selectsByIdLimit(String name, long startId, long limit) throws TlException {
        try {
            return this.conn.SelectsByIdLimit(name, startId, limit);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized DataBean selectByIdx(String name, String column, byte[] value) throws TlException {
        try {
            return this.conn.SelectByIdx(name, column, ByteBuffer.wrap(value));
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized List<DataBean> selectAllByIdx(String name, String column, byte[] value) throws TlException {
        try {
            return this.conn.SelectAllByIdx(name, column, ByteBuffer.wrap(value));
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized List<DataBean> selectByIdxLimit(String name, String column, List<byte[]> value, long startId, long limit) throws TlException {
        List<ByteBuffer> listValue = new ArrayList<>();
        for (byte[] v : value) {
            listValue.add(ByteBuffer.wrap(v));
        }
        try {
            return this.conn.SelectByIdxLimit(name, column, listValue, startId, limit);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized AckBean update(String tableName, long id, Map<String, byte[]> columnsMap) throws TlException {
        TableBean tb = new TableBean();
        tb.setName(tableName);
        tb.setId(id);
        if (columnsMap != null) {
            tb.columns = new HashMap();
            for (String key : columnsMap.keySet()) {
                tb.columns.put(key, ByteBuffer.wrap(columnsMap.get(key)));
            }
        }
        try {
            return this.conn.Update(tb);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized AckBean delete(String tableName, long id) throws TlException {
        TableBean tb = new TableBean(tableName);
        tb.setName(tableName);
        tb.setId(id);
        try {
            return this.conn.Delete(tb);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized AckBean insert(String tableName, Map<String, byte[]> columnsMap) throws TlException {
        TableBean tb = new TableBean(tableName);
        if (columnsMap != null) {
            tb.columns = new HashMap<>();
            for (String key : columnsMap.keySet()) {
                tb.columns.put(key, ByteBuffer.wrap(columnsMap.get(key)));
            }
        }
        try {
            return this.conn.Insert(tb);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized TableBean showTable(String name) throws TlException {
        try {
            return this.conn.ShowTable(name);
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized List<TableBean> showAllTables() throws TlException {
        try {
            return this.conn.ShowAllTables();
        } catch (TException e) {
            throw new TlException(e);
        }
    }

    public synchronized void close() {
        connId.incrementAndGet();
        if (this.transport != null) {
            this.transport.close();
        }
    }

    public void newConnect(boolean tls, String host, int port, String auth) {
        int i = connId.incrementAndGet();
        Ack ack = this.connect(tls, host, port, auth);
        if (!ack.ok) {
            logger.info("connect failed:" + ack.errCode + " >> " + ack.errorDesc);
        }
        new Thread(() -> {
            while (i == connId.get()) {
                try {
                    Thread.sleep(3000);
                    this.pingNum.incrementAndGet();
                    if (this.ping(1).ok) {
                        this.pingNum.decrementAndGet();
                    }
                } catch (Exception e) {
                }
                if (this.pingNum.get() > 5 && i == connId.get()) {
                    this.reconnect();
                }
            }
        }).start();
    }

    public void reconnect() {
        try {
            if (this.transport != null) {
                this.transport.close();
            }
            logger.info("reconnect");
            this.connect(this.tls, this.host, this.port, this.auth);
        } catch (Exception e) {
        }
    }
}
