package com.iac.sdk.producer;

import com.iac.sdk.domain.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.BlockingQueue;

public class EventProducer implements Runnable {

    private static Logger LOGGER = LoggerFactory.getLogger(EventProducer.class);
    private static long SLEEP_TIME_INTERVAL = 1000;

    private BlockingQueue<Event> queue;
    private List<Event> eventData;

    public EventProducer(BlockingQueue<Event> queue, List<Event> eventData) {
        this.queue = queue;
        this.eventData = eventData;
    }

    public void run() {
        try{
            while (true) {
                generateEvents();
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Utility function to generate Events objects for this test application. Generate eventList size per seconds
     * In real application the mobile device generates these events
     * @throws InterruptedException
     */
    private void generateEvents() throws InterruptedException {
        for (int i = 0; i < eventData.size(); i++) {
            queue.put(eventData.get(i));
            //LOGGER.info("Event Name {}", eventData.get(i).getName());
        }

        Thread.sleep(SLEEP_TIME_INTERVAL);
    }
}
