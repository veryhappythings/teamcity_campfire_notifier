TeamCity campfire plugin
========================

Copied from https://github.com/radamant/Team-City-Campfire-Notifier-Plugin with inspiration from some of the forks of that repo - thank you!

I've created this repo because I found a load of Teamcity campfire plugins, but I couldn't get any of them building because every project uses a different build system.  This one uses ant, and should actually just work if you type "ant".

So to build this, you'll need ant installed, and this build.xml assumes you have TeamCity installed in ../TeamCity.  You can edit that at the top of the file if you want.  Then, just run ant, and it should create build/campfireplugin-0.1.zip.  Copy that to .BuildServer/plugins on your TeamCity server and restart, and it should work.
