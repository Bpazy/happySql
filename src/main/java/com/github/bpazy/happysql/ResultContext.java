package com.github.bpazy.happysql;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ResultContext {
    @Getter private final List retList;
}
