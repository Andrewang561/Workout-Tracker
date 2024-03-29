package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of workout tracker events
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: Constructs an empty EventLog
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: gets a instance of eventLog and returns it
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // EFFECTS: adds given event to the list
    public void logEvent(Event e) {
        events.add(e);
    }

    // EFFECTS: clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared!"));
    }

    // EFFECTS: returns the iterator for the EventLog
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
