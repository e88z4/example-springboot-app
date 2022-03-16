# App

## Prerequisite

1. Container engine such as Docker, Podman, containerd
2. Kubernetes cluster with local registry (e.g. https://kind.sigs.k8s.io/docs/user/local-registry/)

## Build containerize application

```
docker build ./ -t localhost:5001/app:latest
docker push localhost:5001/app:latest
```

## Deploy to Kubernetes cluster development workload with 2 replica sets

```
kubectl kustomize k8s/overlays/dev | kubectl apply -f -
```

## Deploy to Kubernetes cluster production workload with 4 replica sets

```
kubectl kustomize k8s/overlays/prod | kubectl apply -f -
```

## Test application running on Kubernetes node port

```
curl "$(kubectl get nodes --selector=kubernetes.io/role!=master -o jsonpath={.items[*].status.addresses[?\(@.type==\"InternalIP\"\)].address}):$(kubectl get services --field-selector metadata.name=app-service -o jsonpath={.items[*].spec.ports[0].nodePort})/echo?echo=hello"
```

## Run on localhost 

```
./gradlew clean bootRun
```

## Test on localhost

```
curl "localhost:8080/echo?echo=hello"
{"echo":"ping"}
```
