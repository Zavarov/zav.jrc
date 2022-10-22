[![Java CI with Maven](https://github.com/Zavarov/JRA/actions/workflows/maven.yml/badge.svg)](https://github.com/Zavarov/JRA/actions/workflows/maven.yml)

# JRC (Java Reddit Client)

An implementation of the Reddit API in Java.

### Getting started

The client requires configuration files containing the user agent and credentials.

The client supports both `Script` and `Userless` authentication, via the ScriptClient and
UserlessClient, respectively. For more information on the different types of
authentication, see [here](https://github.com/reddit-archive/reddit/wiki/OAuth2).

Access to the API is granted via the appropriate endpoint implementations (e.g. Subreddit.getHot()). Instances of
those classes are created via Guice. Furthermore, the user can register listeners to subreddits, to be notified by
changes, new submissions for example.

Check the [Example](zav.jrc.example) project for a quick demonstration.

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

