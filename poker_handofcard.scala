//write kazua

object poker_handofcard {
    def pack[A](ls : List[A]) : List[List[A]] = {
        if (ls.isEmpty) List(Nil)
        else {
            val (head, tail) = ls.span(_ == ls.head)
            if (tail == Nil) List(head)
            else head :: pack(tail)
        }
    }
    def decPokerHand(cdList : List[(String, Int)]) = {
        val mrkCnt = cdList.map(_._1).distinct.size
        val numList = cdList.map(_._2).sorted
        mrkCnt match {
            case m if m == 1 =>
                numList match {
                    case n if n == List(1, 10, 11, 12, 13) => "ロイヤルストレートフラッシュ"
                    case n if n.sliding(2).forall(s => s.head + 1 == s.tail.head) => "ストレートフラッシュ"
                    case n => "フラッシュ"
                }
            case m =>
                numList match {
                    case n if n.sliding(2).forall(s => s.head + 1 == s.tail.head) ||
                              n == List(1, 10, 11, 12, 13) => "ストレート"
                    case n =>
                        val cnt = pack(n.sorted).map(_.size).sorted.reverse
                        cnt match {
                            case c if c.contains(4) => "フォーカード"
                            case c if c.contains(3) && c.contains(2) => "フルハウス"
                            case c if c.contains(3) => "スリーカード"
                            case c if c.filter(_ == 2).size == 2 => "ツーペア"
                            case c if c.filter(_ == 2).size == 1 => "ワンペア"
                            case c => "ブタ"
                        }
                }
        }
    }
    def main(args : Array[String]) {
        println(decPokerHand(List(("クラブ", 11), ("クラブ", 10), ("ハート", 1), ("クラブ", 12), ("クラブ", 13))))
    }
}