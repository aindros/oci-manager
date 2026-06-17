OCI_CTL    = podman
IMAGE_NAME = aiezzi/oci-manager
MVN        = JAVA_VERSION=17 ./mvnw

run-dev:
	${MVN} quarkus:dev

native:
	${MVN} package -Dnative

package:
	${MVN} clean $@

image:
	${MVN} package -Dquarkus.container-image.build=true
