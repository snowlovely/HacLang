#include "core/http"

// test1
// TEST
def add(a,b){
    c=a+b;
    if (c==10) {
        // native function
        print("test-0");
        return "ok";
    }else{
        print("test-1");
        return "error";
    }
}
data = readFile("1.txt");
print(data);
data = doGet("http://www.4399.com",["User-Agent: test"]);
printArray(data[1])
