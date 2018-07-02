class Temperature(val kelvin : Double) {
  def toCelsius : Double = {
    val x = kelvin - 273.15;
    return "%.2f".format(x).toDouble
  }

  def toFahrenheit : Double = {
    val x = ( ( kelvin - 273.15 ) * 1.8 ) + 32
    return "%.2f".format(x).toDouble
  }
}
//https://forums.couchbase.com/t/spark-streaming-from-kafka-with-couchbase-kafka-connector/11271 
//link Currently using
//best json link
//https://riak.docs.hw.ag/riak/ts/1.3.0/add-ons/spark-riak-connector/usage/streaming-example/
//git lab best code 
//https://csil-git1.cs.surrey.sfu.ca/BigData/cmpt-733-proj/blob/608274393af89aad53103d430e64cc4f7a69fb09/src/main/scala/KafkaConsumerApp.scala
//iot uber link
//https://blog.codecentric.de/en/2016/07/iot-analytics-platform/
//yahoo weather 
//https://csil-git1.cs.surrey.sfu.ca/BigData/cmpt-733-proj/blob/608274393af89aad53103d430e64cc4f7a69fb09/src/main/scala/YahooWeather.scala
//weather data stream 
//https://github.com/tmcgrath/spark-scala/blob/master/kafka-streaming/src/main/scala/com/supergloo/WeatherDataStream.scala
//best kafka consumer codes
//www.cJOBodegist.net/code/spark-kafka-consumer-scala-example/
//real time sensor values 
//http://www.codegist.net/snippet/scala/sparkkafkaconsumerscala_carybourgeois_scala
//supergloo data
//https://github.com/tmcgrath/spark-scala/blob/master/kafka-streaming/src/main/scala/com/supergloo/WeatherDataStream.scala
//spark-scala dataset tutorials
//https://www.balabit.com/blog/spark-scala-dataset-tutorial/
//keyed-kafka-producer-consumer
//http://colobu.com/2015/03/13/kafka-example-in-scala/
//best link containing kafka codes
//https://www.programcreek.com/scala/org.apache.kafka.clients.producer.ProducerRecord
