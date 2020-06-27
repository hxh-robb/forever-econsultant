build_cmd=$(readlink -f "$0")
project_home=$(dirname "${build_cmd}")

cd ${project_home}
./mvnw clean package -DskipTests && docker build --tag "forever-demo-server:0.1" . && docker rmi $(docker images -qa -f 'dangling=true')