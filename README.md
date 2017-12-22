# Overview
This project is a sample that helps to showcase how Spring supports [Amazon Simple Queue Service (SQS)](https://aws.amazon.com/sqs/).  Every second a simple payload is placed on the queue which a consumer should immediately process.  The consumer transforms the payload to uppercase and sending it on to a second queue.  That queue's consumer prints the payload, ending the sequence. 

# Guidebook
Details about this project are contained in the [guidebook](guidebook/guidebook.md) and should be considered mandatory reading prior to contributing to this project.

# Prerequisites
* [JDK 8](http://zulu.org/) installed and working
* an SQS queue named `sqs-experiment` -- you can select another name but will have to edit the code
* an SQS queue named `secondary-queue` -- you can select another name but will have to edit the code

# Building
`./gradlew` will pull down any dependencies, compile the source and package everything up.

# Installation
Nothing to install.

# Tips and Tricks
## Starting The Server
Edit `application.yml`, inserting your API keys.

`./gradlew clean bootRun` will start the server on port `8080` and begin producing messages every second. You should see something similar to this:

```
2017-12-22 11:06:36.094  INFO 1154 --- [pool-2-thread-1] com.example.sqs.sqs.SqsApplication       : Producing message 108
2017-12-22 11:06:36.412  INFO 1154 --- [nerContainer-15] com.example.sqs.sqs.SqsApplication       : Consuming 102 from sqs-experiment
2017-12-22 11:06:36.412  INFO 1154 --- [nerContainer-15] com.example.sqs.sqs.SqsApplication       :     LogicalResourceId: sqs-experiment
2017-12-22 11:06:36.602  INFO 1154 --- [nerContainer-10] com.example.sqs.sqs.SqsApplication       : Consuming 106 from secondary-queue
2017-12-22 11:06:36.608  INFO 1154 --- [nerContainer-10] com.example.sqs.sqs.SqsApplication       :     LogicalResourceId: secondary-queue
2017-12-22 11:06:36.832  INFO 1154 --- [nerContainer-15] com.example.sqs.sqs.SqsApplication       : Consuming 108 from sqs-experiment
2017-12-22 11:06:36.832  INFO 1154 --- [nerContainer-15] com.example.sqs.sqs.SqsApplication       :     LogicalResourceId: sqs-experiment
2017-12-22 11:06:37.094  INFO 1154 --- [pool-2-thread-1] com.example.sqs.sqs.SqsApplication       : Producing message 109
2017-12-22 11:06:37.306  INFO 1154 --- [nerContainer-15] com.example.sqs.sqs.SqsApplication       : Consuming 103 from secondary-queue
2017-12-22 11:06:37.306  INFO 1154 --- [nerContainer-14] com.example.sqs.sqs.SqsApplication       : Consuming 107 from secondary-queue
2017-12-22 11:06:37.306  INFO 1154 --- [nerContainer-15] com.example.sqs.sqs.SqsApplication       :     LogicalResourceId: secondary-queue
2017-12-22 11:06:37.306  INFO 1154 --- [nerContainer-14] com.example.sqs.sqs.SqsApplication       :     LogicalResourceId: secondary-queue
2017-12-22 11:06:37.306  INFO 1154 --- [nerContainer-10] com.example.sqs.sqs.SqsApplication       : Consuming 105 from secondary-queue
2017-12-22 11:06:37.306  INFO 1154 --- [nerContainer-10] com.example.sqs.sqs.SqsApplication       :     LogicalResourceId: secondary-queue
2017-12-22 11:06:37.306  INFO 1154 --- [nerContainer-16] com.example.sqs.sqs.SqsApplication       : Consuming 104 from secondary-queue
2017-12-22 11:06:37.306  INFO 1154 --- [nerContainer-16] com.example.sqs.sqs.SqsApplication       :     LogicalResourceId: secondary-queue
2017-12-22 11:06:37.422  INFO 1154 --- [nerContainer-16] com.example.sqs.sqs.SqsApplication       : Consuming 102 from secondary-queue
2017-12-22 11:06:37.422  INFO 1154 --- [nerContainer-16] com.example.sqs.sqs.SqsApplication       :     LogicalResourceId: secondary-queue
```


# Troubleshooting

# Contributing

# License and Credits
* This project is licensed under the [Apache License Version 2.0, January 2004](http://www.apache.org/licenses/).
* The guidebook structure was created by [Simon Brown](http://simonbrown.je/) as part of his work on the [C4 Architectural Model](https://c4model.com/).  His books can be [purchased from LeanPub](https://leanpub.com/b/software-architecture).
* Patrick Kua offered [his thoughts on a travel guide to a software system](https://www.safaribooksonline.com/library/view/oreilly-software-architecture/9781491985274/video315451.html) which has been [captured in this template](travel-guide/travel-guide.md).

# List of Changes
