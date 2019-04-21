package net.wang.groovy.example.closure

/**
 * 闭包在字符串中的运用
 */
println("String".each { println it }) //each返回字符串本身 it隐式参数
String s = "such as ==> 1 + 2 = 3 "
println(s.find { it.isNumber() }) //返回第一个是数字的字符 闭包返回Boolean类型
println(s.findAll { it.isNumber() }) //返回所有是数字的字符集合 闭包返回Boolean类型
println(s.any { it.isNumber() }) //字符串里是否有数字
println(s.every { it.isNumber() }) //字符串里是否每一个字符都是数字
println(s.collect { it.toUpperCase() }) //返回字符串大写集合