

Email template
-------
The email template ( email-config.xml ) on the TC server was changed to include 

build info={EMAIL_MSG_INJECTION}

in order for us to inject our own message into the build result email.


Location on the TeamCity server  
C:\Documents and Settings\acsbuild\.BuildServer\config\
Location locally 
\serv_config


Created - 8/2009
-------------
1. Jar the classess and descriptor file ( emailinject.jar )
    \com\deloitte\acs\*.class etc
    \META-INF\build-server-plugin.xml
    In Eclipse, right click jar.jardesc, choose Create JAR, that will create emailinject.jar 

2.Install on the server by dropping the jar into the 
   \TeamCity\webapps\ROOT\WEB-INF\lib
  directory 

3. Restart the server and the plugin should be loaded automatically
  The stdout_xxx.log should have entry similar to this, once the TeamCity server is started 
  *** EmailMsgInjector constructor, =com.deloitte.acs.EmailMsgInjector@ca8f43

  
  