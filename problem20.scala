//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2020
//K.A

object problem20 {
    def sum(acl : Int, xs : List[String]) : Int = xs match {
        case Nil => acl
        case y :: ys => sum(acl + y.toInt, ys)
    }
    def problem20(is : Int) : Int ={
        sum(0,(BigInt(1) to is).reverse.product.toString.split("").filter(_ != "").toList)
    }
    def main(args : Array[String]) {
        val ploblem20k = problem20 _
        println(ploblem20k(100))
    }
}