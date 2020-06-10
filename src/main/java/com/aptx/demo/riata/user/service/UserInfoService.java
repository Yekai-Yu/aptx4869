package com.aptx.demo.riata.user.service;

import com.aptx.demo.riata.user.model.UserDTO;

public interface UserInfoService {

    public UserDTO getUserInfoById(String uid);

    public String updateUserInfo(UserDTO userDTO);

}
