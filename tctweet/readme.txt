
Created - 8/2009
-------------
1. Jar the classess and descriptor file ( tctweet.jar )
    \com\deloitte\acs\*.class 
    \META-INF\build-server-plugin.xml
    In Eclipse, right click jar.jardesc, choose Create JAR, that will create tctweet.jar 

2.Install on the server by dropping the jar into the 
   \TeamCity\webapps\ROOT\WEB-INF\lib
  directory, as well as twitter4j-core.2.2.2.jar from the /lib directory 

3. Restart the server and the plugin should be loaded automatically
  The stdout_xxx.log should have entry similar to this, once the TeamCity server is started 
  *** Notifier adding listener=com.deloitte.acs.TwitterNotifier@ca8f43
