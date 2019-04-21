package net.wang.groovy.example.struct

/* switch case */
def x = 1.23
def res
switch (x) {

    case 'foo':
        res = "foo"
        break
    case "1.2":
        res = "1.2"
        break
    case [1.234, "1.23"]:
        res = '''[1.23, "1.23"] '''
        break
    case 1..10:
        res = '''1..10'''
        break
    case Integer:
        res = "Integer"
        break
    case BigDecimal:
        res = "BigDecimal"
        break
    default:
        res = "no match"
        break

}
println(res)

//对范围循环
for (i in 0..9) {
    println(i)
}

//对list循环
for (i in [1, 2, 3]) {

}

//对Map循环
for (i in ["username": "wang", "password": new Object()]) {
    println("key = ${i.key}  value = ${i.value} ")
}
