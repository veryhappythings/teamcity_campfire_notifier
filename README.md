TeamCity campfire plugin
========================

Copied from https://github.com/radamant/Team-City-Campfire-Notifier-Plugin with inspiration from some of the forks of that repo - thank you!

I've created this repo because I found a load of Teamcity campfire plugins, but I couldn't get any of them building because every project uses a different build system.  This one uses ant, and should actually just work if you type "ant".

So to build this, you'll need ant installed, and this build.xml assumes you have TeamCity installed in ../TeamCity.  You can edit that at the top of the file if you want.  Then, just run ant, and it should create build/campfireplugin-0.1.zip.  Copy that to .BuildServer/plugins on your TeamCity server and restart, and it should work.


Installation
========================

IMPORTANT: This Notifier is only set up to notify on "build failed" and "build succeeded". No other notification events are implemented.

1. Stop the TeamCity Server service.

2. Copy the JAR files from the dist/DLLs directory into your TeamCity Library: <TeamCity Install>\webapps\ROOT\WEB-INF\lib. Mine was C:\TeamCity\webapps\ROOT\WEB-INF\lib. Do not overwrite any of the JAR files that already exist.

3. Start the Teamcity Server service.

4. Open up the TeamCity Web Interface

5. Click on your name, and go to "My Settings and Tools".

6. Fill out this information and save changes.
	a. The Auth Token is obtained from Campfire. When logged in to campfire, click "My info" in the top right corner which brings you to a page displaying the token.
	b. The Campfire URL is the main page for your company’s Campfire page.
	c. Room ID is the room number which you want Campfire to report to. It is the number at the end of the URL for a Campfire chatroom.

7. Go to the “Notification Rules” section in "My Settings and Tools".

8. Add a new Campfire Notifier Rule if you don’t have one already. 