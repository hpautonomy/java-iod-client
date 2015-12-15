package com.hp.autonomy.iod.client.api.textindexing;

import com.hp.autonomy.iod.client.AbstractIodClientIntegrationTest;
import com.hp.autonomy.iod.client.Endpoint;
import com.hp.autonomy.iod.client.error.IodErrorException;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

import static com.hp.autonomy.iod.client.api.textindexing.ListResourcesServiceITCase.ResourceWithNameMatcher.hasResourceWithName;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class ListResourcesServiceITCase extends AbstractIodClientIntegrationTest {

    private ListResourcesService listResourcesService;

    @Override
    @Before
    public void setUp() {
        super.setUp();

        listResourcesService = getRestAdapter().create(ListResourcesService.class);
    }

    public ListResourcesServiceITCase(final Endpoint endpoint) {
        super(endpoint);
    }

    @Test
    public void testResourcesIndexes() throws IodErrorException {
        final Map<String, Object> params = new ListResourcesRequestBuilder()
                .setResourceTypes(EnumSet.of(ResourceType.content))
                .build();

        final Resources resources = listResourcesService.listResources(endpoint.getApiKey(), params);

        assertThat(resources.getPublicResources(), is(not(empty())));

        for (final Resource publicResource : resources.getPublicResources()) {
            assertThat(publicResource.getType(), is(ResourceType.content));
            assertThat(publicResource.getResource(), is(IsNot.not(nullValue())));
        }

        assertThat(resources.getResources(), hasResourceWithName(getIndex()));
    }

    static class ResourceWithNameMatcher extends BaseMatcher<List<Resource>> {

        private final String name;

        private ResourceWithNameMatcher(final String name) {
            this.name = name;
        }

        static ResourceWithNameMatcher hasResourceWithName(final String name) {
            return new ResourceWithNameMatcher(name);
        }

        @Override
        public boolean matches(final Object item) {
            if (!(item instanceof List)) {
                return false;
            }

            @SuppressWarnings("unchecked")
            final List<Resource> resources = (List<Resource>) item;

            boolean found = false;

            for (final Resource resource : resources) {
                if (resource.getResource().equals(name)) {
                    found = true;
                    break;
                }
            }

            return found;
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("A list containing a resource with name: ").appendValue(name);
        }

    }
}
