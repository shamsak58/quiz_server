#
curl -v --noproxy -X POST -H "Content-Type:application/json" -d "{\"lat\": 100.33, \"lng\": 111.22, \"locationName\" :\"abc\"}"  "http://localhost:8080/location/"