package net.wang.groovy.example.variable

//groovy 脚本文件，可以直接运行

//强类型定义变量
int x = 10
println(x.class) //class java.lang.Integer  groovy 将基本类型装箱成包装类型，groovy 中没有基本类型

//弱类型定义变量
def y = 3.14
println(y.class)  //class java.math.BigDecimal


def z = 3.14D
println(z.class)  //class java.lang.Double

z = "123"
println(z.class)  //class j ava.lang.String