DOCKER_HUB_PERSONAL_REPO?=stefanoboriero

all: docker-all kubernetes-all

compile:
	@mvn clean compile

run:
	@mvn spring-boot:run

docker-all: docker-package docker-tag docker-push

docker-package:
	@mvn spring-boot:build-image

docker-tag:
	@docker tag kubernetes-demo:0.0.1-SNAPSHOT ${DOCKER_HUB_PERSONAL_REPO}/kubernetes-demo

docker-push:
	@docker push ${DOCKER_HUB_PERSONAL_REPO}/kubernetes-demo

kubernetes-deploy:
	@minikube kubectl -- apply -f kubernetes/deployment.yml

kubernetes-remove-deployment:
	@minikube kubectl -- delete deployment kubernetes-demo-deployment

kubernetes-service:
	@minikube kubectl -- apply -f kubernetes/service.yml

kubernetes-remove-service:
	@minikube kubectl -- delete service kubernetes-demo-service

kubernetes-all: kubernetes-deploy kubernetes-service

kubernetes-remove-all: kubernetes-remove-service kubernetes-remove-deployment

