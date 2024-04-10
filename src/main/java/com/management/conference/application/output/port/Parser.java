package com.management.conference.application.output.port;

import java.util.List;


public interface Parser<T1,T2> {
    List<T1> execute(T2 data);
}
