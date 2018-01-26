package com.booking.replication.applier;

import com.booking.replication.applier.console.ConsoleEventApplier;
import com.booking.replication.applier.hbase.HBaseEventApplier;
import com.booking.replication.mysql.binlog.model.Event;

import java.io.Closeable;
import java.util.Map;
import java.util.function.Consumer;

public interface EventApplier extends Consumer<Event>, Closeable {
    enum Type {
        CONSOLE {
            @Override
            public EventApplier newInstance(Map<String, String> configuration) {
                return new ConsoleEventApplier(configuration);
            }
        },
        HBASE {
            @Override
            public EventApplier newInstance(Map<String, String> configuration) {
                return new HBaseEventApplier(configuration);
            }
        };

        public abstract EventApplier newInstance(Map<String, String> configuration);
    }

    interface Configuration {
        String TYPE = "consumer.type";
    }

    static EventApplier build(Map<String, String> configuration) {
        return Type.valueOf(
                configuration.getOrDefault(Configuration.TYPE, Type.CONSOLE.name())
        ).newInstance(configuration);
    }
}