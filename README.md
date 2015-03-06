# java-iod-client

Java Client for communicating with [IDOL OnDemand](http://www.idolondemand.com)

## Why?
java-iod-client provides a Java Interface to the IDOL OnDemand API to simplify calling the IDOL OnDemand APIs from Java.

## Goals
Currently only a small subset of IDOL OnDemand APIs are supported. The eventual aim to support all the IDOL OnDemand
APIs. We also plan to provide support for asynchronous APIs, and automated error handling of IDOL OnDemand error codes.

If an API you need is not supported, submit a pull request!

## Usage

java-iod-client is available from the central Maven repository.

    <dependency>
        <groupId>com.hp.autonomy.iod</groupId>
        <artifactId>java-iod-client</artifactId>
        <version>0.1.0-SNAPSHOT</version>
    </dependency>

java-iod-client uses [Retrofit](http://square.github.io/retrofit/) as the basis of its HTTP implementation. This
requires the use of a RestAdapter to use the services. We have used [Jackson](https://github.com/FasterXML/jackson) for
JSON transformation, so you will need to use the Jackson Converter.

    final RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint("https://api.idolondemand.com/1/api")
        .setConverter(new JacksonConverter())
        .build();

    final QueryTextIndexService queryTextIndexService = restAdapter.create(QueryTextIndexService.class);

You can then call the methods on queryTextIndexService to communicate with IDOL OnDemand.

## Is it any good?
Yes.

## License
Copyright 2015 Hewlett-Packard Development Company, L.P.

Licensed under the MIT License (the "License"); you may not use this project except in compliance with the License.