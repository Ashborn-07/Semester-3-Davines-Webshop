package com.semester3.davines.service;

import com.semester3.davines.domain.models.AccessToken;

public interface AccessTokenDecoder {

    AccessToken decode(String accessTokenEncoded);
}
