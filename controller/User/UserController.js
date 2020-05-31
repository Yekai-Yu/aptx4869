'use strict';

const UserModel = require('../../models/user/user');

// class UserController {
    // GET /user/:uid
exports.getUserInfoById = (req, res, next) => {
        const uid = req.params.uid;
        if(!uid || !Number(uid)) {
            console.log('Invalid uid');
            res.status(404).send("User not found");
            return;
        }
        try {
            // const userInfo = await UserModel.findOne({ID: uid});
            const userInfo = "test User data";
            res.send(userInfo);
        } catch(e) {
            console.log('Failed to get user info', e);
            res.status(404).send("User not found");
        }
    };
// }

// module.exports = UserController;
