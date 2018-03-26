package com.booking.replication.model.cell;

/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Jingqi Xu
 */

/**
 *
 * @author Jingqi Xu
 */

/**
 * Copied from: https://raw.githubusercontent.com/whitesock/open-replicator/master/src/main/java/com/google/code/or/common/glossary/column/YearColumn.java
 *              and renamed YearColumn to YearCell
 */
public final class YearCell implements Cell {
    //
    private static final long serialVersionUID = 6428744630692270846L;

    //
    private static final YearCell[] CACHE = new YearCell[255];

    static {
        for (int i = 0; i < CACHE.length; i++) {
            CACHE[i] = new YearCell(i + 1900);
        }
    }

    //
    private final int value;

    /**
     *
     */
    private YearCell(int value) {
        this.value = value;
    }

    /**
     *
     */
    @Override
    public String toString() {
        return String.valueOf(this.value);
    }

    /**
     *
     */
    public Integer getValue() {
        return this.value;
    }

    /**
     *
     */
    public static final YearCell valueOf(int value) {
        final int index = value - 1900;
        return (index >= 0 && index < CACHE.length) ? CACHE[index] : new YearCell(value);
    }
}