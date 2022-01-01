# HacLang

## 介绍

这是什么？

一个基于Java的脚本语言，打算加入一些Java安全相关的功能，语法类似Python

支持函数
```cpp
def test(a,b){
    c=a+b;
    print(c);
}

test(1,2);
```

支持闭包
```cpp
a=fun(b){
    b=b+1;
}
```

集成一些由Java编写的native方法
```cpp
time=currentTime()
print(time)
a="123"
b=toInt(a)
```