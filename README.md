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

### Dependency: For POM.xml Project (Maven)
```xml
 <dependency>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka-clients</artifactId>
    <version>2.0.0</version>
</dependency>
```

### Dependency: For build.sbt Project (SBT)
```sbt
libraryDependencies += "org.apache.kafka" % "kafka-clients" % "2.0.0"
```

### Logger Recommendation
https://index.scala-lang.org/raistlintao/scalalogger/logger/1.0.0?binaryVersion=_3

### Spark Model Helper
https://index.scala-lang.org/raistlintao/sparkmodelhelper/sparkmodelhelper/1.2.0?binaryVersion=_2.12