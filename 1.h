#include "http"
#include "string"

map = newMap();
putMap(map,"User-Agent","test");
data = http::doPost("http://www.4399.com",map,"data=test%23name=who","form");
code = getMap(data,"code");
print(code)
if toInt(code)==200{
    print("success");
}
headers = getMap(data,"headers")
cookie = getMap(headers,"Server")
print(cookie);


