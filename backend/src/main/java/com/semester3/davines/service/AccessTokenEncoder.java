package com.semester3.davines.service;

import com.semester3.davines.domain.AccessToken;

public interface AccessTokenEncoder {

    String encode(AccessToken accessToken);
}
