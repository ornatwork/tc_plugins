//
package com.deloitte.acs;
//
import jetbrains.buildServer.messages.BuildMessage1;
import jetbrains.buildServer.serverSide.BuildServerAdapter;
import jetbrains.buildServer.serverSide.SBuildServer;
import jetbrains.buildServer.serverSide.SRunningBuild;


// Looks for messages tagged with the INTERCEPT_MARKER 
// marker meaning that it is the information from the build
// that will then be added by EmailMsgInjector into the email
// that TC sends out after a build.
//
// Today this is a workaround for TC limitation of not being able to 
// relay information from the build script to the TC server
//
public class EmailInterceptor extends BuildServerAdapter 
{
   private final static String INTERCEPT_MARKER = "EmailInjectMsg=";
  
   
   // Constructor, register the listener ( this )
   public EmailInterceptor(SBuildServer aBuildServer) 
   {
       System.out.println( "*** EmailInterceptor adding myself as listener=" + this );
       // Register with TC 
       aBuildServer.addListener(this);
   }

  
    // Intercepts injection message from the build log
	public void messageReceived(SRunningBuild arg0, BuildMessage1 poMsg ) 
	{
		//System.out.println( "*** log message=" + poMsg.getValue().toString() );
		
		// If the intercept message is found set the global variable 
		if( poMsg.getValue().toString().startsWith( INTERCEPT_MARKER ) )
		{
			System.out.println( "***** INTERCEPTED ****** message=" + poMsg.getValue().toString() );
			GlobalConst.EmailInjectMsg = poMsg.getValue().toString().substring( INTERCEPT_MARKER.length() ); 
		}
	}


}  // EOC
