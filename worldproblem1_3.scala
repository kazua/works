//write Kazua

object worldproblem1_3 {
    def PermutationsCheck(chkstr1 : String, chkstr2 : String) : Boolean = {
        if (chkstr1.length != chkstr2.length) return false

        val chgchkstr1 = chkstr1.toList.sortWith((o1, o2) => o1.toString.compareToIgnoreCase(o2.toString) < 0).mkString
        val chgchkstr2 = chkstr2.toList.sortWith((o1, o2) => o1.toString.compareToIgnoreCase(o2.toString) < 0).mkString

        chgchkstr1.equals(chgchkstr2)
    }
    def main(args : Array[String]) {
        println(PermutationsCheck("abcgdjwp", "jagdwbpc"))
        println(PermutationsCheck("23de2546sfh", "d2f3e25s4h6"))
        println(PermutationsCheck("23de2546sfh", "d2f3e45s4h6"))
    }
}