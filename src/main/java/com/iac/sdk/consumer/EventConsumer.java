package com.iac.sdk.consumer;

import com.iac.sdk.client.RestClient;
import com.iac.sdk.client.RetryOnRestClientException;
import com.iac.sdk.config.Property;
import com.iac.sdk.domain.Event;
import com.iac.sdk.exception.RestClientException;
import com.iac.sdk.exception.RetryLimitException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

public class EventConsumer implements Runnable {
    private static Logger LOGGER = LoggerFactory.getLogger(EventConsumer.class);

    private BlockingQueue<Event> queue;
    private RestClient restClient;

    public EventConsumer(BlockingQueue<Event> _queue, RestClient _restClient) {
        queue = _queue;
        restClient = _restClient;
    }

    public void run() {

        try{
            while (true) {
                Event event = queue.take();
                postWithRetry(event);
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Makes an http Post request with event data as payload to event tracker rest api
     * @param event
     * @return HttpStatus code
     */
    private int postWithRetry(Event event) {

        CloseableHttpResponse response = null;
        int statusCode;

        RetryOnRestClientException retryHandler = new RetryOnRestClientException(Property.getRetryNumber(), Property.getRetryInteral());

        while (true) {
            try {
                response = restClient.executePostWithEntity(event);
                statusCode = response.getStatusLine().getStatusCode();

            } catch (RestClientException e) {
                LOGGER.info("RestClientException " + e.getMessage() + " --- Retrying.....");
                retryHandler.onError();
                continue;
            } /*catch (RetryLimitException e) {
                LOGGER.info(e.getMessage());
            }*/

            if (statusCode == HttpStatus.SC_OK) {
                break;
            }
            else {
                return statusCode;
            }
        }

        return response.getStatusLine().getStatusCode();
    }
}
