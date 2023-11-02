package com.addy.rest.app.exception;

import jakarta.servlet.http.HttpServletRequest;
import javassist.NotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class ExceptionAdviser {
    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionAdviser.class);


    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public @ResponseBody ExceptionResponse handleInternalAuthenticationServiceException(final InternalAuthenticationServiceException exception, final HttpServletRequest request) {
        LOGGER.error("@@@ Entered 'handleInternalAuthenticationServiceException' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.FORBIDDEN, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public @ResponseBody ExceptionResponse handleBadCredentialsException(final BadCredentialsException exception, final HttpServletRequest request) {
        LOGGER.error("@@@ Entered 'handleBadCredentialsException' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.FORBIDDEN, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleUsernameNotFoundException(final UsernameNotFoundException exception, final HttpServletRequest request) {
        LOGGER.error("@@@ Entered 'handleUsernameNotFoundException' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.NOT_FOUND, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody ExceptionResponse handleResourceNotFoundException(final NotFoundException exception, final HttpServletRequest request) {
        LOGGER.error("@@@ Entered 'handleResourceNotFoundException' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.NOT_FOUND, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    @ExceptionHandler(AddyValidationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleResourceValidation(final AddyValidationException exception, final HttpServletRequest request) {
        LOGGER.error("@@@ Entered 'handleResourceValidation' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.BAD_REQUEST, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody ExceptionResponse handleResourceIllegalArgument(final IllegalArgumentException exception, final HttpServletRequest request) {
        LOGGER.error("@@@ Entered 'handleResourceIllegalArgument' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.CONFLICT, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ExceptionResponse handleResourceInternalServerError(final Exception exception, final HttpServletRequest request) {
        LOGGER.error("@@@ Entered 'handleResourceInternalServerError' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionResponse handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception, final HttpServletRequest request) {
        LOGGER.error("Entered 'handleResourceIllegalArgument' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.BAD_REQUEST, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public @ResponseBody ExceptionResponse handleResourceAccessDenied(final AccessDeniedException exception, final HttpServletRequest request) {
        LOGGER.error("Entered 'handleResourceAccessDenied' in ExceptionAdviser");
        ExceptionResponse error = buildExceptionResponse(HttpStatus.FORBIDDEN, exception, request);
        printLog(request, ExceptionUtils.getStackTrace(exception));
        return error;
    }

    private ExceptionResponse buildExceptionResponse(HttpStatus httpStatus, Exception exception, HttpServletRequest request) {
        ExceptionResponse error = new ExceptionResponse();

        error.setPath(request.getRequestURL().toString());
        error.setStatus(String.valueOf(httpStatus.value()));
        error.setMessage(exception.getMessage());
        error.setError(httpStatus.name());
        error.setTimestamp(getTimeStamp());

        return error;
    }

    protected String getTimeStamp() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss'Z'");
        return formatter.format(new Date());
    }

    protected void printLog(final HttpServletRequest request, String msg) {
        LOGGER.error("Method: {}, URL: {}, Failed with: {}", request.getMethod(), request.getRequestURL(), msg);
    }

}
