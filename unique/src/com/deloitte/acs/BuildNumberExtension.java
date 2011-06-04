//
package com.deloitte.acs;
//
import java.util.List;
import java.util.Map;
//
import javax.servlet.http.HttpServletRequest;
//
import jetbrains.buildServer.web.openapi.WebControllerManager;
import jetbrains.buildServer.web.openapi.WebExtension;
import jetbrains.buildServer.web.openapi.WebPlace;
import jetbrains.buildServer.web.openapi.WebResourcesManager;
//
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


// This is where the resources are extracted and the plugin registered with TC
// the jsp pages are extracted out of the jar and put in the plugin dir 
public class BuildNumberExtension implements WebExtension 
{

  public BuildNumberExtension(@NotNull WebControllerManager manager,
                            @NotNull WebResourcesManager resManager,
                            @NotNull BuildNumberSettings settings) 
  {
	System.out.println( "*** BuildNumberExtension adding resources from uniquebuildnumber.jar" );
	
    manager.addPageExtension(WebPlace.PAGE_HEADER, this);
    resManager.addPluginResources(getPluginName(), "uniquebuildnumber.jar");
  }

  @NonNls
  @NotNull
  public String getPluginName() 
  {
    return BuildNumberSettings.PLUGIN_NAME;
  }

  @NotNull
  public String getTitle() 
  {
    return "";
  }

  public List<String> getCssFiles() 
  {
    return null;
  }

  public List<String> getJsFiles() 
  {
    return null;
  }

  @Nullable
  @NonNls
  public String getJspFilePath() 
  {
    //return myResManager.resourcePath(getPluginName(), "joke.jsp");
	return "";
  }

  public void fillModel(final Map model, @NotNull final HttpServletRequest request) 
  {}

  public boolean isAvailable(@NotNull final HttpServletRequest request) 
  {
    //return mySettings.isEnabled();
	return false;
  }

  @NotNull
  public String getTitle(@NotNull final HttpServletRequest request) 
  {
    return "";
  }


} // EOC
