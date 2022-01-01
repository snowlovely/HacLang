#include "http"
#include "string"

// headers map
headers = newMap();
// set header
putMap(headers,"User-Agent","4ra1n");
// url string
url = "http://127.0.0.1:8080";
// do request
response = http::doGet(url,headers);

// response code
code = getMap(response,"code");
print("response code is: "+code);
// response headers
respHeaders = getMap(response,"headers");
// get response header
connection = getMap(respHeaders,"Connection")
print("Connection: "+connection)
// get response body
responseBody = getMap(response,"body");
if string::contains(responseBody,"Tomcat")==true {
    print("Tomcat")
}
