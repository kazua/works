//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2037
//K.A

object problem37 {
    def isPrime(i : Int) = {
        if (i < 2) false
        else (2 until i).exists(i % _ == 0) == false
    }
    def getRPrime(pl : List[Int], acl : List[Int]) : List[Int] = pl match {
        case a if a.size == 0 => acl.reverse
        case a => {
            val nl = pl.map(m => List(1, 3, 7, 9).map(t => (m.toString + t.toString).toInt)).flatten.filter(i => i.toString.takeRight(1) != 9 && isPrime(i) && List(0, 4, 6, 8).map(n => i.toString.indexOf(n.toString)).forall(_ == -1))
            getRPrime(nl, nl.reverse ::: acl)
        }
    }
    def getLPrime(pl : List[Int], acl : List[Int]) : Int = acl match {
        case a if a.size == 11 => acl.sum
        case a => {
            val nl = pl.dropWhile(i => ((1 to i.toString.length - 1).map(j => isPrime(i.toString.drop(j).toInt)).forall(_ == true) == false))
            getLPrime(nl.drop(1), nl.min :: acl)
        }
    }
    def main(args : Array[String]) {
        println(getLPrime(getRPrime(List(2, 3, 5, 7), Nil), Nil))
    }
}