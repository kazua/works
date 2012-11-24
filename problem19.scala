//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2019
//K.A

object problem19 {
    def getSunCnt : Int = {
        val dayCnt = List(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        def mlf(st : Int, ed : Int) = for(y <- st to ed; m <- 1 to 12) yield if(m == 2) if (if ((y % 100 == 0 && y % 400 != 0) || (y % 4 != 0)) false else true) 29 else 28 else dayCnt(m - 1)

        val ml1900 = mlf(1900,1900)
        val ml = mlf(1901,2000)

        val mf = ml.foldLeft(List((1 + ml1900.sum) % 7))((wf, d) => ((wf.head + d) % 7) :: wf)
        mf.count(_ == 0)
    }
    def main(args : Array[String]) {
        println(getSunCnt)
    }
}