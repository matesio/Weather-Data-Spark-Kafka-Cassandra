import uk.co.bigbeeconsultants.http.{Config, HttpClient}
import uk.co.bigbeeconsultants.http.response.Response
import java.net._
import org.json4s._
import org.json4s.native.JsonMethods._
import org.json4s.Formats

class CurrentWeather() {
  
  implicit val formats = DefaultFormats
  private var _latitude: Option[Double] = None
  private var _longitude: Option[Double] = None
  private var _wind: Wind = null
  private var _country: Option[String] = None
  private var _sunrise: Option[Integer] = None
  private var _sunset: Option[Integer] = None
  private var _temperature: Temperature = null
  private var _temperatureMin: Temperature = null
  private var _temperatureMax: Temperature = null
  private var _pressure: Option[Double] = None
  private var _seaLevel: Option[Double] = None
  private var _groundLevel: Option[Double] = None
  private var _humidity: Option[Integer] = None
  private var _cloudiness: Option[Integer] = None
  private var _id: Integer = null
  private var _weather : List[Weather] = null

  case class Wind(val speed: Double, val deg: Double)
  case class Weather(val id : Integer, val main : String,
                      val description : String, val icon : String)

   
  
//useful
//https://github.com/bikash?language=java&tab=stars
  def initWeatherData() = {
        val url = new URL(List("http://api.openweathermap.org/data/2.5/weather?q=Islamabad&APPID=a156e614beff0effdd38bd90099653ae").mkString)
      //  val url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Islamabad&APPID=a156e614beff0effdd38bd90099653ae")

        val httpClient = new HttpClient
    //  val httpClient = if(_port != null) {
    //  val proxyAddress = new InetSocketAddress("localhost", _port)
    //  val proxy = new Proxy(Proxy.Type.HTTP, proxyAddress)
    //  val config = Config()
    //  new HttpClient(config)
    //} else {
    //  new HttpClient
    //}

    	val response: Response = httpClient.get(url)
    	val rawJson = response.body.asString
     	print (rawJson)
    	val json = parse(rawJson)
    	extractValuesFromJson(json)
  		}

  def extractValuesFromJson(json : JValue) = {
    	_latitude = (json \\ "coord" \ "lat").extractOpt[Double]
    	_longitude = (json \\ "coord" \ "lon").extractOpt[Double]
    	_country = (json \\ "sys" \ "country").extractOpt[String]
    	_sunrise = (json \\ "sys" \ "sunrise").extractOpt[Integer]
    	_sunset = (json \\ "sys" \ "sunset").extractOpt[Integer]
    	_temperature = new Temperature((json \\ "main" \ "temp").extract[Double])
    	_temperatureMin = new Temperature((json \\ "main" \ "temp_min").extract[Double])
    	_temperatureMax = new Temperature((json \\ "main" \ "temp_max").extract[Double])
    	_pressure = (json \\ "main" \ "pressure").extractOpt[Double]
    	_humidity = (json \\ "main" \ "humidity").extractOpt[Integer]
    	_wind = new Wind((json \\ "wind" \ "speed").extract[Double], (json \\ "wind" \ "deg").extract[Double])
    	_cloudiness = (json \\ "clouds" \ "all").extractOpt[Integer]
    	_id = (json \ "id").extract[Integer]
    	_weather = extractWeatherData((json \ "weather"))
  }

  def extractWeatherData(json : JValue) : List[Weather] = {
    	_weather = List[Weather]()
    	for( wn <- json.extract[List[Map[String,String]]] ){
     		_weather ::= new Weather(wn("id").toInt, wn("main"), wn("description"), wn("icon"))
    		}
    	_weather
 		 }

  def weather = { _weather }

  def longitude = { _longitude.getOrElse(0.0) }

  def latitude = { _latitude.getOrElse(0.0) }

  def country = { _country.get }

  def sunrise = { new java.util.Date(_sunrise.get * 1000L) }

  def sunset = { new java.util.Date(_sunset.get * 1000L) }

  def temperature = { _temperature }

  def temperatureMin = { _temperatureMin }

  def temperatureMax = { _temperatureMax }

  def pressure = { _pressure.get }


  def humidity = { _humidity.getOrElse(0) }

  def wind = { _wind }

  def cloudiness = { _cloudiness.get }

  def id = { _id }

}

/* Working Rdd
val processedStream = inputStream.map(record => record.value.split(" "))

   processedStream.foreachRDD(rdd => rdd .map (r => Weather(r(0).toInt,r(1),r(2).toInt,
                          r(3),r(4).toDouble,r(5).toDouble,r(6).toDouble,r(7).toDouble,
                          r(8),r(9),r(10).toDouble,
                          r(11).toDouble,r(12).toDouble,
                          r(13).toDouble,r(14).toDouble)).toDF().write.format("org.apache.spark.sql.cassandra")
                          .options(Map( "table" -> "raw_weather", "keyspace" -> "rosail_weather")).mode("append").save())*/