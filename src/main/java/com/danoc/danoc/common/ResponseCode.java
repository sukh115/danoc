package com.danoc.danoc.common;

import org.springframework.stereotype.Repository;

@Repository
public interface ResponseCode {
    
    String SUCCESS = "SU";

    String VALIDATION_FAIL = "VF";
    String DUPLICATE_ID = "DI";

    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";

    String MAIL_FAIL = "MF";
    String DATABASE_ERROR = "DBE";

    String NOT_FOUND = "NF";
    String USER_NOT_FOUND = "UNF";
    String USER_DETAILS_NOT_FOUND = "UDNF";

    String QNA_NOT_FOUND = "QNF";

    String DELETE_FAIL = "DF";
    String EDIT_FAIL = "EF";
}
