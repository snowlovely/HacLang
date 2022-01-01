#include "string"

// isEmpty
test = "hello world";
if string::isEmpty(test)==false {
    print("not null");
}

// contains
if string::contains(test,"world") {
    print("contains world")
}

// split
split = string::split(test," ");
len = length(split);
i = 0;
while i<len {
    print(split[i]);
    i = i + 1;
}

// substr
sub = string::substr(test,1,4);
print(sub);
