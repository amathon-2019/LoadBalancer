package eight.util;

import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ConnectionPool
{
    private ConcurrentHashMap<String, AtomicInteger> connectionPool = new ConcurrentHashMap<>();

    public AtomicInteger getConnection(String key) {
        return connectionPool.get(key);
    }

    public void setConnection(String key, AtomicInteger value) {
        connectionPool.put(key, value);
    }

    public int getSize() {
       return connectionPool.size();
    }
}
