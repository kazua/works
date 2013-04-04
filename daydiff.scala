//write kazua
//年月日の中に同じ数値が現れない年月日のリスト

import java.util.Calendar
import java.text.SimpleDateFormat

object daydiff {
    def sdf = new SimpleDateFormat("yyyyMd")
    def daydiff(stday : Calendar, edday : Calendar, acl : List[String]) : List[String] = stday match {
        case s if s.compareTo(edday) > 0 => acl.reverse
        case s => {
            val dt = sdf.format(s.getTime)
            s.add(Calendar.DAY_OF_MONTH, 1)
            dt match {
                case d if d.distinct.size == d.size => daydiff(s, edday, d :: acl)
                case d => daydiff(s, edday, acl)
            }
        }
    }
    def main(args : Array[String]) {
        val mindt = Calendar.getInstance()
        val maxdt = Calendar.getInstance()
        mindt.set(1986, 0, 1)
        maxdt.set(2013, 5, 30)
        daydiff(mindt, maxdt, Nil).foreach(println)
    }
}