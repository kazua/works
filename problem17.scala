//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2017
//K.A

object problem17 {
    val u20 = List(0, 3, 3, 5, 4, 4, 3, 5, 5, 4, 3, 6, 6, 8, 8, 7, 7, 9, 8, 8)
    val t10 = List(0, 0, 6, 6, 5, 5, 5, 7, 6, 6)
    def calcChrLen(nm : Int, acl : Int) : Int = nm match {
        case a if a < 20 => acl + (if (acl > 0 && a > 0) 3 else 0) + u20(a)
        case a if a < 100 => calcChrLen(a % 10, acl + t10(a / 10))
        case a if a < 1000 => calcChrLen(a % 100, acl + u20(a / 100) + 7)
        case 1000 => 11
    }
    val calcChrLenPt = calcChrLen(_ : Int, 0)
    def problem17 = (1 to 1000).map(calcChrLenPt).sum
    def main(args : Array[String]) {
        println(problem17)
    }
}