import jetbrains.buildServer.Build;
import jetbrains.buildServer.notification.Notificator;
import jetbrains.buildServer.notification.NotificatorRegistry;
import jetbrains.buildServer.responsibility.TestNameResponsibilityEntry;
import jetbrains.buildServer.serverSide.SBuildType;
import jetbrains.buildServer.serverSide.SProject;
import jetbrains.buildServer.serverSide.SRunningBuild;
import jetbrains.buildServer.serverSide.UserPropertyInfo;
import jetbrains.buildServer.users.NotificatorPropertyKey;
import jetbrains.buildServer.users.SUser;
import jetbrains.buildServer.vcs.VcsRoot;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: apearson
 * Date: Sep 8, 2010
 * Time: 5:04:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CampfireTeamCityNotificator implements Notificator {

    private static final String TYPE = "campfireNotifier";
    private static final String TYPE_NAME = "Campfire Notifier";
    private static final String CAMPFIRE_ROOM_NUMBER = "campfireRoomNumber";
    private static final String CAMPFIRE_AUTH_TOKEN = "campfireAuthToken";
    private static final String CAMPFIRE_URL = "campfireUrl";
    private static final String CAMPFIRE_USE_SSL = "campfireUseSsl";

    public CampfireTeamCityNotificator(NotificatorRegistry notificatorRegistry){
     ArrayList<UserPropertyInfo> userProps = new ArrayList<UserPropertyInfo>();
     userProps.add(new UserPropertyInfo(CAMPFIRE_AUTH_TOKEN, "Auth Token"));
     userProps.add(new UserPropertyInfo(CAMPFIRE_URL, "Campfire Url"));
     userProps.add(new UserPropertyInfo(CAMPFIRE_USE_SSL, "Use SSL (Y or N)"));
     userProps.add(new UserPropertyInfo(CAMPFIRE_ROOM_NUMBER, "Room Id"));

     notificatorRegistry.register(this, userProps);
    }

    public void notifyBuildStarted(SRunningBuild sRunningBuild, Set<SUser> sUsers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyBuildSuccessful(SRunningBuild sRunningBuild, Set<SUser> sUsers) {
        String message = "Build " + sRunningBuild.getFullName() + " #" +
                sRunningBuild.getBuildNumber() + " succeeded. ";

        for (SUser user : sUsers) {
            notify(user, message);
        }
    }

    public void notifyBuildFailed(SRunningBuild sRunningBuild, Set<SUser> sUsers) {
        String message =  "Build " + sRunningBuild.getFullName() + " #" +
                sRunningBuild.getBuildNumber() + " failed. ";

        for (SUser user : sUsers) {
            notify(user, message);
        }

    }

    private void notify(SUser user, String message) {
        String authToken = user.getPropertyValue(new NotificatorPropertyKey(TYPE, CAMPFIRE_AUTH_TOKEN));
        String url = user.getPropertyValue(new NotificatorPropertyKey(TYPE, CAMPFIRE_URL));
        Boolean useSsl = user.getPropertyValue(new NotificatorPropertyKey(TYPE, CAMPFIRE_USE_SSL)).trim().equalsIgnoreCase("y");
        String roomNumber = user.getPropertyValue(new NotificatorPropertyKey(TYPE, CAMPFIRE_ROOM_NUMBER));

        Campfire campfire = new Campfire(authToken, url, useSsl);
        campfire.postMessage(roomNumber, message);
    }

    public void notifyLabelingFailed(Build build, VcsRoot vcsRoot, Throwable throwable, Set<SUser> sUsers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyBuildFailing(SRunningBuild sRunningBuild, Set<SUser> sUsers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyBuildProbablyHanging(SRunningBuild sRunningBuild, Set<SUser> sUsers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleChanged(SBuildType sBuildType, Set<SUser> sUsers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleAssigned(SBuildType sBuildType, Set<SUser> sUsers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleChanged(TestNameResponsibilityEntry testNameResponsibilityEntry,
                                         TestNameResponsibilityEntry testNameResponsibilityEntry1,
                                         SProject sProject, Set<SUser> sUsers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void notifyResponsibleAssigned(TestNameResponsibilityEntry testNameResponsibilityEntry,
                                          TestNameResponsibilityEntry testNameResponsibilityEntry1,
                                          SProject sProject, Set<SUser> sUsers) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public String getNotificatorType() {
        return TYPE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    public String getDisplayName() {
        return TYPE_NAME;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
