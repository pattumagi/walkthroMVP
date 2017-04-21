package com.lakshmi.walkthromvp.splash;

import com.google.android.gms.common.api.GoogleApiClient;
import com.lakshmi.walkthromvp.base.BaseView;

/**
 * Created by mgs1899 on 4/12/2017.
 */

public interface SplashContract {

interface View extends BaseView{

        void gotoMain();

        void callMain();
        void showErrorScreen(String messageRes);
        void checkForPermission();
}
interface Presenter{
        void triggerSafetyNet(GoogleApiClient googleApiClient);

}


}
