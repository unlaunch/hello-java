# hello-java

The Unlaunch Hello-Java is a demo to describe how to use Unlaunch Java Sdk in your application. It is a maven project.
For more details you can visit Unlaunch documentation https://docs.unlaunch.io/docs

# Build Procedure

1. Download code. Edit Hello.java class set your unluanch SDK_KEY and FEATURE_FLAG_KEY that you want to evaluate as 
	
	  static final String SDK_KEY = "1234567890abcdef";

  	static final String FEATURE_FLAG_KEY = "my-flag";

On command line run as:
	
	  mvn clean compile assembly:single

	  java -jar target/hello-java-1.0-SNAPSHOT.jar 

The Hello example will print "Variation served is {variation}"
