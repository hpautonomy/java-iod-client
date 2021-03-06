package com.hp.autonomy.iod.client.api.textindexing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Resources {
    /**
     * @return A list of private resources
     */
    private final List<Resource> resources;

    /**
     * @return A list of public resources
     */
    private final List<Resource> publicResources;

    public Resources(
            @JsonProperty("private_resources") final List<Resource> resources,
            @JsonProperty("public_resources") final List<Resource> publicResources
    ) {
        this.resources = resources;
        this.publicResources = publicResources;
    }
}