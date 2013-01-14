//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2034
//K.A

object problem34 {
    def getLimited(dc : Int) : Int = dc match {
        case a if ((1 to 9).product * a < (BigInt(10).pow(a + 1) - 1)) => (BigInt(10).pow(a + 1)).toInt - 1
        case a => getLimited(dc + 1)
    }
    def getFactSumEq() : Int = {
        (0 to getLimited(1)).map(_.toString.toList).map(_.map(c => (1 to c.toString.toInt).product).sum).zipWithIndex.filter(t => t._1 == t._2).map(_._1).sum - 3
    }
    def main(args : Array[String]) {
        println(getFactSumEq)
    }
}