import uk.co.bigbeeconsultants.http.{Config, HttpClient}
import uk.co.bigbeeconsultants.http.response.Response
import java.net._
import org.json4s._
import org.json4s.native.JsonMethods._
import java.util.Properties
import akka.actor.Actor
import akka.actor.Props
import akka.event.Logging
import org.apache.kafka.clients.producer._
//import org.json.{JSONException, JSONObject}
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.KafkaProducer
case class KafkaProducerScheduler() 

//case class WeatherDataScheduler()

//case class KafkaConsumerScheduler()
case class KeyedMessage[K, V](val topic: String, val key: K, val partKey: Any, val message: V) {
  if(topic == null)
    throw new IllegalArgumentException("Topic cannot be null.")
  
  def this(topic: String, message: V) = this(topic, null.asInstanceOf[K], null, message)
  
  def this(topic: String, key: K, message: V) = this(topic, key, key, message)
  
  def partitionKey = {
    if(partKey != null)
      partKey
    else if(hasKey)
      key
    else
      null  
  }
  
  def hasKey = key != null
}

class SchedulingActor extends Actor {
  
  val weather = new CurrentWeather()
  
  val log = Logging(context.system, this)



  def receive = {
    case KafkaProducerScheduler() => println("Generating message from producer")
                                    
      
      
                                  weather.initWeatherData()
                                 
                                  var longitude		= weather.longitude
                                  var humidity 		= weather.humidity // => -82.7998
                                  var latitude  	= weather.latitude
                                  var country  		= weather.country
                                  var sunrise  		= weather.sunrise
                                  var sunset  		= weather.sunset
                                  var temperature= weather.temperature.toFahrenheit
                                  var temperatureMin= weather.temperatureMin.toFahrenheit
                                  var temperatureMax= weather.temperatureMax.toFahrenheit
                                  var pressure      = weather.pressure
                                  var wind_speed    = weather.wind.speed
                                  var wind_deg      = weather.wind.deg
                                  var cloudiness    = weather.cloudiness
                                  var id            = weather.id
                                  var time_stamp     = new java.util.Date
                                  //val  message = s"""{ "time_stamp" : $time_stamp,"lon" : $longitude,"lat" : $latitude,"hum" : $humidity,"countryy" : $country,"sunrisee" : $sunrise, "sunsett" : $sunset, "temperaturee" : $temperature, "temperatureMinn" : $temperatureMin, "temperatureMaxx" : $temperatureMax,  "pressuree" : $pressure,  "wind_speedd" : $wind_speed, "wind_degg" : $wind_deg, "cloudinesss" : $cloudiness,"idd" : $id}"""
                                                  
                                    //val data= Data (time_stamp, longitude,latitude,humidity,country,sunrise,sunset,
                                        //                temperature,temperatureMin,temperatureMax,pressure,wind_speed,
                                      //                  wind_deg,cloudiness,id)

                                  var message:String =id+" "+"01"+" " + cloudiness + " " + country + " " + humidity + " " +
                                  latitude + " " + longitude + " " + pressure + " " +  "55" + " " + "548" + " " +
                                   temperature + " " + temperatureMax + " " + temperatureMin + " " + wind_deg + " " + wind_speed  
  
                                   // KafkaBrokerSend(longitude,latitude,country,sunrise,sunset,temperature,temperatureMin,
                                   //         temperatureMax,pressure,humidity,cloudiness,id,wind_speed,wind_deg )
                                  val props = new Properties()
   
                               //   props.put ("bootstrap.servers","172.20.53.192:9092,172.20.53.194:9092,172.20.53.250:9092")

                                  props.put ("bootstrap.servers","127.0.0.1:9092")
   // props.put("bootstrap.servers", "172.20.53.222:9092,172.20.53.223:9092,172.20.53.250:9092")
                        //          props.put ("bootstrap.servers","127.0.0.1:9092")
                                  props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
                                  
                                  props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

                                  props.put("producer.type", "async")
                                  
                                 // val producer = new KafkaProducer[String, String](props)

                                  val producer = new KafkaProducer[String,String](props)
                                //  val record = new ProducerRecord(TOPIC, "kedsadasdhy9huopdjasohdoasduasgdouasgdo[asgdy", message)
                                 // val producer = new KafkaProducer[String, String](props)
                               //   val config = new ProducerConfig(props)
                                //  val producer = new ProducerRecord (config)
                                  val TOPIC="test1"
                                  println (message)
                                 
                               ///   val data = new ProducerRecord[String, String](TOPIC,message)
                                //  val dataRecord =  new KeyedMessage[String, String](TOPIC,data) 
                                 // data.foreach (m => producer.send(new KeyedMessage[String, String](TOPIC, m)))
                                // producer.send(data)
 //

    for(i<- 1 to 999){
        val record = new ProducerRecord(TOPIC,"weather-data",message)
        log.info("sending message..............")
        producer.send(record)
        println ("----------------------------------------------")
        log.info(""+record)
        println ("----------------------------------------------")
 }
    
   // val record = new ProducerRecord(TOPIC, "key", "the end "+new java.util.Date)
    //producer.send(record)

    producer.close()
    //  
                 
   // case WeatherDataScheduler() => log.info ("Weather data updating")
   // case KafkaConsumerScheduler() => log.info ("Consumer scheduler message")
    
    case _ => log.info("received unknown message")
              println ("unknown message for scheduler")
    }
    
 
    
    }

