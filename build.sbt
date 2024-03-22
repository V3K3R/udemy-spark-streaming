name := "udemy-spark-streaming"

scalaVersion := "2.13.13"

val sparkVersion = "3.5.1"
val postgresVersion = "42.2.2"
val cassandraConnectorVersion = "3.5.0"
val akkaVersion = "2.6.17"
val akkaHttpVersion = "10.2.7"
val twitter4jVersion = "4.0.7"
val kafkaVersion = "3.7.0"
val log4jVersion = "2.17.1"
val nlpLibVersion = "3.5.1"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,

  // // streaming
  "org.apache.spark" %% "spark-streaming" % sparkVersion,

  // streaming-kafka
  "org.apache.spark" % "spark-sql-kafka-0-10_2.13" % sparkVersion,

  // low-level integrations
  "org.apache.spark" %% "spark-streaming-kafka-0-10" % sparkVersion,
  "org.apache.spark" %% "spark-streaming-kinesis-asl" % sparkVersion,
  "com.datastax.spark" %% "spark-cassandra-connector" % cassandraConnectorVersion,

  // postgres
  "org.postgresql" % "postgresql" % postgresVersion,

  // logging
  "org.apache.logging.log4j" % "log4j-api" % log4jVersion,
  "org.apache.logging.log4j" % "log4j-core" % log4jVersion,

  // kafka
  "org.apache.kafka" %% "kafka" % kafkaVersion,
  "org.apache.kafka" % "kafka-streams" % kafkaVersion
)

javaOptions ++= Seq(
  "--add-opens=java.base/sun.nio.ch=ALL-UNNAMED"
  // "--add-opens=java.base/sun.nio.cs=ALL-UNNAMED",
)
