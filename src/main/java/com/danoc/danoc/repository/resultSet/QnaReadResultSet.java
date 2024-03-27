package com.danoc.danoc.repository.resultSet;

import java.sql.Timestamp;

public interface QnaReadResultSet {

    Long getQaId();
    String getTitle();
    String getCtnt();
    Timestamp getDate();
    Long getUserId();
    String getName();

}
