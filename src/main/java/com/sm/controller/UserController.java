package com.sm.controller;

import com.sm.exception.RetryException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    // it will 3 more times after first attempt is failed
    @GetMapping(value = "/retry")
    @Retryable(value = {RetryException.class}, maxAttempts = 4, label = "Retry API", backoff = @Backoff(delay = 5000))
    public void testRetry() throws RetryException {
        System.out.println("Retry And Recover");
        throwRetryException();
    }

    // it will not retry because of exclude
    @GetMapping(value = "/retry-exclude")
    @Retryable(value = {RetryException.class}, maxAttempts = 4, exclude = RetryException.class, label = "Retry Exclude API", backoff = @Backoff(delay = 5000))
    public void excludeRetryAttempt() throws RetryException {
        System.out.println("Exclude Retry And Run Recover");
        throwRetryException();
    }


    @GetMapping(value = "/retry-params/{id}")
    @Retryable(value = {RetryException.class}, maxAttempts = 2, label = "Retry API", backoff = @Backoff(delay = 5000), recover = "catchRecoverWithParams")
    public void testRetryWithParams(@PathVariable Long id) throws RetryException {
        System.out.println("Retry with Params And Recover with params: " + id);
        throwRetryException();
    }

    public void throwRetryException() throws RetryException {
        if (true) {
            throw new RetryException("Failed, retry again.");
        }
    }

    @Recover
    public void catchRetryException(RetryException ex) {
        System.out.println("Retry attempt is finshed : " + ex.getMessage());
    }

    @Recover
    public void catchRecoverWithParams(RetryException ex, Long id) {
        System.out.println("Catch recover with path value: " + id);
    }
}
