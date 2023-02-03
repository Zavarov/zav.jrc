[![Java CI with Maven](https://github.com/Zavarov/JRA/actions/workflows/maven.yml/badge.svg)](https://github.com/Zavarov/JRA/actions/workflows/maven.yml)

# JRC (Java Reddit Client)

An implementation of the Reddit API in Java.

### Getting started

The client supports both `Script` and `Userless` authentication, via the ScriptClient and
UserlessClient, respectively. In order to authenticate your application, you need to register an app
and use the app id and secret as your credentials. In case of a 'Script', you also need to specify
your username and password. For more information on the different types of authentication, see
[here](https://github.com/reddit-archive/reddit/wiki/OAuth2).

Furthermore, you need specify a unique and descriptive user agent, in order to avoid being
rate-limited. To get a more detailed explanation of the format, see
[here](https://github.com/reddit-archive/reddit/wiki/API).

Create a new client instance using the credentials and user agent described above.

```
Client client = new ScriptClient(userAgent, credentials);
client.login(Duration.TEMPORARY);
```

Access to the API is granted via the appropriate endpoints. Each endpoint is instatiated with the
client. Entities can be requested via the corresponding methods.

```
FrontPage frontPage = new FrontPage(client);
frontPage.getRandom();
```

Some endpoints require additional parameters, such as limit or a time interval. These parameters 
are contributed via a simple map. For all endpoints and their supported parameters, see
[here](https://www.reddit.com/dev/api).

```
FrontPage frontPage = new FrontPage(client);
frontPage.getNew(Map.of("limit", 5));
```

You can use the paginator framework to periodically fetch the latest entries from Reddit. In
combination with the [Observer](https://en.wikipedia.org/wiki/Observer_pattern) pattern you can also
register listeners, which will be notified whenever an update occurs.
When the paginator is called for the first time, its head is initialized with the most recent
element. The next call then returns all elements which have been submitted _after_ the head.
The user is responsible to periodically call the update method.


```
SubredditObserver observer = new SubredditObserver(client, "RedditDev");
observer.addListener(event -> System.out.println(event.getSource()));
observer.notifyAllListeners();
```

### Installation

In order to install this project, simply execute the maven command:

```
mvn clean verify
```

In order to run the integration test, you need to specify the credentials of a `Script` client as
system environment variables. Note, however, that the test expect a fixed test account. In order to
properly run the integration tests, it is therefore recommended to do so via a pull request.

## Built With

* [JSONSchema2Pojo](https://github.com/joelittlejohn/jsonschema2pojo) - For generating Java classes out of JSON schemas.
* [Maven](https://maven.apache.org/) - Dependency management and build tool.

## Authors

* **Zavarov**

## License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details.

