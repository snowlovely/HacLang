# HacLang

一个基于Java的脚本语言，语法类似Python

支持函数
```text
def test(a,b){
    c=a+b;
    print(c);
}

test(1,2);
```

支持闭包
```text
a=fun(b){
    b=b+1;
}
```

集成一些由Java编写的native方法
```text
time=currentTime()
print(time)
a="123"
b=toInt(a)
```