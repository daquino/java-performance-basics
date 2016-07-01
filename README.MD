# Java Performance Basics
Set of simple Java applications that are used to illustrate performance issues
and used in conjunction with the [Java Performance Basics](
https://docs.google.com/presentation/d/1Nv7ez3hl1OukEwcVdVgc0kpMBqjbbgsQuLIpQgrFoKk/edit?usp=sharing
) presentation.

The Java applications in this repository should be used with a tool like
VisualVM to learn more about the performance aspects of the JVM.

## Projects
1. file-uploadr - Spring boot application for uploading files.

### file-uploadr
A simple spring boot application for file uploads.  There are two controllers
used to upload files: a bad one and a good one.  Although this sort of performance
issue is simple to spot, it still can happen in a production environment.  Check
out [this Code School blog entry](https://www.codeschool.com/blog/2015/06/04/how-a-bug-in-my-ruby-code-cost-code-school-13000/)
where a bug with file downloading cost them $13,000.

You can build the application using maven via `./mvnw clean package`.  
Run the application like a normal .jar file: `java -jar file-uploadr-${version}.jar`.  
One thing to try is running the application with a lower max heap size and see the
effect of using the bad upload vs good upload strategy.
```
java -Xmx128M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.hprof -jar file-uploadr-${version}.jar
```

The heap dump flags will configure the JVM to save a heap dump on an out of memory error (hint: if you upload a large file, the bad upload causes an out of memory error).
