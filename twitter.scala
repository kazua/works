//write kazua

import twitter4j._
import twitter4j.conf._
import scala.collection.JavaConverters._

object twitter {
    def twitterinfo(sw : String) = {

        val cb = new ConfigurationBuilder
        cb.setOAuthConsumerKey("*************************")//キーは自分で取得してください
            .setOAuthConsumerSecret("********************************************")//キーは自分で取得してください
            .setOAuthAccessToken("**************************************************")//キーは自分で取得してください
            .setOAuthAccessTokenSecret("*******************************************")//キーは自分で取得してください
        val tf = new TwitterFactory(cb.build)
        val tt = tf.getInstance

        //自分のツイートした内容を表示
        val mts = getTweet(tt, 0, Nil, 1)
        println("mytweet表示件数:" + mts.length + "件")
        for (st <- mts)
            println("@" + st.getUser.getScreenName + "/"
                + st.getUser.getName + ":"
                + st.getText.replace("\n", "")
                + " tweet at " + st.getCreatedAt.formatted("%tY/%<tm/%<td %<tH:%<tM:%<tS")
                + " URL:https://twitter.com/" + st.getUser.getScreenName + "/status/" + st.getId)

        //自分のタイムラインに表示されている内容を表示
        val sts = getTweet(tt, 0, Nil, 2)
        println
        println("mytimeline表示件数:" + sts.length + "件")
        for (st <- sts)
            println("@" + st.getUser.getScreenName + "/"
                + st.getUser.getName + ":"
                + st.getText.replace("\n", "")
                + " tweet at " + st.getCreatedAt.formatted("%tY/%<tm/%<td %<tH:%<tM:%<tS")
                + " URL:https://twitter.com/" + st.getUser.getScreenName + "/status/" + st.getId)

        //検索文字を指定して検索した結果を表示
        val twt = getTweet(tt, 0, Nil, 3, sw)
        println
        println("検索結果:" + twt.length + "件")
        for (st <- twt)
            println("@" + st.getUser.getScreenName + "/"
                + st.getUser.getName + ":"
                + st.getText.replace("\n", "")
                + " tweet at " + st.getCreatedAt.formatted("%tY/%<tm/%<td %<tH:%<tM:%<tS")
                + " URL:https://twitter.com/" + st.getUser.getScreenName + "/status/" + st.getId)
    }

    def getTweet(tt : twitter4j.Twitter, ni : Long, sts : List[twitter4j.Status], pf : Int, sw : String = "", ln : Int = 100) : List[twitter4j.Status] = {
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
                case _ => {
                    pg.setMaxId(mts.get(mts.size - 1).getId - 1)
                    getTweet(tt, pg.getMaxId, sts ::: mts.asScala.toList, pf, sw, ln)
                }
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
        twitterinfo(args(0))
    }
}