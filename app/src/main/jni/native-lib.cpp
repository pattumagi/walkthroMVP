#include <jni.h>
#include <string>
//#include <android/log.h>
//
//
//#define  LOG_TAG    "SBI_PAY"
//#define  //LOGD(...)  if (DEBUG) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__);
//#define  LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__);

/* Set to 1 to enable debug log traces. */
static int DEBUG = 1;


std::string symmetricKey = "0bf86b86716ca8348d50a66673d38255";

std::string oAuthPasswordDev = "sbipay@123";
std::string oAuthPasswordQa = "f89e2d511390414a91a89fc5e0f8f5e4";
std::string oAuthPasswordProd = "f89e2d511390414a91a89fc5e0f8f5e4";

std::string refreshToken = "";
std::string accessToken = "";
std::string nonce = "";

std::string clientSecret = "";


extern "C"
jstring
Java_com_sbi_upi_common_NativeInteractor_getRefreshToken(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(refreshToken.c_str());
}

extern "C"
jstring
Java_com_sbi_upi_common_NativeInteractor_getSymmetricKey(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(symmetricKey.c_str());
}

extern "C"
jstring
Java_com_sbi_upi_common_NativeInteractor_getOAuthPasswordDev(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(oAuthPasswordDev.c_str());
}

extern "C"
jstring
Java_com_sbi_upi_common_NativeInteractor_getOAuthPasswordQa(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(oAuthPasswordQa.c_str());
}

extern "C"
jstring
Java_com_sbi_upi_common_NativeInteractor_getOAuthPasswordProd(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(oAuthPasswordProd.c_str());
}

extern "C"
jstring
Java_com_sbi_upi_common_NativeInteractor_getNonce(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(nonce.c_str());
}

extern "C"
jstring
Java_com_sbi_upi_common_NativeInteractor_getClientSecret(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(clientSecret.c_str());
}

extern "C"
jstring
Java_com_sbi_upi_common_NativeInteractor_getAccessToken(
        JNIEnv *env,
        jobject /* this */) {
    return env->NewStringUTF(accessToken.c_str());
}



extern "C"
void
Java_com_sbi_upi_common_NativeInteractor_setRefreshToken(
        JNIEnv *env,
        jobject thiz,
        jstring jRefreshToken) {
    //LOGD("SetRefreshToken : %s ", jRefreshToken);
    const jsize len = env->GetStringUTFLength(jRefreshToken);
    const char *strChars = env->GetStringUTFChars(jRefreshToken, (jboolean *) 0);

    refreshToken = std::string(strChars, len);

    env->ReleaseStringUTFChars(jRefreshToken, strChars);
}

extern "C"
void
Java_com_sbi_upi_common_NativeInteractor_setAccessToken(
        JNIEnv *env,
        jobject thiz,
        jstring jAccessToken) {
    //LOGD("SetAccessToken: %s ", jAccessToken);
    const jsize len = env->GetStringUTFLength(jAccessToken);
    const char *strChars = env->GetStringUTFChars(jAccessToken, (jboolean *) 0);

    accessToken = std::string(strChars, len);

    env->ReleaseStringUTFChars(jAccessToken, strChars);
}

extern "C"
void
Java_com_sbi_upi_common_NativeInteractor_setNonce(
        JNIEnv *env,
        jobject thiz,
        jstring jNonce) {
    //LOGD("SetAccessToken: %s ", jNonce);
    const jsize len = env->GetStringUTFLength(jNonce);
    const char *strChars = env->GetStringUTFChars(jNonce, (jboolean *) 0);

    nonce = std::string(strChars, len);

    env->ReleaseStringUTFChars(jNonce, strChars);
}

extern "C"
void
Java_com_sbi_upi_common_NativeInteractor_setClientSecret(
        JNIEnv *env,
        jobject thiz,
        jstring jClientSecret) {
    const jsize len = env->GetStringUTFLength(jClientSecret);
    const char *strChars = env->GetStringUTFChars(jClientSecret, (jboolean *) 0);

    clientSecret = std::string(strChars, len);

    env->ReleaseStringUTFChars(jClientSecret, strChars);
}
