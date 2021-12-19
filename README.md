[![Java CI with Maven](https://github.com/Zavarov/JRA/actions/workflows/maven.yml/badge.svg)](https://github.com/Zavarov/JRA/actions/workflows/maven.yml)

# JRC (Java Reddit Client)

An implementation of the Reddit API in Java.

### Getting started

This project heavily utilizes dependency injection via [Guice](https://github.com/google/guice) in order to instantiate
the client and auxiliary classes.

When creating a new instance of the `Client`, Guice will try to load the configuration files
`UserAgent.json` and `Crendentials.json` from the root directory.

Furthermore, the type of client can be specified by either using the `ScriptModule` or the `UserlessModule`. For more 
information on a script and userless app, see [here](https://github.com/reddit-archive/reddit/wiki/OAuth2).

Access to the API is made available using *views*, an abstract overview over the different Reddit entities. Each view
then contains methods reflecting individual API calls. See the [example](example) project for reference.

### Installing

In order to install this project, simply execute the maven command:

```
mvn clean install
```

## Runtime Dependencies

* [Guice 5.0.1](https://github.com/google/guice/tree/5.0.1),
  Apache License 2.0
* [Jackson 2.13.0](https://github.com/FasterXML/jackson-databind/tree/jackson-databind-2.13.0),
  Apache License 2.0
* [Jakarta Inject 1.0.5](https://github.com/eclipse-ee4j/injection-api/tree/1.0.5),
  Apache License 2.0
* [Log4j2 2.17.0](https://github.com/apache/logging-log4j2/tree/rel/2.17.0),
  Apache License 2.0
* [OkHttp 4.9.3](https://github.com/square/okhttp/tree/parent-4.9.3),
  Apache License 2.0

## Built With

* [JSONSchema2Pojo](https://github.com/joelittlejohn/jsonschema2pojo) - For generating Java classes out of JSON schemas.
* [Maven](https://maven.apache.org/) - Dependency management and build tool.

## Authors

* **Zavarov**

## License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details.

