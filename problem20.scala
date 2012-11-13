//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2020
object problem20 {
    def main(args : Array[String]) {
        var sum = 0
        (BigInt(1) to 100).reverse.product.toString.split("").filter(_ != "").foreach(sum += _.toInt)
        println(sum)
    }
}