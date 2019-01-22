package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.UserProfileResponseBody;

public interface OnUserInfoLoaded {

    void onUserInfoLoaded(UserProfileResponseBody profileBody);

    void onUserNotFound();
}
