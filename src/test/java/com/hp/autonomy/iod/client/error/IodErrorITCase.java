/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.iod.client.error;

import com.hp.autonomy.iod.client.AbstractIodClientIntegrationTest;
import com.hp.autonomy.iod.client.Endpoint;
import com.hp.autonomy.iod.client.api.search.QueryTextIndexService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


@RunWith(Parameterized.class)
public class IodErrorITCase extends AbstractIodClientIntegrationTest {

    private QueryTextIndexService queryTextIndexService;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        queryTextIndexService = getRestAdapter().create(QueryTextIndexService.class);
    }

    public IodErrorITCase(final Endpoint endpoint) {
        super(endpoint);
    }

    @Test
    public void testNoQueryTextError() {
        try {
            queryTextIndexService.queryTextIndexWithText(endpoint.getApiKey(), "", null);
            fail("IodClientErrorException not thrown");
        } catch (final IodErrorException e) {
            assertThat(e.getErrorCode(), is(IodErrorCode.MISSING_REQUIRED_PARAMETERS));
            assertThat(e.getMessage(), is("Missing required parameter(s)"));
        }
    }

    @Test
    public void testIodReturnsJobError() {
        try {
            queryTextIndexService.queryTextIndexWithText(endpoint.getApiKey(), "OR", null);
            fail("IodErrorException not thrown");
        } catch (final IodErrorException e) {
            assertThat(e.getErrorCode(), is(IodErrorCode.NO_IGNORE_SPECIALS));
            assertThat(e.getMessage(), is("Invalid query text"));
        }
    }

    @Test
    public void testIodReturnsApiKeyError() {
        try {
            queryTextIndexService.queryTextIndexWithText("*", null);
            fail("IodErrorException not thrown");
        } catch (final IodErrorException e) {
            assertThat(e.getErrorCode(), is(IodErrorCode.AUTHENTICATION_FAILED));
            assertThat(e.getMessage(), is("Authentication failed"));
        }
    }

}
