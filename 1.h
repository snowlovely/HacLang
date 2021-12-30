#include "http"
#include "string"

// request
def request(url){
    go print("do request");
    data = http::doGet(url,["User-Agent: 4ra1n"]);
    if(string::isEmpty(data[2][0])==false){
        return data[2][0];
    }else{
        return null;
    }
}
url = readFile("1.txt");
print(url);
data=request(url);
print(data);

