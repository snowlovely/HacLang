# HacLang

## 介绍

这是一个基于`Java`的脚本语言，主要面向渗透测试与安全研究人员，提供了一些简单高效的内置库

大致原理
1. 实现基于状态机的词法分析
2. 巴科斯范式（BNF）生成抽象语法树
3. 递归遍历抽象语法树执行代码

功能介绍
- 类型只有五种：字符串，数字，数组，哈希表，对象
- 语法简洁：类似`Python`与`Golang`
- 集成各种`Java`安全相关的库

## 使用

### 01 Hello World

新建一个文件：`1.h`并编写如下内容

```cpp
print("hello world");
```

执行：`java -jar HacLang.jar 1.h`

### 02 if else

类似`Golang`的写法

```cpp
a = 1;
b = 2;
if a+b == 3 {
    // do something
} else {
    // do something
}
```

### 03 while

支持`while`循环

```cpp
i = 0;
while i < 10 {
    i = i + 1;
    print(i);
}
```

### 04 函数

类似`Python`的写法

```cpp
def test(a,b) {
    if a > b {
        return a;
    } else {
        return b;
    }
}
print(test(1,2));

a = fun(b) {
    print(b)
}

a("hello world");
```

### 05 多线程

类似`Golang`的写法

```cpp
def test() {
    print(2);
}

go test();
```

### 06 数组

类似`Python`的写法

```cpp
array = [1,2,3];
print(array);
str = ["test1","test2","test3"]
print(str);
```

### 07 内置库：Time

支持简单的时间获取操作

```cpp
// 程序运行当前时间
print(currentTime());
// 格式化后的当前时间
print(formatTime());
```

### 08 内置库：Input

命令行输入操作

```cpp
data = input();
print(data);
```

### 09 内置库：Collection

这是最重要的一个库，底层是`HashMap`

```cpp
// new map
map = newMap();
// put
putMap(map,"key","value");
// get
data = getMap(map,"key");
print(data);
// clear
clearMap(map);
data = getMap(map,"key");
print(data);
```

### 10 内置库：Convert

用于类型转换

```cpp
a = "1";
b = toInt(a);
c = toStr(b);
```

### 11 内置库：File

简单的文件读写操作

```cpp
writeFile("test.txt","hello world");
data = readFile("test.txt");
print(data);
```

### 12 内置库：Print

简单的打印函数，会根据输入的类型判断如何打印

```cpp
a = "hello world";
print(a);
```

### 13 内置库：Util

目前只提供计算长度的功能，会根据输入类型判断

```cpp
a = "test";
print(length(a));
b = [1,2,3,4,5];
print(length(b));
```