//write kazua

import scala.util.Random

object numQuiz {
    val rd = new Random()
    def numQuiz(cpn : Int = rd.nextInt(101), cnt : Int = 1, msg : String = "") : String = msg match {
        case m if m.indexOf("正解") >= 0 => cnt.toString + "回目で正解しました。おめでとうございます。"
        case m => {
            if (cnt == 1 && msg == "") println("1から100までの数値でコンピュータが何を選んだか当ててください。")
            if (msg != "") println(msg)
            try {
                readInt match {
                    case i if i == 0 => "ギブアップされました。次回の挑戦をお待ちしております。"
                    case i if i > cpn => numQuiz(cpn, cnt + 1, "その数値より小さい値をコンピュータは選択しています。")
                    case i if i < cpn => numQuiz(cpn, cnt + 1, "その数値より大きい値をコンピュータは選択しています。")
                    case i if i == cpn => numQuiz(cpn, cnt, "正解")
                }
            } catch {
                case ex : java.lang.NumberFormatException => numQuiz(cpn, cnt, "数値のみ入力可能です。")
            }
        }
    }
    def main(args : Array[String]) {
        println(numQuiz())
    }
}