
all: kubernetes-prometheus-cluster-role kubernetes-prometheus-service-account kubernetes-prometheus-role-binding kubernetes-prometheus-configmap kubernetes-deployment kubernetes-service
kubernetes-prometheus-cluster-role:
	@minikube kubectl -- apply -f cluster-role.yml

kubernetes-prometheus-service-account:
	@minikube kubectl -- apply -f service-account.yml

kubernetes-prometheus-role-binding:
	@minikube kubectl -- apply -f cluster-role-binding.yml

kubernetes-prometheus-configmap:
	@minikube kubectl -- apply -f configmap.yml

kubernetes-deployment:
	@minikube kubectl -- apply -f deployment.yml

kubernetes-service:
	@minikube kubectl -- apply -f service.yml
