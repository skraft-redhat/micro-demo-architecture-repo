# Preparation:
1. Installing Camel K Operator:
    a. Go to “Operator Hub” 
    b. Search for “Camel K Operator”
    c. Install the Operator with all defaults
    d. Create an “Integration Platform”

# Demo Flow

1. Prepare Project in OpenShift
    a. Create a Project

    b. Check that all Operators are available:
```
      oc get csv
```

2. Get all the properties / tokens from the various systems:
- (optional) Gitter: https://developer.gitter.im/apps
- Slack: https://api.slack.com/
Also, create a web hook which is required for the standard Kamelet “slack-sink”: https://api.slack.com/messaging/webhooks

- Kafka: https://developers.redhat.com/products/red-hat-openshift-streams-for-apache-kafka/getting-started?extIdCarryOver=true&sc_cid=701f2000000txokAAA


3. Create a secret with all properties:
```
oc create secret generic stage4 --from-file=[File Location]
```

!!! IMPORTANT: The file needs to be named “stage4.properties”

The structure needs to be like this:
```
# Allows HTTP streaming from Gitter
camel.main.streamCachingEnabled=false

# On shutdown it reduces waiting time when stoping Camel's streaming listener
camel.main.shutdownTimeout = 5

# Gitter credentials
gitter.token=
gitter.room=
#gitter.room.name=room10

# Slack
# credentials
slack.token=

# Kafka credentials
kafka.bootstrapServer=
kafka.clientsecret=
kafka.clientid=
kafka.topic=
```


4. Configure Kafka:
a. Create Kafka Instance
b. Create Service Account   
c. Give access to the Service Account
d. Create topic
5. Import Kamelet “coffee-source” 


6. Create Connection from coffee-source to Kafka:
a. Create the Format Transformation:
b. Create FormatTransformation in the folder maps
c.Convert it into a Config map:
```
oc create cm stage4-transform --from-file=maps
```

7. Create KameletBinding:
Generate from coffee-source to Kafka
Make sure that you are pointing to the right Secret and Configmap that we have created earlier

 annotations:
   trait.camel.apache.org/mount.resources: "configmap:stage4-transform"
   trait.camel.apache.org/mount.configs: "secret:stage4"


Check in the RHOSAK GUI the results:
Go to https://console.redhat.com/application-services/streams/kafkas
Click on topic -> Messages


Create Connection from Kafka to Slack:
Create the Format Transformation:
Create the Format Transformation:
Create FormatTransformation in the folder maps
Make sure to update the channel with the right channel id from Slack:


Channel ID can be found at the bottom!!

Delete the config map:
Oc delete cm stage-transform
Convert it (and the previously created mapping) into a Config map:

oc create cm stage4-transform --from-file=maps

Create KameletBinding:
Generate from Kafka2Slack
Make sure that you are pointing to the right Secret and Configmap that we have created earlier

 annotations:
   trait.camel.apache.org/mount.resources: "configmap:stage4-transform"
   trait.camel.apache.org/mount.configs: "secret:stage4"


Now, you should see chat messages coming in 
