package com.mrntlu.myanimeinfo.view;

import com.mrntlu.myanimeinfo.service.model.jsonresponsebody.CharacterInfoBody;

public interface OnCharacterInfoLoaded {

    void onCharacterInfoLoaded(CharacterInfoBody body);

    void onFailedToLoad();
}
