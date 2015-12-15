/*
 * Copyright 2015 Hewlett-Packard Development Company, L.P.
 * Licensed under the MIT License (the "License"); you may not use this file except in compliance with the License.
 */

package com.hp.autonomy.iod.client.converter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.converter.JacksonConverter;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;
import retrofit.mime.TypedString;

import java.lang.reflect.Type;

/**
 * {@link retrofit.converter.Converter} implementation that respects the {@link com.hp.autonomy.iod.client.converter.DoNotConvert}
 * annotation.
 */
public class IodConverter implements Converter {

    private final Converter converter;

    /**
     * Creates a new IodConverter
     */
    public IodConverter() {
        this(new ObjectMapper());
    }

    /**
     * Creates a new IodConverter
     *
     * @param objectMapper Jackson ObjectMapper for handling Json
     */
    public IodConverter(final ObjectMapper objectMapper) {
        final ObjectMapper objectMapperCopy = objectMapper.copy();
        // HPE Haven OnDemand does not consider adding new properties to be a breaking change, so ignore any unknown properties
        objectMapperCopy.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        converter = new JacksonConverter(objectMapperCopy);
    }

    /**
     * Returns the object returned by calling fromBody on the underlying converter
     */
    @Override
    public Object fromBody(final TypedInput body, final Type type) throws ConversionException {
        return converter.fromBody(body, type);
    }

    /**
     * If object is annotated with {@link com.hp.autonomy.iod.client.converter.DoNotConvert}, returns a String
     * representation of the object. Otherwise returns the object returned by calling toBody on the underlying converter
     */
    @Override
    public TypedOutput toBody(final Object object) {
        if (object.getClass().isAnnotationPresent(DoNotConvert.class)) {
            return new TypedString(object.toString());
        } else {
            return converter.toBody(object);
        }
    }
}
