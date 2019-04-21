package net.wang.groovy.example.string

def str = "groovy"

//2边填充 先右后左一边一次，直至填充长度为9
println(str.center(9, "a")) //agroovyaa
//左边填充
println(str.padLeft(9, "a")) //aaagroovy
//右边填充
println(str.padRight(9, "a")) //groovyaaa

def str2 = 'hello'
println(str > str2) //直接比较字符串

println(str.getAt(1))
println(str[1])
println(str[1..3])
Thread.sleep(10000)
def str3 = "oo"
println(str-str3) //grvy