Created - 7/2008

This project includes three plugins for TeamCity, 
---------------------------------
  * emailinject - Injects message from build script into the body of the email TeamCity sends out after a build.
  
  * tctweet - Tweets build starts and end messages on twitter to a specific twitter account you can follow to get notified about your builds.
  
  * unique - Issues unique build numbers to builds across projects in a simple counter fashion.  This is valuable when you have two or more differnt major versions that you want to share the build number.  Other example is when you seperate nightly and continues configurations.


The jars have been explicitly compiled under Java 1.5 to work on older TeamCity servers.



All the jars pretty much work the same, here is the gist of it 
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
  only replace the build / compile number for those marked {unique}  Example 1.5.0.{unique}
  
  
