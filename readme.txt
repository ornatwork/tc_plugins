

Three plugins for TeamCity,  

emailinject - Allows your build script to inject custom message in the TeamCity email that is sent out after a build.

tctweet - Allows build statuses to be tweeted to a Twitter account.

unique - Allows your builds to share one unique build number counter across multiple projects.




-----------------


The workspace in Eclipse has been set to this location / directory in order for some of the project settings to work properly.



All the jars pretty much work the same, here is the gist of it 

Created - 7/2008
-------------
1. Jar the classess and descriptor file ( uniquebuildnumber.jar )
    \com\deloitte\acs\*.class etc
    \META-INF\build-server-plugin.xml
    In Eclipse, right click jar.jardesc, choose Create JAR, that will create uniquebuildnumber.jar 

2.Install on the server by dropping the jar into the 
   \TeamCity\webapps\ROOT\WEB-INF\lib
  directory 

3. Restart the server and the plugin should be loaded automatically
  The stdout_xxx.log should have entry similar to this, once the TeamCity server is started 
  *** UniqueBuildnumber adding listener=com.deloitte.acs.UniqueBuildNumber@ca8f43

4. Configure each project for the unique plugin, the plugin will examine each build number and 
  only replace the build / compile number for those marked unique.  Example 1.5.0.{unique}
  
  
