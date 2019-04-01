package com.iac.sdk.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iac.sdk.config.Property;
import com.iac.sdk.domain.Event;
import com.iac.sdk.exception.RestClientException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class RestClient {

    private CloseableHttpClient httpClient;
    private HttpPost httpPost;
    private ObjectMapper objectMapper;
    private String apiUrl;

    public RestClient() {
        apiUrl = Property.getApiUrl();
        httpClient = HttpClients.createDefault();
        httpPost = createHttpPostWithHeaders();
        objectMapper = new ObjectMapper();
    }

    /**
     * create HttpPost object and add basic headers
     * @return HttpPost
     */
    public HttpPost createHttpPostWithHeaders() {
        httpPost = new HttpPost(apiUrl);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        return httpPost;
    }

    /**
     * Take an Event as parameter to be used as the Post payload (entity) for httpPost
     * @param event
     * @return CloseableHttpResponse
     */
    public CloseableHttpResponse executePostWithEntity(Event event) {
        CloseableHttpResponse response;

        try {
            String eventJson = objectMapper.writeValueAsString(event);
            StringEntity entity = new StringEntity(eventJson);
            httpPost.setEntity(entity);
            response = httpClient.execute(httpPost);
        }
        catch (IOException e) {
            throw new RestClientException(e.getMessage(), e);
        }

        return response;
    }
}
