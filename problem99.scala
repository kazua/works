//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%2099
//指数の形で表される2つの数, 例えば 2^11 と 3^7, の大小を調べることは難しくはない. 電卓を使えば, 2^11 = 2048 < 3^7 = 2187 であることが確かめられる.
//しかし, 632382^518061 > 519432^525806 を確認することは非常に難しい (両者ともに300万桁以上になる).
//各行に1組が書かれている1000個の組を含んだ22Kのテキストファイルbase_exp.txtから, 最大の数が書かれている行の番号を求めよ.

//write kazua

import scala.io.Source
import scala.math._

object problem99 {
    def problem99(is : Iterator[String]) = (is.map(l => (log(l.split(',')(0).toDouble) * l.split(',')(1).toDouble)).zipWithIndex).max._2 + 1
    def main(args : Array[String]) {
        println(problem99(Source.fromFile("e:/base_exp.txt")("UTF-8").getLines))
    }
}