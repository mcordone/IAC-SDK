package com.iac.sdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iac.sdk.client.RestClient;
import com.iac.sdk.consumer.EventConsumer;
import com.iac.sdk.domain.Event;
import com.iac.sdk.producer.EventProducer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class IacSdkApplication
{
    private static Properties appProps;

    public static void main( String[] args )
    {
        //load application properties
        loadApplicationProperty();

        BlockingQueue<Event> queue = new LinkedBlockingQueue<>();
        List<Event> eventData = loadEventData();

        new Thread(new EventProducer(queue, eventData)).start();
        new Thread(new EventConsumer(queue, new RestClient())).start();
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

    /**
     * loads application properties
     */
    private static void loadApplicationProperty() {

        try {
            appProps = new Properties();
            InputStream in = ClassLoader.getSystemResourceAsStream("application.properties");
            appProps.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
