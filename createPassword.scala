//write kazua

import scala.util.Random

object createPassword {
    val rd = new Random()

    val ba = List("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")
    val sa = List("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z")
    val nm = List("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
    val yk = List("!", "\"", "#", "$", "%", "&", "'", "(", ")", "=", "~", "|", "-", "^", "@", "[", ";", ":", "]", ",", ".", "/", "`", "{", "+", "*", "}", ">", "?", " ")
    val mk = List("(", "`", "~", "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "_", "+", "-", "=", "{", "}", "|", "[", "]", ":", "\"", ";", "'", "<", ">", "?", ",", ".", "/", ")")
    val gk = List("!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~")
    val ek = List(".")

    def createPassword(tgt : String, emin : Int = 6, emax : Int = 10, cpf : Int = 0) : String = cpf match {
        case c if c == 0 => tgt match {
            case a if a == "google" => createPasswordProc(ba ::: sa ::: nm ::: gk, rd.shuffle(Range(8, 30).toList).head, "")
            case a if a == "msn" => createPasswordProc(ba ::: sa ::: nm ::: mk, rd.shuffle(Range(6, 30).toList).head, "")
            case a if a == "yahoo" => createPasswordProc(ba ::: sa ::: nm ::: yk, rd.shuffle(Range(6, 30).toList).head, "")
            case a => createPasswordProc(ba ::: sa ::: nm ::: ek, rd.shuffle(Range(emin, emax).toList).head, "")
        }
        case c => tgt match {
            case a if a == "google" => createPasswordProcCp(rd.shuffle(ba), rd.shuffle(sa), rd.shuffle(nm), rd.shuffle(gk), rd.shuffle(Range(8, 30).toList).head, "")
            case a if a == "msn" => createPasswordProcCp(rd.shuffle(ba), rd.shuffle(sa), rd.shuffle(nm), rd.shuffle(mk), rd.shuffle(Range(6, 30).toList).head, "")
            case a if a == "yahoo" => createPasswordProcCp(rd.shuffle(ba), rd.shuffle(sa), rd.shuffle(nm), rd.shuffle(yk), rd.shuffle(Range(6, 30).toList).head, "")
            case a => createPasswordProcCp(rd.shuffle(ba), rd.shuffle(sa), rd.shuffle(nm), rd.shuffle(ek), rd.shuffle(Range(emin, emax).toList).head, "")
        }
    }
    def createPasswordProc(src : List[String], len : Int, acl : String) : String = acl match {
        case a if a.length == len => a
        case a => createPasswordProc(rd.shuffle(src), len, a + rd.shuffle(src).head)
    }
    def createPasswordProcCp(ba : List[String], sa : List[String], nm : List[String], kg : List[String], len : Int, acl : String) : String = acl match {
        case a if a.length >= len => a.take(len)
        case a => createPasswordProcCp(rd.shuffle(ba), rd.shuffle(sa), rd.shuffle(nm), rd.shuffle(kg), len, a + rd.shuffle(ba.head :: sa.head :: nm.head :: kg.head :: Nil).mkString)
    }
    def main(args : Array[String]) {
        println(createPassword("google"))
        println(createPassword("msn"))
        println(createPassword("yahoo"))
        println(createPassword("jitaku"))
        println(createPassword("jitaku2", 4, 5))

        println(createPassword("google", cpf = 1))
        println(createPassword("msn", cpf = 1))
        println(createPassword("yahoo", cpf = 1))
        println(createPassword("jitaku", cpf = 1))
        println(createPassword("jitaku2", 4, 5, 1))
    }
}