# scala-task-boilerplate

Scala Task boilerplate for datashop with Kafka ability

This is the boilerplate of Scala task with Kafka communication ability for Datashop Project

### Params Configuration

```scala
//  Normally, Scala Spark will use Spark-Submit to perform Scala App.
//  So the params index (of args) is 17 here
//  But for demo we use static params

val params = args(17).trim.split(" ")
val dataFileURL = params(0).toString
val jobID = params(1).toString
val kafkaBrokerURL = params(2).toString
val kafkaGroupId = params(3).toString
val kafkaTopic = params(4).toString

```

### Initial Kafka Producer
```scala
 val props: Properties = new Properties()
    props.put("bootstrap.servers", kafkaBrokerURL)
    props.put("key.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer",
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put("group.id", kafkaGroupId)
val producer = new KafkaProducer[String, String](props)
```

### Sending Message
```scala
val metadata = producer.send(new ProducerRecord[String, String](kafkaTopic, "JOB #" + jobID + " Started"))
```