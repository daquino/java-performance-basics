# Java Performance Basics
Set of simple Java applications that are used to illustrate performance issues
and used in conjunction with the [Java Performance Basics](
https://docs.google.com/presentation/d/1Nv7ez3hl1OukEwcVdVgc0kpMBqjbbgsQuLIpQgrFoKk/edit?usp=sharing
) presentation.

The Java applications in this repository should be used with a tool like
VisualVM to learn more about the performance aspects of the JVM.

## Projects

All projects can be built using Maven.  Each project comes with a Maven wrapper script that can be used to build the project.

```
./mvnw clean package
```

A build of each project should contain an runnable JAR file.  You can run the project like any other runnable JAR.

```
java -jar ${project-version}.jar
```

It is encouraged pass in additional options, such as max heap size, (i.e. -XmxN) when running the project to observe issues around memory performance in each project.  A tool such as VisualVM can provide visualizations into the performance of each application.

### file-uploadr
A simple spring boot application for file uploads.  There are two controllers
used to upload files: a bad one and a good one.  Although this sort of performance
issue is simple to spot, it still can happen in a production environment.  Check
out [this Code School blog entry](https://www.codeschool.com/blog/2015/06/04/how-a-bug-in-my-ruby-code-cost-code-school-13000/)
where a bug with file downloading cost them $13,000.

One thing to try is running the application with a lower max heap size and see the
effect of using the bad upload vs good upload strategy.
```
java -Xmx128M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=heap.hprof -jar file-uploadr-${version}.jar
```

The heap dump flags will configure the JVM to save a heap dump on an out of memory error (hint: if you upload a large file, the bad upload causes an out of memory error).

### memory-leak
A sample JavaFX project that contains two buttons to mimic a task processor.  The
actual task implementation just prints out to the console, but they also contain
an object array used to simulate a memory issue.  The *memory leaking* task processor
accesses the queue of tasks incorrectly which is backed by an ArrayDeque.

When running the application, it is best to limit the max heap size so that the
memory issue is apparent.

For example:
```
java -Xmx64M -jar memory-leak-${version}.jar
```

You should notice a large difference in memory usage between the two task processing strategies using a tool such as VisualVM.
