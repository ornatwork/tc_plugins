//
package com.deloitte.acs;
//
import junit.framework.*;



public class TxMisc extends TestCase
{
	 

    public void test_replaceNonNumeric()
    {
      System.out.println("My test is running");
      //
      UniqueBuildnumber.setFileName( "some.txt" );
      String filename = UniqueBuildnumber.getFileName();
      System.out.println("filename=" + filename);
      //
      Assert.assertEquals("some.txt", filename );
    }
 	  
    public void test_peek()
    {
    	// As that file does  not exist, the plugin should return 0
		String  compilePeekVer = UniqueBuildnumber.peekNextBuildNumber();
		System.out.println("peek ver=" + compilePeekVer );
		Assert.assertEquals( "0", compilePeekVer );
    }


    
   public static Test suite()
   { 
      TestSuite suite = new TestSuite();
      // Add more tests here
      suite.addTestSuite(com.deloitte.acs.TxMisc.class);

      return suite;
    }

  // Runs the test suite using the textual runner
  // Run as java app, it will output to the console window
  public static void main(String[] args)
  {
      junit.textui.TestRunner.run(suite());
      System.exit(0);
  }

}  // EOC
