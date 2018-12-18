# springRawMVC
A basic project to test the creation of web applications without the use of Spring Boot. Configuration is made trough plain XML files and and so. Application is run inside Jetty Server through command : jetty:run


FRONT-END:

Angular application with basic UI to show the functionality of services, HttpClient and the use of Routings.
Also is using the default Angular cli proxy which can be launched with ng command:

ng serve --open --proxy-config proxy.conf.json

where proxy.conf.json is a simple file with basic configuration for the proxy.

The Angular files used in this example can be found inside this repository with following URL:



BACK-END

Demo application made with plain Spring configuration which provides REST resources to the client. It connects to a local PostgreSQL database which Pool connection information can be found in the persistence-context file.

