//
package com.deloitte.acs;
//
import java.io.*;
import java.net.Authenticator;
import java.net.URL;
import java.net.URLEncoder;
import java.net.URLConnection;
//
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.messages.Status;
//
import com.intellij.openapi.diagnostic.Logger;



// Monitors the Twitter account for tweets that will trigger a build
// and triggers build accordingly
public class CxTweetChecker extends BuildServerAdapter 
{
   private final static Logger LOG = Logger.getInstance(CxTweetChecker.class.getName());
   private SBuildServer mServer = null; 
   
   // Constructor, register the listener ( this )
   public CxTweetChecker(SBuildServer aBuildServer) 
   {
       System.out.println( "*** Adding tweet listener=" + this );
       LOG.info("*** Adding tweet listener=" + this);
       
       // Set referance to the server
       mServer = aBuildServer;
   }

   
   // Checks the twitter account for build initiation   
   public static StringBuffer checkForTweet( String psText )
   {
	   StringBuffer sbRet = new StringBuffer();
	   
	   try
	   {
		   //  Using parameters known to work
		   String urlString = "http://twitter.com/statuses/update.xml";
		   String data = "status=" + URLEncoder.encode( psText, "UTF-8");
	       
	       // Credentials
	       Authenticator.setDefault(new CxAuthenticator(CxGlobal.TwitterName, CxGlobal.TwitterPass));

	       // Send data
	        URL url = new URL(  urlString );
	        URLConnection conn = url.openConnection();
	        conn.setDoOutput(true);
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(data);
	        wr.flush();
	        
	        // Get the response
	        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        while ((line = rd.readLine()) != null) 
	        	sbRet.append(line + "\n" );

	        //
	       // System.out.println( "*** Response=" + sb.toString() );
	        
	        // Close  	
	        wr.close();
	        rd.close();
	   }
	   catch( Exception ex )
	   {
	       System.out.println( "***ERROR=" + stack2string( ex ));
	       LOG.info( "***ERROR=" + stack2string( ex ) );
	   }
	   
	   return sbRet;
   }

   public static String stack2string(Exception e) 
   {
      try 
      {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return "------\r\n" + sw.toString() + "------\r\n";
      }
      catch(Exception e2) 
      {
        return "bad stack2string";
      }
   }

    
}  // EOC
