package com.wongislandd.portfolio.programs.infinityindex.infra.util

enum class NetworkError(override val displayMessage: String) : Error {
    REQUEST_TIMEOUT("Request timed out"),
    UNAUTHORIZED("Unauthorized request"),
    NOT_FOUND("Resource not found"),
    CONFLICT("Network Conflict"),
    TOO_MANY_REQUESTS("Too many requests"),
    NO_INTERNET("No internet connection"),
    PAYLOAD_TOO_LARGE("Payload too large"),
    MARVEL_API_RATE_LIMITED("Marvel API Rate Limit Reached. Please try again later."),
    SERVER_ERROR("Internal Server Error"),
    COULD_NOT_CONTACT_MARVEL_API("Could not contact Marvel API"),
    SERIALIZATION("Serialization Error"),
    UNKNOWN("Unknown error");
}