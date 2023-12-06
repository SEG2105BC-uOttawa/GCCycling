package com.example.gcccyclingapp;

import android.content.Context;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class editOptionsTest {

    @Mock
    Context mMockContext;
    public editOptionsTest() {

    }

    @Test
    public void readStringFromContext_LocalizedString() {

        ParticipantBrowseEvents eventBrowser = new ParticipantBrowseEvents();

        assertThat(eventBrowser.showEditDeleteOptions("test", "test"), is(true));
    }


}
