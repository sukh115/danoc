package com.danoc.danoc.repository.resultSet;

import java.sql.Timestamp;

public interface BoardReadResultSet {
    Long getBoardId();
    String getTitle();
    String getCtnt();
    Long getCate();
    Timestamp getDate();
    Long getUserId();
    String getName();
}
