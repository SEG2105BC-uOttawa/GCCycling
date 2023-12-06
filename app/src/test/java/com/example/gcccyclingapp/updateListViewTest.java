package com.example.gcccyclingapp;

import android.os.Looper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class updateListViewTest { // works when running app, for some reason unit test wont work
    @Mock
    ListView mockListView;

    @Mock
    TextView mockNoEventsMessage;

    @Mock
    ParticipantBrowseEvents mockActivity;

    public updateListViewTest() {
        mockActivity = Mockito.spy(new ParticipantBrowseEvents());
        mockActivity.listView = mockListView;
        mockActivity.noEventsMessage = mockNoEventsMessage;
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockActivity = Mockito.spy(new ParticipantBrowseEvents());
        mockActivity.listView = mockListView;
        mockActivity.noEventsMessage = mockNoEventsMessage;
    }
    @Test
    public void testUpdateListView() {
        // Mock data
        List<Map<String, String>> newListItems = new ArrayList<>();
        Map<String, String> item = new HashMap<>();
        item.put("event", "TestEvent");
        item.put("club", "TestClub");
        newListItems.add(item);

        String filter = "Club";

        // Mock behavior
        when(mockActivity.updateListView(newListItems, filter)).thenCallRealMethod();

        // Call the method under test
        boolean result = mockActivity.updateListView(newListItems, filter);

        // Verify the behavior
        assertTrue(result);
        verify(mockListView).setVisibility(View.VISIBLE);
        verify(mockNoEventsMessage).setVisibility(View.GONE);

        // Additional verifications or assertions based on your specific logic

    }
}
