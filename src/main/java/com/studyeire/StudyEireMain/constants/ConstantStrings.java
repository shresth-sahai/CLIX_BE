package com.studyeire.StudyEireMain.constants;

public class ConstantStrings {
    // APIs
    public static final String API_V1 = "/api/v1";

    public static final String API_V1_AUTH = API_V1 + "/auth";

    public static final String API_V1_USERS = API_V1 + "/users";

    public static final String API_V1_STUDENTS = API_V1 + "/students";

    public static final String API_V1_SYLLABUS = API_V1 + "/syllabus";

    public static final String API_V1_TEACHERS = API_V1 + "/teachers";

    public static final String API_V1_PARENTS = API_V1 + "/parents";

    public static final String API_V1_ADMIN = API_V1 + "/admin";

    public static final String REGISTER = "/register";

    public static final String SIGNIN = "/signin";
    public static final String API_CONNECTION_FORM = "/connection-form";

    public static final String API_CONNECTION_FORM_WITH_ID = "/connection-form/{id}";

    public static final String API_CONNECTION_FORM_RESOLVE = "/connection-form/{id}/resolve";

    // Messages
    public static final String CONNECTION_FORM_IS_INVALID = "Connection form is invalid.";

    public static final String CONNECTION_FORM_NAME_INVALID = "Name in connection form is invalid.";

    public static final String CONNECTION_FORM_EMAIL_INVALID = "Email in connection form is invalid.";

    public static final String CONNECTION_FORM_DESCRIPTION_INVALID = "Description in connection form is invalid.";

    public static final String CONNECTION_FORM_RESOLVED_OK = "Successfully resolved connection form ID: ";

    public static final String CONNECTION_FORM_NOT_FOUND = "Connection form not found ID: ";

    public static final String CONNECTION_FORM_INTERNAL_ERROR = "Error occurred while resolving connection form ID: ";

    public static final String USER_REGISTER_SUCCESS = "User registered successfully.";

    public static final String USER_NOT_FOUND = "Username not found";

    public static final String USER_FOUND_ERROR = "Error while getting user.";

    public static final String USER_FOUND_SUCCESS = "Successfully retrieved user.";

    public static final String BAD_USER_UPDATE_REQUEST = "Bad user update request.";

    public static final String ERROR_PROCESSING_REQUEST = "Error happened processing your request.";

    public static final String USER_DELETE_SUCCESS = "User successfully deleted.";
    // Regex
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$";
}
