docker stop eida-shard
docker rm eida-shard

docker run \
  -p 10830:10830 \
  -v /Library/EidaShard:/Library/EidaShard \
  --name eida-shard \
  eida-shard


