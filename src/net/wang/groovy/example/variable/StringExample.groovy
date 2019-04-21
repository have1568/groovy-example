package net.wang.groovy.example.variable

def name = 'hello world !'
println(name.class) //class java.lang.String

//有格式的字符串
def thuple = '''\
           three 
           single 
           string
'''
println(thuple.class)
println(thuple)

//可扩展字符串
def doubleName = "this is a common String" //class java.lang.String
println(doubleName.class)

//含表达式的可扩展字符串
def extendStr = "${1 == 1}${doubleName}"
println(extendStr.class) //class org.codehaus.groovy.runtime.GStringImpl

//extendStr作为参数 在调用echo时，自动转换成 String类型
def echo = { String str -> return str }

def res = echo.call(extendStr)
println(res.class)