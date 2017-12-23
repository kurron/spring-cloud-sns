# Overview
This project is a sample that helps to showcase how Spring supports [Amazon Simple Queue Service (SQS)](https://aws.amazon.com/sqs/).  Every 5 seconds a simple payload is published to an SNS topic which forwards the message to an SQS queue. 

# Guidebook
Details about this project are contained in the [guidebook](guidebook/guidebook.md) and should be considered mandatory reading prior to contributing to this project.

# Prerequisites
* [JDK 8](http://zulu.org/) installed and working
* an SQS queue named `sqs-experiment` -- you can select another name but will have to edit the code
* an SQS queue named `secondary-queue` -- you can select another name but will have to edit the code
* the queue should be subscribed to the topic.  Using the SQS console is a convenient way to do this.

# Building
`./gradlew` will pull down any dependencies, compile the source and package everything up.

# Installation
Nothing to install.

# Tips and Tricks
## Starting The Server
Edit `application.yml`, inserting your API keys.

`./gradlew clean bootRun` will start the server on port `8080` and begin producing messages every second. You should see something similar to this:

```
2017-12-23 10:12:43.502  INFO 4833 --- [enerContainer-5] com.example.sns.SnsApplication           :     LogicalResourceId: sqs-experiment
2017-12-23 10:12:44.009  INFO 4833 --- [pool-2-thread-1] com.example.sns.SnsApplication           : Producing message 6a
2017-12-23 10:12:49.009  INFO 4833 --- [pool-2-thread-1] com.example.sns.SnsApplication           : Producing message 6b
2017-12-23 10:12:49.849  INFO 4833 --- [enerContainer-5] com.example.sns.SnsApplication           : Consuming {
  "Type" : "Notification",
  "MessageId" : "188c0f2b-3239-575a-96ee-5d934695fdf1",
  "TopicArn" : "arn:aws:sns:us-west-2:037083514056:example-topic",
  "Subject" : "Subject 6a",
  "Message" : "6a",
  "Timestamp" : "2017-12-23T15:12:44.214Z",
  "SignatureVersion" : "1",
  "Signature" : "Jln5nZhLON0KKYSF8zsoMv9ynYt9o6S96zxXB9LBHG2d1m4cOEeS57/Q0vxlx/LeMNveKeUz4UtdlOG7+rqyThLdK5CrGjhIthzUS9Jte0V5B3FTgfwwjJZIQFAXAKZvDUFL5hBByMaAXt3ohkCvgM2KBeCgahsVn5zL9fj1znciuXelQSxvR/jOwdx2Fp8Lrw/RY3FfdPLawg5Sg+pwIQl4FG0bGgzShjpOA6gvwxg9aB1lWRBJulAuXrZfrN8i6vbr3aAXfOcv05c0DBiGxinnDsvh8BiMgB7h4JpLhDsSFJUg+X4c3z/CXedRQZrHUuXgyfJWvxRePr+hlZiQVw==",
  "SigningCertURL" : "https://sns.us-west-2.amazonaws.com/SimpleNotificationService-433026a4050d206028891664da859041.pem",
  "UnsubscribeURL" : "https://sns.us-west-2.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:us-west-2:037083514056:example-topic:715084eb-8abb-4ba0-bab8-c5cc0ad046fb",
  "MessageAttributes" : {
    "NOTIFICATION_SUBJECT_HEADER" : {"Type":"String","Value":"Subject 6a"},
    "id" : {"Type":"String","Value":"d373db89-eeca-51ad-5ba2-d76bf5112da9"},
    "contentType" : {"Type":"String","Value":"text/plain;charset=UTF-8"},
    "timestamp" : {"Type":"Number.java.lang.Long","Value":"1514041964116"}
  }
} from sqs-experiment
2017-12-23 10:12:49.849  INFO 4833 --- [enerContainer-5] com.example.sns.SnsApplication           :     LogicalResourceId: sqs-experiment
2017-12-23 10:12:51.065  INFO 4833 --- [enerContainer-5] com.example.sns.SnsApplication           : Consuming {
  "Type" : "Notification",
  "MessageId" : "784da7a5-0761-5504-b9b6-f51d73d04faf",
  "TopicArn" : "arn:aws:sns:us-west-2:037083514056:example-topic",
  "Subject" : "Subject 6b",
  "Message" : "6b",
  "Timestamp" : "2017-12-23T15:12:49.221Z",
  "SignatureVersion" : "1",
  "Signature" : "b+b7VJLDqlLm2Q2ZgK3CM3eIo3KQKi7y768iU645tHlpWAPfNJFnQX/biX5tBlScT5Dm3/WYb+iXmTNkwB9rBtx4yAR7rl8yJhz/C4XnGM4X9jLAMhwW1Dcbu7v1O0hghHG3L99FGKq5GX8htE84axvRFLIqaejiuBI+AUZc0b26VT4h9cMyeRD78+K1ymM7zljHK8U3UNLnZXVhssIeoMEb26xIsMsROvI04qfJWCDTvoyWdbM78n05zxgEV5r/UvHir5HA+/t4XO+kE2n1BIMledG0WTbe3q9lNkwg6lFnn7bCYq4W+iUa62v0f0x/hB4SvW8oh/WUdQww2ctwBg==",
  "SigningCertURL" : "https://sns.us-west-2.amazonaws.com/SimpleNotificationService-433026a4050d206028891664da859041.pem",
  "UnsubscribeURL" : "https://sns.us-west-2.amazonaws.com/?Action=Unsubscribe&SubscriptionArn=arn:aws:sns:us-west-2:037083514056:example-topic:715084eb-8abb-4ba0-bab8-c5cc0ad046fb",
  "MessageAttributes" : {
    "NOTIFICATION_SUBJECT_HEADER" : {"Type":"String","Value":"Subject 6b"},
    "id" : {"Type":"String","Value":"cceed84b-6b8c-ca9e-5da5-d2e36024c061"},
    "contentType" : {"Type":"String","Value":"text/plain;charset=UTF-8"},
    "timestamp" : {"Type":"Number.java.lang.Long","Value":"1514041969119"}
  }
} from sqs-experiment
```


# Troubleshooting
## Message Not Arriving On The Queue
It probably means that you forgot to have your queue subscribe to the topic.  The quickest way to accomplish this is to use the SQS console, subscribing to the topic. 

# Contributing

# License and Credits
* This project is licensed under the [Apache License Version 2.0, January 2004](http://www.apache.org/licenses/).
* The guidebook structure was created by [Simon Brown](http://simonbrown.je/) as part of his work on the [C4 Architectural Model](https://c4model.com/).  His books can be [purchased from LeanPub](https://leanpub.com/b/software-architecture).
* Patrick Kua offered [his thoughts on a travel guide to a software system](https://www.safaribooksonline.com/library/view/oreilly-software-architecture/9781491985274/video315451.html) which has been [captured in this template](travel-guide/travel-guide.md).

# List of Changes
