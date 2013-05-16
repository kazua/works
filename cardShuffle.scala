//write kazua

import scala.util.Random

object cardShuffle {
    val rd = new Random
    val cdl = List(("クラブ", 1), ("クラブ", 2), ("クラブ", 3), ("クラブ", 4), ("クラブ", 5), ("クラブ", 6), ("クラブ", 7), ("クラブ", 8), ("クラブ", 9), ("クラブ", 10), ("クラブ", 11), ("クラブ", 12), ("クラブ", 13),
        ("スペード", 1), ("スペード", 2), ("スペード", 3), ("スペード", 4), ("スペード", 5), ("スペード", 6), ("スペード", 7), ("スペード", 8), ("スペード", 9), ("スペード", 10), ("スペード", 11), ("スペード", 12), ("スペード", 13),
        ("ハート", 1), ("ハート", 2), ("ハート", 3), ("ハート", 4), ("ハート", 5), ("ハート", 6), ("ハート", 7), ("ハート", 8), ("ハート", 9), ("ハート", 10), ("ハート", 11), ("ハート", 12), ("ハート", 13),
        ("ダイヤ", 1), ("ダイヤ", 2), ("ダイヤ", 3), ("ダイヤ", 4), ("ダイヤ", 5), ("ダイヤ", 6), ("ダイヤ", 7), ("ダイヤ", 8), ("ダイヤ", 9), ("ダイヤ", 10), ("ダイヤ", 11), ("ダイヤ", 12), ("ダイヤ", 13))

    def shuffle(cd : List[(String, Int)], sn : Int, acl : List[(String, Int)]) : List[(String, Int)] = acl match {
        case a if a.size == sn => a.sorted
        case a => {
            val na = cd(rd.nextInt(cd.size - 1)) :: a
            shuffle(cd diff na, sn, na)
        }
    }
    def main(args : Array[String]) {
        println(shuffle(cdl, 5, Nil))
        println(shuffle(cdl, 5, Nil))
        println(shuffle(cdl, 5, Nil))
        println(shuffle(cdl, 5, Nil))
        println(shuffle(cdl, 5, Nil))
    }
}