//
package com.deloitte.acs;
//
import java.io.*;
//
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SRunningBuild;
//
import com.intellij.openapi.diagnostic.Logger;


// Sets the build number if the build profile asks for it
// the build profile must specify {unique} to mark that 
// the build number should be overridden 
public class UniqueBuildnumber extends BuildServerAdapter 
{
   private final static Logger LOG = Logger.getInstance(UniqueBuildnumber.class.getName());
   // Default / hardcode
   private static String msFile = "File name not set";
   private static final String UNIQUE = "{unique}";  
   
   
   // Constructor, register the listener ( this )
   public UniqueBuildnumber(SBuildServer aBuildServer) 
   {
       System.out.println( "*** UniqueBuildnumber adding listener=" + this );
       LOG.info("*** UniqueBuildnumber adding listener=" + this);

       // Get value from datafile, if available 
       String temp = getTextFromFile( GlobalConst.DataFile );
       if( temp != null && !temp.equals("") )
    	   msFile = temp;

       // Register with TC 
       aBuildServer.addListener(this);
   }

   
   // Build started, set the version number
   public void buildStarted(SRunningBuild build) 
   {
       String sTcBuildNumber = build.getBuildNumber();
       System.out.println( "*** UniqueBuildnumber plugin, buildStarted, getting build number, default value=" +  sTcBuildNumber );
       LOG.info("*** UniqueBuildnumber plugin, buildStarted, getting build number, default value=" + sTcBuildNumber );

       // Set the build number if the configuration asked for it, ie UNIQUE = {unique}
       // The configuration has to look something like 1.5.0.{unique}
       if( sTcBuildNumber.lastIndexOf( UNIQUE ) > -1 ) 
       {
    	   // Last portion of the build number will be set automatically
    	   int iWhere = sTcBuildNumber.lastIndexOf('.');
    	   build.setBuildNumber(createUniqueBuildNumber( sTcBuildNumber.substring( 0, iWhere + 1 ) ));
       }
       else
    	   System.out.println( "*** UniqueBuildnumber plugin, no {unique} marker not overriding default value=" +  sTcBuildNumber );
   }

   
   
   // Fetches and sets the compile unique number 
   private String createUniqueBuildNumber( String psTopVersion ) 
   {
	  int buildNumber = 0;
   
	   try
	   {
			//
			System.out.println( "*** UniqueBuildnumber plugin, file name for compile number=" + msFile );
			   
			// Get build number from file, update and save to file
			 buildNumber = Integer.parseInt( getTextFromFile( msFile ) );
			 writeToFile( msFile, "" + ( buildNumber + 1 ) );
			
			 System.out.println( "*** UniqueBuildnumber plugin, creating build Number, version=" + psTopVersion + buildNumber );
			 LOG.info( "*** UniqueBuildnumber plugin, creating build Number, version=" + psTopVersion + buildNumber );
	   }
	   catch( Exception ex )
	   {
		   System.out.println( "*** UniqueBuildnumber plugin, error=" + ex );
	   }
	   
	   // Return new number to caller 
	   return psTopVersion + buildNumber;
   }
   
   
   // Read text from file 
   static private String getTextFromFile( String psFile ) 
   {
	    //
	    File readFile = new File( psFile );
	    StringBuilder contents = new StringBuilder();
	    
	    try 
	    {
	      // use buffering, reading one line at a time
	      // FileReader always assumes default encoding is OK!
	      BufferedReader input =  new BufferedReader(new FileReader( readFile));
	      try 
	      {
	        String line = null; 
	        while (( line = input.readLine()) != null)
	          contents.append(line);
	      }
	      finally 
	      {
	        input.close();
	      }
	    }
	    catch (IOException ex)
	    {
	      //ex.printStackTrace();
	      System.out.println( "*** UniqueBuildnumber plugin, error=" + ex );
	    }

	    System.out.println( "*** UniqueBuildnumber plugin, read text from file, text=" + contents.toString() );
	    return contents.toString();
	  }

   	  // Write text to file 	
	  static private void writeToFile(String psFile, String psText )
	                                 throws FileNotFoundException, IOException 
      {
	    // Use buffering
		File writeFile = new File( psFile );
	    Writer output = new BufferedWriter(new FileWriter(writeFile));
	    try 
	    {
	      // FileWriter always assumes default encoding is OK!
	      output.write( psText );
	    }
	    finally 
	    {
	      output.close();
	    }
	    
	    System.out.println( "*** UniqueBuildnumber plugin, saved text to file, text=" + psText );
	  }

	  static public void setFileName( String psFileName )
	  {
		  msFile = psFileName;
		  try{ writeToFile( GlobalConst.DataFile, msFile ); } catch( Exception dontcare){}
	  }
	  static public String getFileName()
	  {
		  return msFile;
	  }
	  
	  // Returns what's the next expected build number, to show in the admin / config interface
	  static public String peekNextBuildNumber()
	  {
		// Get build number from file, return what's going to be used next
		int buildNumber = 0;
		String tmp = getTextFromFile( msFile );

		// if there is no value, no need to set it 
		if( tmp.length() > 0  )  
		{
			buildNumber = Integer.parseInt( getTextFromFile( msFile ) );
			buildNumber++;
		}
		
		//
		return "" + buildNumber;
	  }
	  



}  // EOC
