//import java.util.Properties

import akka.actor.Actor
import akka.actor.ActorSystem

import akka.actor.Props

import scala.concurrent._

import ExecutionContext.Implicits.global

import scala.concurrent.duration._
//import CurrentWeather
//import org.apache.kafka.clients.producer._
//import system.dispatcher

object Main extends App  {
  val system = ActorSystem ("ProducerSchedulingActorForKafka")


  //Actor handler created  now we need to send messages.
  val schedulingActorRef = system.actorOf (Props[SchedulingActor],name ="KafkaProdcuerStramingActor")

  //schedulingActorRef ! KafkaProducerScheduler
  //scheduling concurrent threads
  system.scheduler.schedule (0 milliseconds,1000 milliseconds , schedulingActorRef , KafkaProducerScheduler())

 // val weather = new CurrentWeather()

		}
