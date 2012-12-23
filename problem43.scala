//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2043
//K.A

object problem43 {
    def getPandigitalSum(maxNum : Int) : BigInt = {
        (0 to maxNum).permutations.map(_.mkString).filter(s =>
                                                         ((s.substring(0, 1).toInt != 0) &&
                                                         (s.substring(1, 4).toInt % 2 == 0) &&
                                                         (s.substring(2, 5).toInt % 3 == 0) &&
                                                         (s.substring(3, 6).toInt % 5 == 0) &&
                                                         (s.substring(4, 7).toInt % 7 == 0) &&
                                                         (s.substring(5, 8).toInt % 11 == 0) &&
                                                         (s.substring(6, 9).toInt % 13 == 0) &&
                                                         (s.substring(7, 10).toInt % 17 == 0)
                                                         )).map(i => BigInt(i)).sum
    }
    def main(args : Array[String]) {
        println(getPandigitalSum(9))
    }
}