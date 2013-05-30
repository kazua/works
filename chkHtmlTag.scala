//write kazua

import scala.io._

object chkHtmlTag {
    val st = "<[^=%].*?>".r

    def chkHtmlTag(fp : String) : String = {
        val src = Source.fromFile(fp)("UTF-8")
        val lines = src.getLines.toList.mkString
        chkHtmlTagProc(st.findAllIn(lines).filterNot(_.contains("META")).toList, Nil, fp)
    }
    def chkHtmlTagProc(tglist : List[String], oplist : List[String], fp : String) : String = tglist match {
        case t if t == Nil => "指定HTMLファイル:" + fp + "は正常な入れ子状態です。"
        case t => t.head match {
            case h if h.take(2) != "</" && h.takeRight(2) != "/>" =>
                chkHtmlTagProc(t.tail, h :: oplist, fp)
            case h if h.take(2) == "</" && h.takeRight(2) != "/>" && h.replaceAll("[</>]", "") == oplist.head.take(h.size - 2).replaceAll("[</>]", "") =>
                chkHtmlTagProc(t.tail, oplist.tail, fp)
            case h if h.take(2) == "</" && h.takeRight(2) != "/>" && h.replaceAll("[</>]", "") != oplist.head.take(h.size - 2).replaceAll("[</>]", "") =>
                "指定HTMLファイル:" + fp + "は正常な入れ子状態ではありません。" + System.getProperty("line.separator") +
                    "閉じタグが漏れているか、タグ終わりスラッシュの付け忘れの可能性があります。異常タグは" + oplist.head + "です。"
            case h if h.takeRight(2) == "/>" =>
                chkHtmlTagProc(t.tail, oplist, fp)
        }
    }
    def main(args : Array[String]) {
        println(chkHtmlTag("E:/jojo2.txt"))
    }
}