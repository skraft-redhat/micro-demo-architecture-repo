# Overview
<br>This is a series of modules to showcase different options with regard to RBAC. The basic architecture is always the same:
+ A **client** has three endpoints (*publicService*, *userService*, *adminService*) that is calling respective end-points from a **server**
+ A **server** allows all access to the *publicService*, only "user" access to the *userService* and only "admin" access to the *adminService*

<br>See below a table with an overview about the different modules:<br>

| Module |Description | Options | Concepts showcased | Other components required? |
| --- | --- | --- | --- | --- |
|[RBAC with http Query Headers](https://github.com/skraft-redhat/micro-demo-architecture-repo/tree/main/RBAC-JWT-Demo/1a-BasicAuthentication)|This is the most basic module where the "role" is transferred as a Query Header. The server validates the role programmatically.||REST Client \&Server||
|[RBAC with Basic Authentication](https://github.com/skraft-redhat/micro-demo-architecture-repo/tree/main/RBAC-JWT-Demo/1b-Authentication-via-http-header)|The client uses browser-based Basic Authentication. This is then propagated to the Server. |Credentials are specified in .properties file|Basic Authentication<br>Http Header Propogation||
|||Credentials are stored in a database|Basic Authentication<br>JPA|Exernal database|
|RBAC with JWT|In order to establish trust, a JWT is used. |Creating JWT manually|JWT||
|||Creating JWT with JWT Build|JWT||
|||Adding JWT automatically to the header|JWT||
|RBAC with Keycloak, decentral|Keycloak acts as an intermediary to issue a JWT token. The server validates the token and enforces RBAC.|ClientCredentials Flow||Keycloak|
|||Resource Owner Password Credentials||Keycloak|
|||Authorization Flow||Keycloak|
|RBAC with Keycloak, central|Access Policies can be centrally managed in Keycloak. The application only gets notice whether access shall be granted or not.|Policies specified in .properties file||Keycloak|
|||Policies specified in Keycloak||Keycloak|
|RBAC with Keycloak on OpenShift|This is the same scenario as above, deployed on OpenShift.|||Keycloak,<br>OpenShift|
|RBAC with Keycloak and 3Scale on Openshift|In this scenario the server uses a proxied gateway (3Scale) which is responsible for enforcing RBAC.|||Keycloak,<br>OpenShift,<br>3Scale|
|RBAC with OpenShift Service Mesh|Client and Server are both managed by OpenShift Service Mesh. The JWT is created outside and enforced by the Service Mesh.||||Keycloak,<br>OpenShift,<br>Service Mesh|