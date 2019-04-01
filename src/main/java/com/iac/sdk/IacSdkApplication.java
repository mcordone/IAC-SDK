package com.iac.sdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iac.sdk.client.RestClient;
import com.iac.sdk.consumer.EventConsumer;
import com.iac.sdk.domain.Event;
import com.iac.sdk.producer.EventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IacSdkApplication
{
    private static Logger LOGGER = LoggerFactory.getLogger(IacSdkApplication.class);

    public static void main( String[] args )
    {
        //load and create events
        List<Event> eventData = loadEventData();

        BlockingQueue<Event> queue = new LinkedBlockingQueue<>();

        new Thread(new EventProducer(queue, eventData)).start();
        new Thread(new EventConsumer(queue, new RestClient())).start();
        new Thread(new EventConsumer(queue, new RestClient())).start();
    }

    /**
     * Util method to load mock event data.
     * @return List of events
     */
    private static List<Event> loadEventData() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Event> eventData = new ArrayList<>();
        try {
            InputStream in = new FileInputStream("src/main/resources/eventData.json");
            if(in != null){
                eventData = objectMapper.readValue(in, new TypeReference<List<Event>>(){});
            }
        } catch (IOException e) {
            LOGGER.error("Application event mock data load failed");
            e.printStackTrace();
        }

        return eventData;
    }
}
