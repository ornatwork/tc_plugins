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

3. Setup a text file to keep the global unique number.  Goto TeamCity Administration -> Server Configuration -> General.
  Once your there you should see a section towards the bottom for the plugin.  ( UniqueBuildNumber plugin settings )
  Create a text file with the build number you want to start at.  There should only be one number in this file, 
  this is the build number counter.  Make sure the text file is read and writeable and in a directory that is read and writeable.
  Once you set the file and hit ok, you can refresh the page to make sure it's working.  You should see a text similar to
  this in the plugin section -- Next build number from file: 101

4. Configure each project for the unique plugin, the plugin will examine each build number and 
  only replace the build / compile number for those marked unique.  Example 1.5.0.{unique}
  
  