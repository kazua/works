//http://odz.sakura.ne.jp/projecteuler/index.php?cmd=read&page=Problem%208
//K.A

object problem08 {

    def main(args : Array[String]) {
        val nums = "7316717653133062491922511967442657" +
        		"47423553491949349698352031277450632" +
        		"62395783180169848018694788518438586" +
        		"15607891129494954595017379583319528" +
        		"53208805511125406987471585238630507" +
        		"15693290963295227443043557668966489" +
        		"50445244523161731856403098711121722" +
        		"38311362229893423380308135336276614" +
        		"28280644448664523874930358907296290" +
        		"49156044077239071381051585930796086" +
        		"67017242712188399879790879227492190" +
        		"16997208880937766572733300105336788" +
        		"12202354218097512545405947522435258" +
        		"49077116705560136048395864467063244" +
        		"15722155397536978179778461740649551" +
        		"49290862569321978468622482839722413" +
        		"75657056057490261407972968652414535" +
        		"10047482166370484403199890008895243" +
        		"45065854122758866688116427171479924" +
        		"44292823086346567481391912316282458" +
        		"61786645835912456652947654568284891" +
        		"28831426076900422421902267105562632" +
        		"11111093705442175069416589604080719" +
        		"84038509624554443629812309878799272" +
        		"44284909188845801561660979191338754" +
        		"99200524063689912560717606058861164" +
        		"67109405077541002256983155200055935" +
        		"72972571636269561882670428252483600" +
        		"823257530420752963450"

        println(nums.map(_.asDigit).sliding(5).map(_.product).max)
    }
}