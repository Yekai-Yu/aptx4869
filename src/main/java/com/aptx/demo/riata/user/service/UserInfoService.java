package com.aptx.demo.riata.user.service;

import com.aptx.demo.riata.user.model.UserDTO;

import java.util.List;

public interface UserInfoService {

    public UserDTO getUserInfoById(String uid);

    public UserDTO updateUserInfo(String uid, List<String> preference);

}
