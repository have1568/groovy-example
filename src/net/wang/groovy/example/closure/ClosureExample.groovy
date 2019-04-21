package net.wang.groovy.example.closure

//定义一个被闭包
def closure = { str -> println(" param is ${str}") }
//call调用
//closure.call("hello")
//()调用
closure("???")

//闭包传递闭包
closure.call({ println("closure as param !") })

//隐式参数 it关键字
def itClosure = { println("${it}") }
def res = itClosure.call("ClosureExample")

//无返回值
println(res)
//有返回值
println({ return it }.call("res"))

//多个参数 TODO
//def args = { String a, String, b -> println("${a} ${b}") }
//args.call("a","b")

println("==================================================")

//闭包求阶乘
int fab(int number) {
    int res = 1
    1.upto(number, { num -> res *= num })
    return res
}

println(fab(5))


int fab2(int number) {
    int res = 1
    //闭包可以写在方法外
    number.downto(1) {
        num -> res *= num
    }

    return res
}

println(fab2(5))


//累加
int cal(int number) {
    int res = 0
    number.times {
        num -> res += num
    }
    return res
}

println(cal(5)) //0-4的和
println("==================================================")

/**
 * 闭包的三个重要变量：this,owner,delegate
 */

def closureKeyWord = {
    println("scriptClosure this : ${this}")  //this代表当前类
    println("scriptClosure owner : ${owner}") //代表闭包定义处的类或者对象，不仅可以指向当前类，也可以指向嵌套闭包
    println("scriptClosure delegate : ${delegate}") //代表任意对象，默认值一owner一致
}
closureKeyWord.call()

/**this,owner,delegate 全部指向封闭的第一个类 Person。如果不是静态的，则指向一个Person对象*/
class Person {

    def static classClosure = {
        println("classClosre this : ${this}")
        println("classClosre owner : ${owner}")
        println("classClosre delegate : ${delegate}")
    }

    def static say() {
        def methodClosure = {
            println("methedClosre this : ${this}")
            println("methedClosre owner : ${owner}")
            println("methedClosre delegate : ${delegate}")
        }
        methodClosure.call()
    }

}

Person.classClosure.call()
Person.say()
println("==================================================")

/**
 * 闭包嵌套  TODO 为什么不调用 toString方法会出现递归调用？
 */

def nestClosure = {
    def innerClosure = {
        println("innerClosure this : ${this.toString()}") //指向 ClosureExample对象
        println("innerClosure owner : ${owner.toString()}") // 指向nestClosure
        println("innerClosure delegate : ${delegate}") //与owner相等
    }
    innerClosure.delegate = new Object()
    innerClosure.call()
}
nestClosure()
println("==================================================")

