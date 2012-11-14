//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2020
//K.A
object problem20 {
    def problem20(is : Int) : Int ={
        var sum = 0
        (BigInt(1) to is).reverse.product.toString.split("").filter(_ != "").foreach(sum += _.toInt)
        sum
    }
    def main(args : Array[String]) {
        val ploblem20k = problem20 _
        println(ploblem20k(100))
    }
}