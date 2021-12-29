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
print(add(2,8));
