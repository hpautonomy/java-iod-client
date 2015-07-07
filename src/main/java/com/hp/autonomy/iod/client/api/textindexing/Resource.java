package com.hp.autonomy.iod.client.api.textindexing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Resource {
    /**
     * @return The name of the resource
     */
    private final String resource;

    /**
     * @return The resource description
     */
    private final String description;

    /**
     * @return The resource type
     */
    private final ResourceType type;

    /**
     * @return The resource flavour
     */
    private final ResourceFlavor flavour;

    /**
     * @return The date created
     */
    private final String dateCreated;

    public Resource(
            @JsonProperty("resource") final String resource,
            @JsonProperty("description") final String description,
            @JsonProperty("type") final ResourceType type,
            @JsonProperty("flavor") final ResourceFlavor flavour,
            @JsonProperty("date_created") final String dateCreated
    ) {
        this.resource = resource;
        this.description = description;
        this.type = type;
        this.flavour = flavour;
        this.dateCreated = dateCreated;
    }
}
