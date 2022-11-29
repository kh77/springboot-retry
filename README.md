# Spring Boot Retry

### Use of @Retryable and @Recover annotation

---------------------------------------------


### Retry With Recover
    
       curl --location --request GET 'localhost:8080/users/retry' \
      --data-raw ''

Output:

        Retry And Recover
        Retry And Recover
        Retry And Recover
        Retry And Recover
        Retry attempt is finshed : Failed, retry again.




### Exclude Retry


    curl --location --request GET 'localhost:8080/users/retry-exclude' \
    --data-raw ''

Output:

    Exclude Retry And Run Recover
    Retry attempt is finshed : Failed, retry again.



### Retry and Recover with Parameters
 

      curl --location --request GET 'localhost:8080/users/retry-params/3' \
       --data-raw ''


Output:

            Retry with Params And Recover with params: 3
            Retry with Params And Recover with params: 3
            Catch recover with path value: Failed, retry again.