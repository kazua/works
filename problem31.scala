//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2031
//write kazua

object problem31 {
    val coinlist = List(1, 2, 5, 10, 20, 50, 100, 200)
    def getCoinComb(cl : List[Int], ta : Int, ac : Int) : Int = cl match {
        case h :: t if h + ac > ta => 0
        case h :: t if h + ac == ta => 1
        case h :: t => getCoinComb(cl, ta, ac + h) + getCoinComb(t, ta, ac)
        case nil => 0
    }
    def main(args : Array[String]) {
        println(getCoinComb(coinlist, 200, 0))
    }
}