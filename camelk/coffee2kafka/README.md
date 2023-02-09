# Preparation:
1. Installing Camel K Operator:
    a. Go to “Operator Hub” 
    b. Search for “Camel K Operator”
    c. Install the Operator with all defaults
    d. Create an “Integration Platform”

# Demo Flow

## Prepare Project in OpenShift
1. Create a Project

2. Check that all Operators are available:
```
      oc get csv
```

## Get all the properties / tokens from the various systems:
- (optional) Gitter: https://developer.gitter.im/apps
- Slack: https://api.slack.com/
Also, create a web hook which is required for the standard Kamelet “slack-sink”: https://api.slack.com/messaging/webhooks

- Kafka: https://developers.redhat.com/products/red-hat-openshift-streams-for-apache-kafka/getting-started?extIdCarryOver=true&sc_cid=701f2000000txokAAA


## Create a secret with all properties:
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

# Slack credentials
sk.channel.name=
slack.weburi=

# Kafka credentials
kafka.bootstrapServer=
kafka.clientsecret=
kafka.clientid=
kafka.topic=
```


## Configure Kafka:
1. Create Kafka Instance
2. Create Service Account   
3. Give access to the Service Account
4. Create topic

## Create Connection from coffee-source to Kafka
1. Import Kamelet [“coffee-source”](https://raw.githubusercontent.com/skraft-redhat/micro-demo-architecture-repo/main/camelk/coffee2kafka/Kamelets/coffee-source.yaml) 

2. Create the Format Transformation:
- Create folder "maps" (for storing all the format transformations)

- Create a [jslt FormatTransformation](https://raw.githubusercontent.com/skraft-redhat/micro-demo-architecture-repo/main/camelk/coffee2kafka/maps/coffee2k.jslt) in the folder maps

- Convert it into a Config map:
```
oc create cm stage4-transform --from-file=maps
```

4. Create a [KameletBinding](https://raw.githubusercontent.com/skraft-redhat/micro-demo-architecture-repo/main/camelk/coffee2kafka/flows/coffee2k.yaml) from coffee-source to Kafka


**_IMPORTANT: Make sure that you are pointing to the right Secret and Configmap that we have created earlier_**

```
 annotations:
   trait.camel.apache.org/mount.resources: "configmap:stage4-transform"
   trait.camel.apache.org/mount.configs: "secret:stage4"
```

5. Check in the RHOSAK GUI the results:
Go to https://console.redhat.com/application-services/streams/kafkas
Click on topic -> Messages


##Create Connection from Kafka to Slack:
1. Create the Format Transformation:
Create the [jslt FormatTransformation](https://raw.githubusercontent.com/skraft-redhat/micro-demo-architecture-repo/main/camelk/coffee2kafka/maps/coffee2k.jslt) in the folder maps

2. Re-build the config map:

- Delete the config map:

```
Oc delete cm stage4-transform
```

- Convert it (and the previously created mapping) into a Config map:
```
oc create cm stage4-transform --from-file=maps
```

3. Create KameletBinding [Kafka2Slack](https://raw.githubusercontent.com/skraft-redhat/micro-demo-architecture-repo/main/camelk/coffee2kafka/maps/coffee2k.jslt)

**_IMPORTANT:
 Make sure that you are pointing to the right Secret and Configmap that we have created earlier_**

```
 annotations:
   trait.camel.apache.org/mount.resources: "configmap:stage4-transform"
   trait.camel.apache.org/mount.configs: "secret:stage4"
```

Now, you should see in slack chat messages coming in!!
:smiley:
