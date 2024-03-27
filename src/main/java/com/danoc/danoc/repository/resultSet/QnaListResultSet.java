package com.danoc.danoc.repository.resultSet;


import java.sql.Timestamp;

public interface QnaListResultSet {

    Long getQaId();
    String getTitle();
    Timestamp getDate();
    Long getUserId();
    String getName();

}
