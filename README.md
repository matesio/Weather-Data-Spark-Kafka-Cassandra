# Ingestion and analytics platform for real-time streaming data:

* A module from Final Year project for BS Computer Science. 

# Introduction:

Aim of the project was

* To deploy clusters of different big data tools.
* Handle tons of thousands of data in real time.
* Give Optimized solution for handling data in real time.

# About this module:

* Data from openweathermap api.
* Apache Kafka as a messaging queue.
* Apache Spark as stream processing engine.
* Apache Cassandra as a persistant storage.

# Pre-Requisites:

* Start Apache kafka broker.
* Apache spark (Standalone or any).
* Apache Cassandra (please make sure to enable cassandra Thrift in cofiguration for allowing connection).


# Data 

* From openweathermap.

# Data Pipeline and Architecture Diagram
![Architecture Diagram](/images/ArchitectureDiagram.png)

# Ingestion Layer

* Apache kafka is being used for ingestion layer, because of it high throughput and scalability.
* Akka Scheduling is being used to schedule job to call weather data.
* JSON string for weather data is being enqueued in kafka.

# Speed Layer

* Spark streaming is being used for stream processing. As spark streaming process data in micro batches it was suitable for this          scenario as data from OpenWeatherMap API refreshes after every 2 seconds.

# Database 
* Apache cassandra is being used as a database.
 
 
# Steps For execution.
 
# * Kafka Job
 * git clone https://github.com/matesio/Weather-Data-Spark-Kafka-Cassandra.git.
 * install latest version of sbt and scala.
 * cd Weather-Data-Spark-Kafka-Cassandra/kafkaProject/  && sbt
 * compile
 * run
# * Spark Streaming 
 * cd Weather-Data-Spark-Kafka-Cassandra/sparkStream && sbt
 * compile
 * run
 


