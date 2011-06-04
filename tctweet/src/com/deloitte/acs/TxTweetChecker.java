//
package com.deloitte.acs;
//
import junit.framework.*;



// Test for TweetChecker class 
public class TxTweetChecker extends TestCase 
{

	/*
  public static void test_getRecryptDaysInMillis() throws Exception
  {
    Assert.assertEquals(true, true);
    StringBuffer buff = CxTweetChecker.checkForTweet("psText");
    System.out.println( "*** line=" + buff );
    //for( int i=0; i<buff.length(); i++)
  }
*/

  public void test_tweet()
  {
	 // As that file does  not exist, the plugin should return 0
	 System.out.println("test tweet");
	 CxTwitterNotifier.sendTweet("UnitTest here, #2");
  }

  public static Test suite()
  {
      TestSuite suite = new TestSuite("com.deloitte.acs.TxTweetChecker");
      suite.addTestSuite(com.deloitte.acs.TxTweetChecker.class);

      return suite;
  }

  // Runs the test suite using the textual runner.
  public static void main(String[] args)
  {
      junit.textui.TestRunner.run(suite());
      System.exit(0);
  }


}  // TxGuard

