import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}

import java.util.Properties

object App {
  def main(args: Array[String]): Unit = {

    // 1. Load the configuration
    //  Normally, Scala Spark will use Spark-Submit to perform Scala App.
    //  So the params index (of args) is 17 here
    //  But for demo we use static params
    /*
    val params = args(17).trim.split(" ")
    val dataFileURL = params(0).toString
    val jobID = params(1).toString
    val kafkaBrokerURL = params(2).toString
    val kafkaGroupId = params(3).toString
    val kafkaTopic = params(4).toString
     */
    val dataFileURL = "123"
    val jobID = "123"
    val kafkaBrokerURL = "Localhost:9092"
    val kafkaGroupId = "Datashop"
    val kafkaTopic = "JobStatus"
    println("External Params Loaded!")

    // 2. Initialise KAFKA
    val props: Properties = new Properties()
    props.put("bootstrap.servers", kafkaBrokerURL)
    props.put("key.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put("group.id", kafkaGroupId)
    val producer = new KafkaProducer[String, String](props)

    println("Start Task")
    // 3. Start update to Kafka
    val metadata = producer.send(new ProducerRecord[String, String](kafkaTopic, "JOB #" + jobID + " Started"))
    println("Sent meta(partition=" +
      metadata.get().partition() +
      ", offset= " +
      metadata.get().offset() + ")")
    // 4. Do you task
    Thread.sleep(5000)

    // 5. Update Jobstatus again when finished.
    producer.send(new ProducerRecord[String, String](kafkaTopic, "JOB #" + jobID + " Finished"))

    // 6. Clean Up
    producer.flush()
    producer.close()
    println("Task Finished!")
  }
}