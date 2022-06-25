docker stop eida-manager
docker rm eida-manager

docker run \
  -p 10325:10325 \
  -v /Library/EidaManager:/Library/EidaManager \
  --name eida-manager \
  eida-manager