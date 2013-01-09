//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2026
//K.A

object problem026 {
    def getRecDecMax(mn : Int) : Int = {
        val rd = (2 until mn).map(i => (1 until mn).dropWhile(BigInt(10).modPow(_, i) != 1)).map(k => k match { case a if a.isEmpty => 0 case a => a.min })
        rd.indexOf(rd.max) + 2
    }
    def main(args : Array[String]) {
        val mn = 1000
        println(getRecDecMax(mn))
    }
}