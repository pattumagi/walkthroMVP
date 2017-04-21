package com.lakshmi.walkthromvp.common.preferences;

/**
 * Created by mgs1850 on 1/4/2017.
 */

public interface SharedPreferencesInteractor {

    interface RefreshTokenListener {
        void onRefreshTokenFetched(String refreshToken);

    }

    interface AccessTokenListener {
        void onAccessTokenFetched(String accessToken);
    }

    interface AppPassCodeListener {
        void onPassCodeFetched(String passCode);
    }

    interface SimStatePair {
        void onSimStateFetched(String simStatePair);
    }

    interface ListKeyToken {
        void onListKeyTokenFetched(String listKeyToken);
    }

    interface ChallangeType {
        void onChallangeTypeFetched(String challangeType);
    }

    interface ChallangeSubtype {
        void onChallangeSubtypeFetched(String challangeSubType);
    }

    interface SimSerialNoLength {
        void onSimSerialNoLength(int simSerialNoLength);
    }

    interface MobileNumber {
        void onMobileNumberFetched(String mobileNumber);
    }

    void setAppPassCode(String passCode);

    void getAppPassCode(AppPassCodeListener appPassCodeListener);

    void getSimStatePair(SimStatePair simStatePair);

    void getSimSerialNoLength(SimSerialNoLength simSerialNoLength);

    void getListKeyToken(ListKeyToken listKeyToken);

    void getChallangeType(ChallangeType challangeType);

    void getChallangeSubtype(ChallangeSubtype challangeSubtype);

    void getMobileNumber(MobileNumber mobileNumber);

    void getAccessToken(AccessTokenListener accessTokenListener);

    void getRefreshToken(RefreshTokenListener onRefreshTokenFetched);
}
