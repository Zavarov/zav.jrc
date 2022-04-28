[![Java CI with Maven](https://github.com/Zavarov/JRA/actions/workflows/maven.yml/badge.svg)](https://github.com/Zavarov/JRA/actions/workflows/maven.yml)

# JRC (Java Reddit Client)

An implementation of the Reddit API in Java.

### Getting started

This project heavily utilizes dependency injection via [Guice](https://github.com/google/guice) in order to instantiate
the client and auxiliary classes.

When creating a new instance of the `Client`, Guice will try to load the configuration files
`UserAgent.json` and `Crendentials.json` from the root directory.

The form of access can be configured by using either the `ScriptModule` or the `UserlessModule`. For more 
information on a script and userless app, see [here](https://github.com/reddit-archive/reddit/wiki/OAuth2).

Access to the API is granted via the appropriate endpoint implementations (e.g. Subreddit.getHot()). Instances of
those classes are created via Guice. Furthermore, the user can register listeners to subreddits, to be notified by
changes, new submissions for example.

### Installing

In order to install this project, simply execute the maven command:

```
mvn clean install
```

## Built With

* [JSONSchema2Pojo](https://github.com/joelittlejohn/jsonschema2pojo) - For generating Java classes out of JSON schemas.
* [Maven](https://maven.apache.org/) - Dependency management and build tool.

## Authors

* **Zavarov**

## License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details.

