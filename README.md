# Kubernetes demo with Spring Boot

This is a demo app to play around with Kubernetes and testing out different ways we can communicate between services.

## Prerequisite

To run this application on your machine you need:
* [Maven](https://maven.apache.org/)
* [Java](https://adoptium.net/) version 17 or greater
* [Docker](https://www.docker.com/) and a valid user on [DockerHub](https://hub.docker.com) to push your images
* [Make](https://www.gnu.org/software/make/)
* [minikube](https://minikube.sigs.k8s.io/docs/start/)

## Building the application

Run `make compile` to compile the jar file. Run `make docker-all DOCKER_HUB_PERSONAL_REPO=your-docker-login-id` to build the docker image and push it to DockerHub

> **NOTE** Change the DOCKER_HUB_PERSONAL_REPO to a repo you have permission to push to or you'll run into authentication issues!

## Deploy the application standalone

Run `make kubernetes-all` to deploy the application on Kubernetes and expose it as a Service

## Accessing the application

You can access the app at http://192.168.49.2:30003/hello. 
The app has 3 additional endpoints testing out different ways of connecting between services in the cluster:

* http://192.168.49.2:30003/forwardUsingExternalisedService will get the response contacting a second service using its externalised ip.
* http://192.168.49.2:30003/forwardUsingClusterIp will get the response contacting a second service using its internal clusterIP.
* http://192.168.49.2:30003/forwardUsingServiceName will get the response contacting a second service using its service name, letting Kubernetes resolve the actual service ip.

## Run the application outside Kubernetes

You can also try to run the application outside Kubernetes using the command `make run`: you'll see then that the only
working endpoint would be http://127.0.0.1:8080/forwardUsingExternalisedService (assuming the app is also deployed and running in Kubernetes)
because the other two communication options are available only from within the cluster.

## BONUS: Prometheus monitoring

It's nice to always have your systems monitored. We can set up Prometheus to run and scrape targets in the Kubernetes cluster.
To do so, change directory to `cd kubernetes/prometheus` and do `make all` to deploy all the necessary bits.
You should be able to see yout targets being scraped at http://192.168.49.2:30909/targets!
