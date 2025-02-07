package amirgol.coach.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum Exceptions {
    // Authentication & Authorization (1000-1999)
    INVALID_CREDENTIALS(1001, "Invalid username or password", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED(1002, "Authentication token has expired", HttpStatus.UNAUTHORIZED),
    INSUFFICIENT_PERMISSIONS(1003, "User does not have required permissions", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(1004, "Invalid or malformed authentication token", HttpStatus.UNAUTHORIZED),
    SESSION_EXPIRED(1005, "User session has expired", HttpStatus.UNAUTHORIZED),

    // Participant Related (2000-2999)
    PARTICIPANT_NOT_FOUND(2001, "Participant profile not found", HttpStatus.NOT_FOUND),
    INVALID_CUSTOMER_DATA(2002, "Invalid participant information provided", HttpStatus.BAD_REQUEST),
    DUPLICATE_PARTICIPANT(2003, "Participant already exists in the system", HttpStatus.CONFLICT),
    PARTICIPANT_INACTIVE(2004, "Participant account is currently inactive", HttpStatus.FORBIDDEN),
    INVALID_CONTACT_INFO(2005, "Invalid participant contact information", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_UPDATE(2006, "Participant email cannot be update", HttpStatus.BAD_REQUEST),
    TOO_LONG_EMAIL(2007, "Participant email cannot use email with more than 50 characters", HttpStatus.BAD_REQUEST),
    TOO_LONG_NAME(2007, "Participant email cannot use email with more than 50 characters", HttpStatus.BAD_REQUEST),
    EMPTY_EMAIL(2008, "Email cannot be empty", HttpStatus.BAD_REQUEST),
    EMPTY_NAME(2009, "Username cannot be empty", HttpStatus.BAD_REQUEST),


    // Membership Related (3000-3999)
    MEMBERSHIP_EXPIRED(3001, "Membership has expired", HttpStatus.FORBIDDEN),
    INVALID_MEMBERSHIP_TYPE(3002, "Invalid membership type specified", HttpStatus.BAD_REQUEST),
    MEMBERSHIP_LIMIT_REACHED(3003, "Membership capacity limit reached", HttpStatus.CONFLICT),
    MEMBERSHIP_SUSPENDED(3004, "Membership is currently suspended", HttpStatus.FORBIDDEN),
    INVALID_MEMBERSHIP_UPGRADE(3005, "Invalid membership upgrade request", HttpStatus.BAD_REQUEST),

    // Payment Related (4000-4999)
    PAYMENT_FAILED(4001, "Payment transaction failed", HttpStatus.PAYMENT_REQUIRED),
    INVALID_PAYMENT_METHOD(4002, "Invalid payment method provided", HttpStatus.BAD_REQUEST),
    INSUFFICIENT_FUNDS(4003, "Insufficient funds for transaction", HttpStatus.PAYMENT_REQUIRED),
    PAYMENT_EXPIRED(4004, "Payment authorization has expired", HttpStatus.GONE),
    REFUND_FAILED(4005, "Unable to process refund request", HttpStatus.INTERNAL_SERVER_ERROR),

    // Booking & Scheduling (5000-5999)
    BOOKING_NOT_FOUND(5001, "Booking record not found", HttpStatus.NOT_FOUND),
    SCHEDULE_CONFLICT(5002, "Schedule conflict detected", HttpStatus.CONFLICT),
    INVALID_BOOKING_TIME(5003, "Invalid booking time specified", HttpStatus.BAD_REQUEST),
    BOOKING_LIMIT_EXCEEDED(5004, "Booking limit exceeded for this period", HttpStatus.CONFLICT),
    PAST_BOOKING_DATE(5005, "Cannot book for past dates", HttpStatus.BAD_REQUEST),

    // Common Exceptions (6000-6499)
    INVALID_DOMAIN_EVENT(6001, "Invalid domain event encountered", HttpStatus.BAD_REQUEST),
    INVALID_VALUE_FORMAT(6002, "Invalid value format for value object", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_FORMAT(6003, "Invalid email format", HttpStatus.BAD_REQUEST),
    INVALID_NAME_FORMAT(6004, "Invalid username format", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_FORMAT(6005, "Invalid phone number format", HttpStatus.BAD_REQUEST),
    INVALID_DATE_FORMAT(6006, "Invalid date format", HttpStatus.BAD_REQUEST),

    // Logging Exceptions (6500-6749)
    LOGGING_FAILURE(6500, "Failed to write to log", HttpStatus.INTERNAL_SERVER_ERROR),
    LOG_ROTATION_FAILURE(6501, "Failed to rotate log files", HttpStatus.INTERNAL_SERVER_ERROR),
    LOG_CONFIGURATION_ERROR(6502, "Invalid logging configuration", HttpStatus.INTERNAL_SERVER_ERROR),
    LOG_PERSISTENCE_ERROR(6503, "Failed to persist log entries", HttpStatus.INTERNAL_SERVER_ERROR),

    // Monitoring Exceptions (6750-6999)
    METRIC_COLLECTION_FAILURE(6750, "Failed to collect system metrics", HttpStatus.INTERNAL_SERVER_ERROR),
    MONITORING_SERVICE_FAILURE(6751, "Monitoring service unavailable", HttpStatus.SERVICE_UNAVAILABLE),
    ALERT_CONFIGURATION_ERROR(6752, "Invalid alert configuration", HttpStatus.INTERNAL_SERVER_ERROR),
    THRESHOLD_VIOLATION(6753, "System threshold violation detected", HttpStatus.INTERNAL_SERVER_ERROR),

    // System Related (9000-9999)
    SYSTEM_MAINTENANCE(9001, "System is under maintenance", HttpStatus.SERVICE_UNAVAILABLE),
    DATABASE_ERROR(9002, "Database operation failed", HttpStatus.INTERNAL_SERVER_ERROR),
    EXTERNAL_SERVICE_ERROR(9003, "External service integration failed", HttpStatus.BAD_GATEWAY),
    RATE_LIMIT_EXCEEDED(9004, "API rate limit exceeded", HttpStatus.TOO_MANY_REQUESTS),
    INTERNAL_SERVER_ERROR(9005, "Internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int errorStatusCode;
    private final String errorText;
    private final HttpStatus statusCode;

}
