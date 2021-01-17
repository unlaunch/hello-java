# hello-java

> Hello Unlaunch, from Java!

Unlaunch is a free feature flag service. Please visit [https://www.unlaunch.io](https://www.unlaunch.io) to sign up
 for a new account today!
 
This is a **demo** project to describe how to use [Unlaunch Java SDK](https://github.com/unlaunch/java-sdk) in your application. It is a maven project.
For more details, read our [Getting Started](https://docs.unlaunch.io/docs/getting-started) tutorial.

# Build Procedure
1. Download code. Edit Hello.java class set your Unlaunch SDK_KEY and FEATURE_FLAG_KEY you want to evaluate as: 

```
private static final String SDK_KEY = "your-sdk-key";
private static finalString FEATURE_FLAG_KEY = "your-flag-key";
```

By default, we have set these values to an example feature flag. So you can run the code as is.

Then on on the command line, type:

```
mvn clean compile assembly:single
java -jar target/hello-java-1.0-SNAPSHOT.jar 
```

When you run the project, it will print something like:

```
[DEMO] Feature returned variation: on. Evaluation reason: Default Rule served.
```
