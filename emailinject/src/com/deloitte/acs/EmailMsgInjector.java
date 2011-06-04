//
package com.deloitte.acs;
//
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.notification.TemplateMessageBuilder;
//
import com.intellij.openapi.diagnostic.Logger;
import jetbrains.buildServer.notification.RunningBuildPatternProcessor;
import org.jetbrains.annotations.Nullable;



// This class will replace the string {EMAIL_MSG_INJECTION}
// from the email template with a text string that the build
// script set during the build, if any.  Most likely it will be empty
// unless there was a failure during the build.
public class EmailMsgInjector extends RunningBuildPatternProcessor 
{

   private final static Logger LOG = Logger.getInstance(EmailMsgInjector.class.getName());
   private final static String INJECT_MSG = "{EMAIL_MSG_INJECTION}";

   
	public EmailMsgInjector(TemplateMessageBuilder builder) 
	{
		System.out.println( "*** EmailMsgInjector constructor, =" + this );
		LOG.info( "*** EmailMsgInjector constructor, =" + this );
	  
		builder.setPatternProcessor( INJECT_MSG, this);
	}
	 
	@Nullable
	public String process(SRunningBuild build) 
	{
	    	
	    // Process and return substitution for INJECT_MSG = {EMAIL_MSG_INJECTION} pattern:
	    String sRet = GlobalConst.EmailInjectMsg;
	    // Reset the global message, don't want it picked up later on
	    GlobalConst.EmailInjectMsg = "None!";
	    
	    return sRet;
	}
	      
  
}  // EOC

