//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2041
//K.A

object problem41 {
    def isPrime(i : Int) = {
        if (i < 2) false
        else (2 until i).exists(i % _ == 0) == false
    }
    def getPandigitalPrime(nowNumdgt : Int, maxNumdgt : Int, acl : Int) : Int = nowNumdgt match {
        case n if n == 2 || acl != 0 => acl
        case n if (1 to n).reduceLeft(_ + _) % 3 != 0 => getPandigitalPrime(nowNumdgt - 1, maxNumdgt, (1 to n).permutations.toList.map(_.mkString.toInt).reverse.dropWhile(!isPrime(_)).max)
        case n if (1 to n).reduceLeft(_ + _) % 3 == 0 => getPandigitalPrime(nowNumdgt - 1, maxNumdgt, acl)
    }
    def main(args : Array[String]) {
        println(getPandigitalPrime(9, 9, 0))
    }
}