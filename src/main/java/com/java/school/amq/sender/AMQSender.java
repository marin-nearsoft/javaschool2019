package com.java.school.amq.sender;

import java.util.List;

public interface AMQSender<T> {
    List<T> get();
}
