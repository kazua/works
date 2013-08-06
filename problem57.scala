//http://projecteuler.net/index.php?section=problems&id=57(英語)
//http://x2357.github.io/projecteuler_jatrans/problem/57.html(日本語)
//2の平方根は無限に続く連分数で表すことができる.
//√ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
//最初の4回の繰り返しを展開すると以下が得られる.
//1 + 1/2 = 3/2 = 1.5
//1 + 1/(2 + 1/2) = 7/5 = 1.4
//1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
//1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
//次の3つの項は99/70, 239/169, 577/408である. 第8項は1393/985である. これは分子の桁数が分母の桁数を超える最初の例である.
//最初の1000項を考えたとき, 分子の桁数が分母の桁数を超える項はいくつあるか?

//Kazua

import scala.math._

object problem57 {
    def problem57(n : Int = 1, m : BigInt = 2, c : BigInt = 3, a : Int = 0) : Int = a match {
        case a if n == 1000 => a
        case _ =>
            if ((c + 2 * m).toString.size > (c + 2 * m - m).toString.size)
                problem57(n + 1, c + 2 * m - m, c + 2 * m, a + 1)
            else
                problem57(n + 1, c + 2 * m - m, c + 2 * m, a)
    }
    def main(args : Array[String]) {
        println(problem57())
    }
}