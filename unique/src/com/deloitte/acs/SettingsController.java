//
package com.deloitte.acs;
//
import java.util.Map;
//
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//
import jetbrains.buildServer.controllers.BaseController;
import jetbrains.buildServer.web.openapi.SimpleWebExtension;
import jetbrains.buildServer.web.openapi.WebControllerManager;
import jetbrains.buildServer.web.openapi.WebPlace;
import jetbrains.buildServer.serverSide.SBuildServer;
//
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


public class SettingsController extends BaseController implements BuildNumberSettings 
{
  private boolean mbEnabled = true;
  private String msFileName = "notset"; 
  public String PLUGIN_NAME = "UniqueBuildNumber";

    
  public SettingsController(final SBuildServer server, final WebControllerManager manager) 
  {
    super(server);
    //
    System.out.println( "*** SettingsController constructor" );
    //
    manager.registerController("/admin/buildnumbersettings.html", this);
    SimpleWebExtension extension = new SimpleWebExtension(manager) 
    {
      public void fillModel(final Map model, @NotNull final HttpServletRequest request) 
      {
        super.fillModel(model, request);
        
        // This name / key MUST match the name of the bean in settings jsp page
        model.put("buildsettings", SettingsController.this);
      }
    };
    extension.setPluginName(PLUGIN_NAME);
    extension.setPlace(WebPlace.ADMIN_SERVER_CONFIGURATION);
    extension.setJspPath("settings.jsp");
    extension.register();
  }

  @Nullable
  protected ModelAndView doHandle(final HttpServletRequest request, final HttpServletResponse response) throws Exception 
  {
    if (request.getParameter("enabled") != null) 
    {
      mbEnabled = Boolean.valueOf(request.getParameter("enabled"));
    }
    
    // If filename submitted from the setting page
    // set that for the UniqueBuildnumber class
    if (request.getParameter("filename") != null) 
    {
      String newfilename = request.getParameter("filename");
      if( newfilename.length() > 0 )
      {
    	  System.out.println( "*** SettingsController set new file name=" + newfilename );
    	  msFileName = newfilename;
    	  UniqueBuildnumber.setFileName( newfilename );
      }
    }

    return new ModelAndView(new RedirectView("/admin/serverConfig.html", true));
  }

  public boolean isEnabled() 
  {
    return mbEnabled;
  }

  public void setFilename( String psFilename )
  {
	msFileName = psFilename;
  }

  public String getFilename()
  {
	return msFileName;
  }
  
  
}  // EOC
