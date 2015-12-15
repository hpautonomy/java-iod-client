package com.hp.autonomy.iod.client.api.textindexing;

import com.hp.autonomy.iod.client.util.MultiMap;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Setter
@Accessors(chain = true)
public class ListResourcesRequestBuilder {

    /**
     * @param type value of the type parameter
     */
    @SuppressWarnings("FieldMayBeFinal")
    private Set<ResourceType> resourceTypes = new HashSet<>();
    
    /**
     * @param flavor value of the flavor parameter
     */
    @SuppressWarnings("FieldMayBeFinal")
    private Set<ResourceFlavor> resourceFlavors = new HashSet<>();

    /**
     * @return A map of query parameters suitable for use with
     * {@link com.hp.autonomy.iod.client.api.textindexing.ListResourcesService}. get is NOT supported on the resulting map
     */
    public Map<String, Object> build() {
        final Map<String, Object> map = new MultiMap<>();

        for (final ResourceType resourceType : resourceTypes) {
            map.put("type", resourceType);
        }

        for (final ResourceFlavor resourceFlavor : resourceFlavors) {
            map.put("flavor", resourceFlavor);
        }

        return map;
    }
}
