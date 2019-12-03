package com.shine.netty.intergration.server;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  存储从节点的状态的Map，采用饱汉单例模式，每个主节点各有一个Map。
 */
public class ClientStateMap {
    class ServiceState {
        long timestamp;
        boolean isBusy = false;

        public ServiceState(long timestamp) {
            this.timestamp = timestamp;
        }

        public ServiceState(long timestamp, boolean isBusy) {
            this.timestamp = timestamp;
            this.isBusy = isBusy;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public boolean isBusy() {
            return isBusy;
        }

        public void setBusy(boolean busy) {
            isBusy = busy;
        }
    }

    private static Map<InetSocketAddress, ServiceState> serviceMap = new ConcurrentHashMap<>();
    private static ClientStateMap stateMap = new ClientStateMap();

    private ClientStateMap() {
    }

    public static ClientStateMap getStateMap() {
        return stateMap;
    }

    /**
     * 获取所有服务的IP地址
     *
     * @return 服务IP地址的Set
     */
    public Set<InetSocketAddress> getIpSet() {
        return serviceMap.keySet();
    }

    public boolean update(InetAddress addr, String port, String timestamp, String isBusy) {
        boolean ans;
        try {
            InetSocketAddress ip = new InetSocketAddress(addr, Integer.parseInt(port));
            ans = update(ip, Long.parseLong(timestamp), Boolean.parseBoolean(isBusy));
        } catch (Exception e) {
            System.out.println("传输注册报文有误");
            ans = false;
        }
        return ans;
    }

    /**
     * 更新服务端列表
     *
     * @param ip        服务端地址
     * @param timestamp 服务端存活时刻
     * @return 是否更新成功 true：更新成功 false：更新失败，消息重复或过时
     */
    public boolean update(InetSocketAddress ip, long timestamp, boolean isBusy) {
        boolean update = false;
        ServiceState oldState = serviceMap.getOrDefault(ip, null);
        if (oldState != null) {
            long oldStamp = oldState.getTimestamp();
            if (timestamp > oldStamp) {
                oldState.setTimestamp(timestamp);
                oldState.setBusy(isBusy);
                update = true;
            }
        } else {
            ServiceState state = new ServiceState(timestamp, isBusy);
            serviceMap.put(ip, state);
            update = true;
        }
        return update;
    }
}
