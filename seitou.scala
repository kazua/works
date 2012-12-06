object seitou {

    trait party {
        def partyName : String = "政党名"
        def createManifest : String = "マニフェスト"
        def hasOzawa : Boolean = false
        def badwords : List[String] = "卒原発" :: "脱原発" :: "子ども手当" :: "沖縄基地県外移設" :: Nil
        def isBadParty : Boolean = if (hasOzawa || badwords.filter(s => createManifest.contains(s)).length >= 2) true else false
        def matome = partyName + "は" + (if (isBadParty) "悪い" else "悪くない") + "政党です。"
    }

    class ldp extends party {
        override def partyName  = "自由民主党"
        override def createManifest = "国土強靭化・国防軍"
        override def hasOzawa = false
    }

    class ksd extends party {
        override def partyName  = "国民の生活が第一"
        override def createManifest = "卒原発・消費税増税反対"
        override def hasOzawa = true
    }

    class nmt extends ksd with party {
        override def partyName  = "日本未来の党"
        override def createManifest = "卒原発・子ども手当"
    }

    def main(args : Array[String]) {
        val ldp = new ldp
        val nmt = new nmt
        println(ldp.matome)
        println(nmt.matome)
    }

}