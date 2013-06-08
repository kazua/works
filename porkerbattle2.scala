//write kazua

import scala.util.Random

object porkerbattle2 {
    val rd = new Random
    val cdl = List(("クラブ", 1), ("クラブ", 2), ("クラブ", 3), ("クラブ", 4), ("クラブ", 5), ("クラブ", 6), ("クラブ", 7), ("クラブ", 8), ("クラブ", 9), ("クラブ", 10), ("クラブ", 11), ("クラブ", 12), ("クラブ", 13),
        ("スペード", 1), ("スペード", 2), ("スペード", 3), ("スペード", 4), ("スペード", 5), ("スペード", 6), ("スペード", 7), ("スペード", 8), ("スペード", 9), ("スペード", 10), ("スペード", 11), ("スペード", 12), ("スペード", 13),
        ("ハート", 1), ("ハート", 2), ("ハート", 3), ("ハート", 4), ("ハート", 5), ("ハート", 6), ("ハート", 7), ("ハート", 8), ("ハート", 9), ("ハート", 10), ("ハート", 11), ("ハート", 12), ("ハート", 13),
        ("ダイヤ", 1), ("ダイヤ", 2), ("ダイヤ", 3), ("ダイヤ", 4), ("ダイヤ", 5), ("ダイヤ", 6), ("ダイヤ", 7), ("ダイヤ", 8), ("ダイヤ", 9), ("ダイヤ", 10), ("ダイヤ", 11), ("ダイヤ", 12), ("ダイヤ", 13))

    def pack[A](ls : List[A]) : List[List[A]] = {
        if (ls.isEmpty) List(Nil)
        else {
            val (hdele, elsele) = ls.span(_ == ls.head)
            if (elsele == Nil) List(hdele)
            else hdele :: pack(elsele)
        }
    }
    def decPokerHand(cdList : List[(String, Int)]) : (String, List[Int]) = {
        val mrkCnt = cdList.map(_._1).distinct.size
        val numList = cdList.map(_._2).sorted
        mrkCnt match {
            case m if m == 1 =>
                val nm = if (numList.contains(1)) 14 else numList.max
                numList match {
                    case n if n == List(1, 10, 11, 12, 13) => ("ロイヤルストレートフラッシュ", List(1, 0, 0))
                    case n if n.sliding(2).forall(s => s.head + 1 == s.tail.head) => ("ストレートフラッシュ", List(2, nm, 0))
                    case n => ("フラッシュ", List(5, nm, 0))
                }
            case m =>
                val nm = if (numList.contains(1)) 14 else numList.max
                numList match {
                    case n if n.sliding(2).forall(s => s.head + 1 == s.tail.head) ||
                        n == List(1, 10, 11, 12, 13) => ("ストレート", List(6, nm, 0))
                    case n =>
                        val cnt = pack(n.sorted).map(_.size)
                        val nsb = pack(n.sorted).map(_.head)
                        cnt match {
                            case c if c.contains(4) => ("フォーカード", List(3, nsb(c.indexWhere(_ == 4)), nsb(c.indexWhere(_ == 1))))
                            case c if c.contains(3) && c.contains(2) => ("フルハウス", List(4, nsb(c.indexWhere(_ == 3)), nsb(c.indexWhere(_ == 2))))
                            case c if c.contains(3) => {
                                val ym = nsb(c.indexWhere(_ == 3))
                                val el = nsb.filterNot(_ == nsb(c.indexWhere(_ == 3)))
                                val em = if (el.contains(1)) 14 else el.max
                                ("スリーカード", List(7, ym, em))
                            }
                            case c if c.filter(_ == 2).size == 2 => {
                                val ym = nsb.filterNot(_ == nsb(c.indexWhere(_ == 1))).max
                                val em = nsb.filterNot(_ == nsb(c.indexWhere(_ == 1))).min
                                ("ツーペア", List(8, ym, em))
                            }
                            case c if c.filter(_ == 2).size == 1 => {
                                val ym = nsb.filterNot(_ == nsb(c.indexWhere(_ == 1))).max
                                val em = nsb.filterNot(_ == nsb(c.indexWhere(_ == 2))).max
                                ("ワンペア", List(9, ym, em))
                            }
                            case c => ("ブタ", List(10, nsb.max, nsb.filterNot(_ == nsb.max).max))
                        }
                }
        }
    }
    def dealCards(cd : List[(String, Int)], sn : Int, acl : List[List[(String, Int)]], cgc : Int) : List[List[(String, Int)]] = acl match {
        case a if a.head.size == sn && a.tail.head.size == sn => cgc match {
            case c if c >= 3 => List(a.head.sorted, a.tail.head.sorted)
            case c => {
                print("現在の手役：")
                a.tail.head.sorted.foreach(print)
                println(s"\n何枚目の手札を交換しますか？数値だけで指定してください（2枚目と3枚目を交換するときは23）")
                try {
                    readInt match {
                        case i if i == 0 => List(a.head.sorted, a.tail.head.sorted)
                        case i => {
                            println(s"${c + 1}回目の手札交換です。")
                            dealCards(cd, sn, List(a.head.sorted, a.tail.head.sorted.zipWithIndex.filterNot(s =>
                                i.toString.toList.map(_.toString).contains((s._2 + 1).toString)).map(_._1).toList), cgc + 1)
                        }
                    }
                } catch {
                    case ex : java.lang.NumberFormatException => {
                        println(Integer.MAX_VALUE + "以下の数値のみ入力可能です。")
                        List(a.head.sorted, a.tail.head.sorted)
                    }
                }
            }
        }
        case a if a.head.size == sn && a.tail.head.size != sn => {
            val na = cd(rd.nextInt(cd.size - 1)) :: a.tail.head
            val lcd = cd diff na
            dealCards(lcd, sn, List(a.head, na), cgc)
        }
        case a => {
            val ns = cd(rd.nextInt(cd.size - 1)) :: a.head
            val ncd = cd diff ns
            val na = ncd(rd.nextInt(ncd.size - 1)) :: a.tail.head
            val lcd = ncd diff na
            dealCards(lcd, sn, List(ns, na), cgc)
        }
    }
    def judge(hc : List[List[(String, Int)]]) : String = {
        val cp = decPokerHand(hc.head)
        val p = decPokerHand(hc.tail.head)
        val cpcd = hc.head.mkString
        val pcd = hc.tail.head.mkString
        val cpy = cp._1
        val py = p._1
        val cpyr = cp._2.head
        val pyr = p._2.head
        val cpym = if (cp._2.tail.head == 1) 14 else cp._2.tail.head
        val pym = if (p._2.tail.head == 1) 14 else p._2.tail.head
        val cpem = if (cp._2.tail.tail.head == 1) 14 else cp._2.tail.tail.head
        val pem = if (p._2.tail.tail.head == 1) 14 else p._2.tail.tail.head

        val wc = if (cpyr < pyr) 1
                 else if (cpyr > pyr) 2
                 else
                     if (cpym > pym) 1
                     else if (cpym < pym) 2
                     else
                         if (cpem > pem) 1
                         else if (cpem < pem) 2
                         else 0

        val wm = if (wc == 0) "引き分けです" else if (wc == 1) "コンピュータの勝ちです" else "あなたの勝ちです"
        s"""|コンピュータの役：$cpy 手札$cpcd
                            |あなたの役：$py 手札$pcd
                            |$wm""".stripMargin
    }
    def main(args : Array[String]) {
        println(judge(dealCards(cdl, 5, List(Nil, Nil), 0)))
    }
}