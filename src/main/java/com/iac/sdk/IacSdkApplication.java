package com.iac.sdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iac.sdk.domain.Event;
import com.iac.sdk.producer.EventProducer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IacSdkApplication
{
    public static void main( String[] args )
    {
        BlockingQueue<Event> queue = new LinkedBlockingQueue<>();
        List<Event> eventData = loadEventData();

        new Thread(new EventProducer(queue, eventData)).start();
    }

    /**
     *
     * @return list of events
     */
    private static List<Event> loadEventData() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File jsonFile = new File(classLoader.getResource("eventData.json").getFile());

        ObjectMapper objectMapper = new ObjectMapper();
        List<Event> eventData = new ArrayList<>();

        try {
            eventData = objectMapper.readValue(jsonFile, new TypeReference<List<Event>>(){});
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return eventData;
    }
}
