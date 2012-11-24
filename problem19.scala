//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2019
//K.A

object problem19 {
    def getSunCnt : Int = {
        val dayCnt = List(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 31, 31)
        val ml = for(y <- 1901 to 2000; m <- 1 to 12) yield if(m == 2) if (if ((y % 100 == 0 && y % 400 == 0) || y % 4 == 0) true else false) 29 else 28
                                                            else dayCnt(m - 1)

        val mf = ml.foldLeft(List(0))((wf, d) => ((wf.head + d) % 7) :: wf)
        mf.count(_ == 6)
    }
    def main(args : Array[String]) {
        println(getSunCnt)
    }
}