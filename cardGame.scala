//write kazua

import scala.util.Random

object cardGame {
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
    def bjnumclc(acl : List[Int], ai : Int) : Int = acl match {
        case x if x == Nil || x.size == 0 => ai
        case 1 :: xs if bjnumclc(xs, 0) + ai + 11 > 21 => bjnumclc(xs, ai + 1)
        case 1 :: xs if bjnumclc(xs, 0) + ai + 11 <= 21 => bjnumclc(xs, ai + 11)
        case x if x.head >= 11 => bjnumclc(x.tail, ai + 10)
        case x => bjnumclc(x.tail, ai + x.head)
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
    def dealPokerCards(cd : List[(String, Int)], acl : List[List[(String, Int)]], cgc : Int) : List[List[(String, Int)]] = acl match {
        case a if a.head.size == 5 && a.tail.head.size == 5 => cgc match {
            case c if c >= 3 => List(a.head.sorted, a.tail.head.sorted)
            case c => {
                println("現在のあなたの手札：" + a.tail.head.sorted.mkString)
                println("何枚目の手札を交換しますか？数値だけで指定してください（2枚目と3枚目を交換するときは23）")
                try {
                    readInt match {
                        case i if i == 0 => List(a.head.sorted, a.tail.head.sorted)
                        case i => {
                            println(s"${c + 1}回目の手札交換です。")
                            dealPokerCards(cd, List(a.head.sorted, a.tail.head.sorted.zipWithIndex.filterNot(s =>
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
        case a if a.head.size == 5 && a.tail.head.size != 5 => {
            val na = cd(rd.nextInt(cd.size - 1)) :: a.tail.head
            val lcd = cd diff na
            dealPokerCards(lcd, List(a.head, na), cgc)
        }
        case a => {
            val ns = cd(rd.nextInt(cd.size - 1)) :: a.head
            val ncd = cd diff ns
            val na = ncd(rd.nextInt(ncd.size - 1)) :: a.tail.head
            val lcd = ncd diff na
            dealPokerCards(lcd, List(ns, na), cgc)
        }
    }
    def dealBlackjackCards(cd : List[(String, Int)], acl : List[List[(String, Int)]]) : List[List[(String, Int)]] = acl match {
        case a if bjnumclc(a.head.map(_._2), 0) >= 17 && a.tail.head.size >= 2 && bjnumclc(a.tail.head.map(_._2), 0) < 21 => {
            print("現在のディーラーの手札のうち1枚：")
            println(a.head.sorted.head)
            print("現在のあなたの手札：" + a.tail.head.sorted.mkString)
            println("\n手札の合計数は" + bjnumclc(a.tail.head.map(_._2), 0))
            println("ヒットしますか？スタンドしますか？（ヒット（もう1枚取得）は1、スタンド（勝負）は0を入力してください)")
            try {
                readInt match {
                    case i if i == 0 => List(a.head.sorted, a.tail.head.sorted)
                    case i if i == 1 => {
                        val na = cd(rd.nextInt(cd.size - 1)) :: a.tail.head
                        val lcd = cd diff na
                        dealBlackjackCards(lcd, List(a.head.sorted, na))
                    }
                    case i => {
                        println("1（ヒット）か0（スタンド）のどちらかを入力してください。")
                        dealBlackjackCards(cd, a)
                    }
                }
            } catch {
                case ex : java.lang.NumberFormatException => {
                    println("入力不可能文字です。")
                    List(a.head.sorted, a.tail.head.sorted)
                }
            }
        }
        case a if (a.head.size >= 2 && bjnumclc(a.head.map(_._2), 0) >= 21) || (a.tail.head.size >= 2 && bjnumclc(a.tail.head.map(_._2), 0) >= 21) => {
            List(a.head.sorted, a.tail.head.sorted)
        }
        case a if a.head.size >= 2 && bjnumclc(a.head.map(_._2), 0) < 17 => {
            val ns = cd(rd.nextInt(cd.size - 1)) :: a.head
            val ncd = cd diff ns
            dealBlackjackCards(ncd, List(ns, a.tail.head))
        }
        case a => {
            val ns = cd(rd.nextInt(cd.size - 1)) :: a.head
            val ncd = cd diff ns
            val na = ncd(rd.nextInt(ncd.size - 1)) :: a.tail.head
            val lcd = ncd diff na
            dealBlackjackCards(lcd, List(ns, na))
        }
    }
    def judgePoker(hc : List[List[(String, Int)]]) : String = {
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
        else if (cpym > pym) 1
        else if (cpym < pym) 2
        else if (cpem > pem) 1
        else if (cpem < pem) 2
        else 0

        val wm = if (wc == 0) "引き分けです" else if (wc == 1) "コンピュータの勝ちです" else "あなたの勝ちです"
        s"""|コンピュータの役：$cpy 手札$cpcd
                            |あなたの役：$py 手札$pcd
                            |$wm""".stripMargin
    }
    def judgeBlackjack(hc : List[List[(String, Int)]]) : String = {
        val cp = hc.head
        val p = hc.tail.head
        val cpcd = cp.mkString
        val pcd = p.mkString
        val cpn = bjnumclc(cp.map(_._2), 0)
        val pn = bjnumclc(p.map(_._2), 0)
        val cps = cp.size
        val ps = p.size
        val cpy = ""
        val py = ""

        val wc = if (cpn == 21 && pn == 21 && cps == 2 && ps == 2) 0
        else if (cpn > 21 && pn <= 21) 2
        else if (pn > 21) 1
        else if (cpn == 21 && cps == 2 && ps != 2) 1
        else if (pn == 21 && cps != 2 && ps == 2) 2
        else if (pn > cpn) 2
        else if (cpn > pn) 1
        else 0

        val nbj = if ((cpn == 21 && cps == 2) || (pn == 21 && ps == 2)) "ナチュラルブラックジャックで"
        else ""

        val wm = if (wc == 0) "引き分けです" else if (wc == 1) nbj + "ディーラーの勝ちです" else nbj + "あなたの勝ちです"
        s"""|ディーラーの手札の合計数：$cpn 手札$cpcd
                            |あなたの手札の合計数：$pn 手札$pcd
                            |$wm""".stripMargin
    }
    def main(args : Array[String]) {
        if (args.size == 0 || args(0) == "") println(judgePoker(dealPokerCards(cdl, List(Nil, Nil), 0)))
        else println(judgeBlackjack(dealBlackjackCards(cdl, List(Nil, Nil))))
    }
}