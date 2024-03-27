package com.danoc.danoc.common;

import org.springframework.stereotype.Repository;

@Repository
public interface ResponseMessage {


    String SUCCESS = "Success.";

    String VALIDATION_FAIL = "Validation failed.";
    String DUPLICATE_ID = "Duplicate Id.";

    String SIGN_IN_FAIL = "Login information mismatch.";
    String CERTIFICATION_FAIL = "Certification failed.";

    String MAIL_FAIL = "Mail send failed";
    String DATABASE_ERROR = "Database error.";

    String NOT_FOUND = "Not Found";
    String USER_NOT_FOUND = "User not found";
    String USER_DETAILS_NOT_FOUND = "User details not found";

    String QNA_NOT_FOUND = "QNA not found";

    String DELETE_FAIL = "Delete Fail";
    String EDIT_FAIL = "Edit Fail";
};
