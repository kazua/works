//write kazua

import twitter4j._
import twitter4j.conf._
import scala.collection.JavaConverters._
import java.awt.Desktop
import java.net._
import javax.swing.JOptionPane
import java.io._

object twitter2 {
    def twittersearch(sn : String, sw : String) = {
        //認証
        val ck = "*************************"//キーは自分で取得してください
        val cs = "*************************"//キーは自分で取得してください
        val tt = TwitterFactory.getSingleton
        val sat = try {
            val is = new ObjectInputStream(new FileInputStream(sn + ".dat"))
            is.readObject.asInstanceOf[twitter4j.auth.AccessToken]
        } catch {
            case e : Exception => null
        }
        if (sat == null) {
            tt.setOAuthConsumer(ck, cs)
            val rt = tt.getOAuthRequestToken
            val url = rt.getAuthorizationURL
            val dt = Desktop.getDesktop
            dt.browse(URI.create(url))
            val pin = JOptionPane.showInputDialog("画面上で表示されたPIN番号を入力して下さい").trim
            if (pin == "") println("PIN番号が未入力です")
            else {
                val at = tt.getOAuthAccessToken(rt, pin)
                val os = new ObjectOutputStream(new FileOutputStream(tt.getScreenName + ".dat"))
                os.writeObject(at)
            }
        } else {
            tt.setOAuthConsumer(ck, cs)
            tt.setOAuthAccessToken(sat)
        }

        println("指定検索文字:" + sw)

        //指定検索文字を含む自ツイートを一覧表示
        val mts = if (sw.trim != "") getTweet(tt, 0, Nil, 1).filter(s => s.getText.indexOf(sw) >= 0) else getTweet(tt, 0, Nil, 1)
        println("mytweet表示件数:" + mts.length + "件")
        for (st <- mts)
            println("@" + st.getUser.getScreenName + "/"
                + st.getUser.getName + ":"
                + st.getText.replace("\n", "")
                + " tweet at " + st.getCreatedAt.formatted("%tY/%<tm/%<td %<tH:%<tM:%<tS")
                + " URL:https://twitter.com/" + st.getUser.getScreenName + "/status/" + st.getId)

        //指定検索文字を含む自タイムラインを一覧表示
        val sts = if (sw.trim != "") getTweet(tt, 0, Nil, 2).filter(s => s.getText.indexOf(sw) >= 0) else getTweet(tt, 0, Nil, 2)
        println
        println("mytimeline表示件数:" + sts.length + "件")
        for (st <- sts)
            println("@" + st.getUser.getScreenName + "/"
                + st.getUser.getName + ":"
                + st.getText.replace("\n", "")
                + " tweet at " + st.getCreatedAt.formatted("%tY/%<tm/%<td %<tH:%<tM:%<tS")
                + " URL:https://twitter.com/" + st.getUser.getScreenName + "/status/" + st.getId)
    }

    def getTweet(tt : twitter4j.Twitter, ni : Long, sts : List[twitter4j.Status], pf : Int, sw : String = "", ln : Int = 300) : List[twitter4j.Status] = {
        val pg = new Paging(1, 100)
        val query = new Query
        if (ni != 0) pg.setMaxId(ni)
        try {
            val mts = if (pf == 1) tt.getUserTimeline(pg)
            else if (pf == 2) tt.getHomeTimeline(pg)
            else {
                query.setQuery(sw)
                query.setMaxId(ni)
                val rs = tt.search(query)
                rs.getTweets
            }
            mts.size match {
                case i if i == 0 || sts.length + i > ln => sts
                case _ => getTweet(tt, mts.get(mts.size - 1).getId - 1, sts ::: mts.asScala.toList, pf, sw, ln)
            }
        } catch {
            case e : TwitterException => {
                if (e.getStatusCode == 400 || e.getStatusCode == 429) sts
                else {
                    e.printStackTrace
                    Nil
                }
            }
            case e : Exception => {
                e.printStackTrace
                Nil
            }
        }
    }

    def main(args : Array[String]) {
        twittersearch(args(0), args(1))
    }
}